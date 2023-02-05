void call() {
    
    def label = "worker-${UUID.randomUUID().toString()}"
    def imageTag = config.image_tag ?: "jdk17-alpine"
    
    podTemplate(
        label: label,
        containers: [containerTemplate(name: 'gradle', image: "gradle:${imageTag}", command: 'sleep', args: '99d', ttyEnabled: true)],
        volumes: [hostPathVolume(mountPath: '/home/gradle/.gradle', hostPath: '/tmp/jenkins/.gradle')]
    ) {
        node(label) {
            
            def myRepo = checkout scm
            def gitCommit = myRepo.GIT_COMMIT
            def gitBranch = myRepo.GIT_BRANCH
            
            stage('Gradle:Build') {
                container('gradle') {
                    sh """
                        pwd
                        echo "GIT_BRANCH=${gitBranch}" >> /etc/environment
                        echo "GIT_COMMIT=${gitCommit}" >> /etc/environment
                        gradle build
                    """
                }
            }
        }  
    } 
    
}
