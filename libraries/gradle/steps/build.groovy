void call(){
    podTemplate(yaml: '''
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
    ''') { }
}
