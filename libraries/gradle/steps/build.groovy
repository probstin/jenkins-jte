void call() {
    
    String image = "gradle:" + config.image_tag ?: "jdk17-alpine"
    
    podTemplate(containers: [containerTemplate(name: 'gradle', image: image, command: 'sleep', args: '99d')]) {
        node(POD_LABEL) {
            stage('Gradle:Build') {
                container('gradle') {
                  sh 'gradle -version'
                }
            }
        }  
    } 
    
}
