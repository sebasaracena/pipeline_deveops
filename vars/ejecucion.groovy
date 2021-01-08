def call(){


    pipeline {
        agent any

        parameters {
            choice(
                name:'compileTool',
                choices: ['maven', 'gradle'],
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
                      sh 'env'            
                        switch(params.compileTool)
                        {
                            case 'maven':
                                maven.call()
                            break;
                            case 'gradle':
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

