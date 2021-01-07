def call(){
<<<<<<< HEAD

    pipeline {
        agent any

        parameters {
            choice(
                name:'compileTool',
                choices: ['Maven', 'Gradle'],
                description: 'Seleccione herramienta de compilacion'
            )
            string(
                name: 'stage',
                defaultValue: '',
                description: 'Seleccione stage a ejecutar (Dejar en blanco para ejecutar todos)'
            )
        }

        stages {
            stage('pipeline') {
                steps {
                    script {
                        
                        switch(params.compileTool)
                        {
                            case 'Maven':
                                maven.call()
                            break;
                            case 'Gradle':
                                gradle.call()
                            break;
                        }
                    }
                }
            }
        }
    }

}

return this;
=======
  
 pipeline {
    agent any

    stages {
        stage('compile') {
            steps {
                
                sh 'gradle clean build'
                
                
            }
        }
      
     
        stage('run jar') {
            steps {
              
                 sh 'JENKINS_NODE_COOKIE=dontKillMe nohup start gradlew bootRun &'
               
                
            } 
        }
        
    }
}

}

return this;
>>>>>>> bbbd4fa0ddffc8d130de95b0bf207cf7960fcdf4
