def call(String module) {
	pipeline {
	    agent any
		stages {
			stage('Build & Upload') {
				when {
					allOf {
						branch 'master'
						expression { "a" == "a" }
					}
				}
				steps {
					echo '\n\nBuilding...\n'
				}
			}
			stage('Deploy to DEV') {
				steps {
					echo '\n\nDEV deploy...\n'
				}
			}
			stage('Integration Tests') {
				steps {
					echo '\n\nIntegration tests...\n'
				}
			}
			stage('SonarQube') {
				steps {
					echo '\n\nSonarQube...\n'
				}
			}
			stage('TEST deploy?') {
				steps {
					input "Deploy to TEST?"
				}
			}
			stage('Deploy to TEST') {
				steps {
					// deploy(this, 'TEST', buildData)
					echo '\n\nTEST deploy...\n'
				}
			}
			stage('Finish if not on on master-branch'){
				when{
					not {branch 'development'}
				}
				steps {
					script {
						String msg = '\n\n';
						// msg += "Merge commit ${buildData['commitSha1']} "
						msg += "from branch \'${BRANCH_NAME}\' to \'master\' "
						msg += 'branch if you want to deploy past TEST milieu.'
						// error msg + '\n'
						echo msg + '\n'
					}
				}
			}
			stage('STAGING deploy?') {
				when {
					branch 'moller'
				}
				steps {input "Deploy to STAGING?"}
			}
			stage('Deploy to STAGING') {
				when {
					branch 'development'
				}
				steps {
					// deploy(this, 'STAGING', buildData)
					echo '\n\nSTAGING deploy...\n'
				}
			}
			stage('PRODUCTION deploy?') {
				when {
					branch 'development'
				}
				steps {
					input "Deploy to PRODUCTION?"
				}
			}
			stage('Deploy to PRODUCTION') {
				when {
					expression {return false}
				}
				steps {
					// deploy(this, 'PRODUCTION', buildData)
					echo '\n\nPRODUCTION deploy...\n'
				}
			}
		}
	}
}
