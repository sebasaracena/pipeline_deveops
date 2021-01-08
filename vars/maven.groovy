def call(){
    figlet env.GIT_BRANCH
    if(env.GIT_BRANCH=='origin/release-v0.01'){
        echo 'buena es de la rama release'
    }
    else if(env.GIT_BRANCH=='feature-librery' || env.GIT_BRANCH=='develop'){
       echo 'no es de la rama realease'

    } 

  

}

return this;