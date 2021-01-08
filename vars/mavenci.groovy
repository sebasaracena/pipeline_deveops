def call(){
    
    if(util.validateStage('build'))
    {
        stage('build') {
            env.STAGE = 'build'
           figlet env.STAGE
            sh './mvnw.cmd clean compile -e'
        }
    }

    if(util.validateStage('test'))
    {
        stage('test'){
            env.STAGE = 'test'
            figlet env.STAGE
            bat './mvnw.cmd clean test -e'
        }
    }

   if(util.validateStage('sonar')) 
   {
   stage('sonar') {
       env.STAGE = 'sonar'
       figlet env.STAGE
        
    withSonarQubeEnv(installationName: 'SonarQube') { 
      bat 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
    
            }
    }
  } 

    if(util.validateStage('jar'))
    {
        stage('jar'){
            env.STAGE = 'jar'
            figlet env.STAGE
            bat './mvnw.cmd clean package -e'
        }
    }

     if(util.validateStage('run'))
    {
        stage('run'){
             env.STAGE = 'run'
            figlet env.STAGE
           sh 'JENKINS_NODE_COOKIE=dontKillMe nohup start mvnw spring-boot:run &'
        }
    }

       if(util.validateStage('rest'))
    {
        stage('rest') {
            
            env.STAGE = 'rest'
            figlet env.STAGE
            sleep(time: 10, unit: "SECONDS")
            bat 'curl -X GET "http://localhost:8081/rest/mscovid/test?msg=testing"'
        }
    }

}

return this;