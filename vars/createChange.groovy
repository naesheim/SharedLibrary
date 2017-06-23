import static no.moller.WorkItem.*

def call(String applicationName){
    WORK_ITEM = null
    APPLICATION_NAME = applicationName
    CHANGE_REPO_URL = "git@github.com:naesheim/chachachanges.git"

    node('master') {
        withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'pythonpass',
        usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
            stage('extract work items') {
                echo "uname $USERNAME pwd=$PASSWORD"
                checkout scm
                GIT_COMMIT = sh(returnStdout: true, script: 'git log -1 --pretty=%B')
                WORK_ITEM = getWorkItem(this, GIT_COMMIT)
            }

            stage('commit change') {
                git branch: 'master', credentialsId: '2d7f1202-2262-4dc2-b9f8-30744ba7688d', url: CHANGE_REPO_URL
                sh 'python3 -m venv venv'
                sh "venv/bin/pip3 install -r requirements.txt"
                echo "work iteks: $WORK_ITEM and applicationName: $APPLICATION_NAME"
                sh "venv/bin/python3 changeAnywhere -u ${env.USERNAME} -p ${env.PASSWORD}"
            }
        }
    }
}
