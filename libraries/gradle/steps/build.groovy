void call(){
    agent {
        kubernetes {
          yaml '''
            apiVersion: v1
            kind: Pod
            spec:
              containers:
              - name: gradle
                image: gradle:jdk17-alpine
                command:
                - cat
                tty: true
            '''
        }
    }
    
    stage('Gradle:Build') {
        println "build from the gradle library" 

        steps {
            container('gradle') {
              sh 'gradle -version'
            }
        }
    }
}
