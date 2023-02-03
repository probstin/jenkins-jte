void call(){
    podTemplate(yaml '''
        apiVersion: v1
        kind: Pod
        spec:
            containers:
            - name: gradle
              image: gradle:jdk17-alpine
              command:
              - sleep
              args:
              - 99d
    ''') {
        
        node(POD_LABEL) {
            stage('Gradle:Build') {
            println "build from the gradle library" 

            container('gradle') {
              sh 'gradle -version'
            }
            
            }
        }
    }
        
}
