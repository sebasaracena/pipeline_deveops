def call(){
    
   if(util.validateStage('Downloadnexus')){
      stage('Downloadnexus') {
       env.STAGE = 'Downloadnexus'
       figlet env.STAGE
       sh 'curl http://35.199.77.109:8081/repository/realese-v0.1.0/com/devopusach2020/DevOpsUsach2020/1.0.0/DevOpsUsach2020-1.0.0.jar --output DevOpsUsach2020-1.0.0.jar'
      }
   }

    if(util.validateStage('runDownloadjar')){
     stage('runDownloadjar') {
    env.STAGE = 'runDownloadjar'
    figlet env.STAGE
    echo 'Esperando a que termine la descarga'
	sleep(time: 10, unit: "SECONDS")
     sh 'JENKINS_NODE_COOKIE=dontKillMe nohup start java -jar DevOpsUsach2020-1.0.0.jar &'
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
    if(util.validateStage('nexuscd'))
    { 
  stage('nexuscd'){
	    env.STAGE = 'nexuscd'
	    figlet env.STAGE
        nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'ejemplo-maven-gradle', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: "C:\\\\Users\\\\usuario}\\\\.jenkins\\\\workspace\\\\ipeline-cicd_pipeline-ci_develop\\\\build\\\\DevOpsUsach2020-0.0.1.jar"]], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '1.0.1']]]
	}
} 


}

return this;