def call(String applicationName){
    WORK_ITEM = null
    APPLICATION_NAME = applicationName
    CHANGE_REPO_URL = "https://github.com/naesheim/chachachanges"

    node {

        stage('extract work items') {
            checkout scm
            GIT_COMMIT = sh(returnStdout: true, script: 'git log -1 --pretty=%B')
            WORK_ITEM = gitCommit GIT_COMMIT
        }

        stage('commit change') {
            git url CHANGE_REPO_URL
            sh 'pyvenv venv'
            sh "venv/bin/pip3 install -r requirements.txt"
            echo sh(returnStdout: true, script: 'ls')
        }
    }
}
