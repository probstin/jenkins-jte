void call() {
    
    String imageTag = config.image_tag ?: "jdk17-alpine"
    
    podTemplate(
        containers: [containerTemplate(name: 'gradle', image: "gradle:${imageTag}", command: 'sleep', args: '99d', ttyEnabled: true)],
    ) {
        node(POD_LABEL) {
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
