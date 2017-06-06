def call(){
	def commit = sh(returnStdout: true,  script: 'git log --online').trim()
    echo ${commit}
}
