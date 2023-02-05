void call() {
    
    def imageTag = config.image_tag ?: "jdk17-alpine"
    
    podTemplate(
        containers: [containerTemplate(name: 'gradle', image: "gradle:${imageTag}", command: 'sleep', args: '99d', ttyEnabled: true)],
    ) {
        node(POD_LABEL) {
            
            def myRepo = checkout scm
            def gitCommit = myRepo.GIT_COMMIT
            def gitBranch = myRepo.GIT_BRANCH
            
            stage('Gradle:Build') {
                container('gradle') {
                    sh """
                        pwd
                        gradle test
                        """
                }
            }
        }  
    } 
    
}
