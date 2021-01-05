def call(){

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
                                maven.call(params.stage)
                            break;
                            case 'Gradle':
                                gradle.call(params.stage)
                            break;
                        }
                    }
                }
            }
        }
    }

}

return this;