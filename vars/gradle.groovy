def call(){

    if(util.validateStage('build') || util.validateStage('test'))
    {
        stage('build & test') {
            bat "gradle clean build"
        }
    }

    if(util.validateStage('run'))
    {
        stage('run') {
           sh 'JENKINS_NODE_COOKIE=dontKillMe nohup start gradlew bootRun &'
            sleep 20
        }
    }

    if(util.validateStage('rest'))
    {
        stage('rest') {
            bat 'curl -X GET "http://localhost:8081/rest/mscovid/test?msg=testing"'
        }
    }

}

return this;
