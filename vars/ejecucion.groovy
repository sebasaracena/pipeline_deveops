def call(){

    pipeline {
        agent any

        parameters {
            choice(
                name:'compileTool',
                choices: ['Maven', 'Gradle'],
                description: 'Seleccione herramienta de compilacion'
            )
        }

        stages {
            stage('pipeline') {
                steps {
                    script {

                        params.compileTool

                        switch(params.compileTool)
                        {
                            case 'Maven':
                                //def ejecucion = load 'maven.groovy'
                                maven.call()
                            break;
                            case 'Gradle':
                                //def ejecucion = load 'gradle.groovy'
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