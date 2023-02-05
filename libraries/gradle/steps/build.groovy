void call() {
    
    String imageTag = config.image_tag ?: "jdk17-alpine"
    
    podTemplate(
        containers: [containerTemplate(name: 'gradle', image: "gradle:${imageTag}", command: 'sleep', args: '99d')],
    ) {
        node(POD_LABEL) {
            stage('Gradle:Build') {
                container('gradle') {
                    sh 'gradle -version'
                    sh 'ls /home/jenkins/agent'
                }
            }
        }  
    } 
    
}
