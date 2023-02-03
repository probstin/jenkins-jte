void call(){
    String imageVersion = config.image_version ?: "gradle:jdk17-alpine"
    
    podTemplate(containers: [
        containerTemplate(name: 'gradle', image: imageVersion, command: 'sleep', args: '99d')
    ]) {
        node(POD_LABEL) {
            stage('Gradle:Build') {
                container('gradle') {
                  sh 'gradle -version'
                }
            }
        }  
    }
        
}
