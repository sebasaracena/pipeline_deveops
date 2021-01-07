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
            bat 'start /B gradle bootRun'
            sleep 20
        }
    }

    if(util.validateStage('rest'))
    {
        stage('rest') {
            bat 'curl -X GET "http://localhost:8082/rest/mscovid/test?msg=testing"'
        }
    }

}

return this;
