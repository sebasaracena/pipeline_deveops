def call(){
    
    if(util.validateStage('compile'))
    {
        stage('compile') {
           echo env.BRANCH_NAME
            sh './mvnw.cmd clean compile -e'
        }
    }

    if(util.validateStage('test'))
    {
        stage('test'){
            bat './mvnw.cmd clean test -e'
        }
    }

   if(util.validateStage('sonar')) 
   {
   stage('sonar') {
        
    withSonarQubeEnv(installationName: 'SonarQube') { 
      bat 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
    
            }
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