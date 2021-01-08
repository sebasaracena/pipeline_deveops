def call(){
    figlet env.GIT_BRANCH
    if(env.GIT_BRANCH=='release-v0.01'){
        figlet 'buena es de la rama release'
    }
    else if(env.GIT_BRANCH=='feature*' || env.GIT_BRANCH=='develop'){
       figlet 'no es de la rama realease'

    } 

  

}

return this;