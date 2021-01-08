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
  stage('nexus'){
	    env.STAGE = 'nexus'
	        nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: ‘ejemplo-maven-gradle', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: 'C:\Users\usuario}\.jenkins\workspace\gradle-multibranch_develop\build\DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
	}

}

return this;