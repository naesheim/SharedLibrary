def call(String applicationName){
    WORK_ITEM = null
    APPLICATION_NAME = applicationName
    CHANGE_REPO_URL = "git@github.com:naesheim/chachachanges.git"

    node('master') {
        withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'pythonpass',
        usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
            stage('extract work items') {
                checkout scm
                GITCOMMIT = "git log -1 --pretty=%B".execute().text
                WORK_ITEM = workItem(GITCOMMIT)
            }

            stage('commit change') {
                git branch: 'master', credentialsId: '01f6690e-ef95-4a99-a882-c140e863c6db', url: CHANGE_REPO_URL
                sh 'python3 -m venv venv'
                sh "venv/bin/pip3 install -r requirements.txt"
                echo "work items: $WORK_ITEM and applicationName: $APPLICATION_NAME"
                sh "work items ${WORK_ITEMS.join(' ')}"
            }
        }
    }
}
