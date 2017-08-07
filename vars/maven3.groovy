
/**
 * Standard Java 1.8 pipeline for Jetty modules.
 *
 * // TODO EXPLAIN STEPS
 *
 * @param module		name of the module to deploy (Must be identical to the module health check string)
 * @param contextPath	the context path of the module, such as "MyModuleEndpoint/api"
 * @param serverGroup	map of serves to deploy the module to, such as "MnetApi"
 * @param artifactId	optional if the artifactId in pom is not correct
 */
def call(
	String module
) {
	pipeline {
		agent none
		options {
			// timestamps()
			skipDefaultCheckout()
			timeout(time: 1, unit: 'DAYS')
		}
		stages {
			stage('Build & Upload') {
				agent any
				steps {
					echo '\n\nBuilding...\n'
				}
			}
			stage('Deploy to DEV') {
				agent any
				steps {
					echo '\n\nDEV deploy...\n'
				}
			}
			stage('Integration Tests') {
				agent any
				steps {
					echo '\n\nIntegration tests...\n'
				}
			}
			stage('SonarQube') {
				agent any
				steps {
					echo '\n\nSonarQube...\n'
				}
			}
			stage('TEST deploy?') {
				agent none
				steps {
					input "Deploy to TEST?"
				}
			}
			stage('Deploy to TEST') {
				agent any
				steps {
					// deploy(this, 'TEST', buildData)
					echo '\n\nTEST deploy...\n'
				}
			}
			stage('Finish if not on on master-branch'){
				agent none
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
				agent none
				when {
					branch 'development'
				}
				steps {input "Deploy to STAGING?"}
			}
			stage('Deploy to STAGING') {
				agent {label 'linux'}
				when {
					branch 'development'
				}
				steps {
					// deploy(this, 'STAGING', buildData)
					echo '\n\nSTAGING deploy...\n'
				}
			}
			stage('PRODUCTION deploy?') {
				agent none
				when {
					branch 'development'
				}
				steps {
					input "Deploy to PRODUCTION?"
				}
			}
			stage('Deploy to PRODUCTION') {
				agent any
				when {
					branch 'development'
				}
				steps {
					// deploy(this, 'PRODUCTION', buildData)
					echo '\n\nPRODUCTION deploy...\n'
				}
			}
		}
	}
}
