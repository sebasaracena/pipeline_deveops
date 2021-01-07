def call(){
    
    if(util.validateStage('compile'))
    {
        stage('compile') {
            bat './mvnw.cmd clean compile -e'
        }
    }

    if(util.validateStage('test'))
    {
        stage('test'){
            bat './mvnw.cmd clean test -e'
        }
    }

    if(util.validateStage('jar'))
    {
        stage('jar'){
            bat './mvnw.cmd clean package -e'
        }
    }
     if(util.validateStage('run'))
    {
        stage('run'){
           sh 'JENKINS_NODE_COOKIE=dontKillMe nohup bash mvnw spring-boot:run &'
        }
    }

    
  

}

return this;