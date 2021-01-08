def call(){
    
    if(env.GIT_BRANCH=='origin/release-v0.01'){
        figlet env.GIT_BRANCH
        mavencd.call()
    }
    else if(env.GIT_BRANCH=='feature-librery' || env.GIT_BRANCH=='develop' || env.GIT_BRANCH=='feature-dir-gradle' || env.GIT_BRANCH=='feature-gradle' ){
      figlet env.GIT_BRANCH
      mavenci.call()

    } 

  

}

return this;