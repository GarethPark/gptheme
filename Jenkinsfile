pipeline {
    agent {
        docker {
            image 'maven:3.6.3-jdk-8'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url:'https://github.com/GarethPark/gptheme.git'
            }
        }
        stage('Build') {
            steps {
                withMaven(maven: 'mvn') {
                    sh 'mvn clean package'
                }
            }
        }
        stage('Docker Build and Push') {
            steps {
                withMaven(maven: 'mvn') {
                    sh 'mvn docker:build'

                    withDockerRegistry([serverAddress: "https://index.docker.io/v1/",
                                          username: "garethpark",
                                          password: "Everton1995!"]) {
                        sh 'docker push my-repo/spring-boot-project:latest'
                    }
                }
            }
        }
        stage('Deploy to OpenShift') {
            steps {
                sh 'oc login https://openshift.example.com:8443 --token=my-token'
                sh 'oc new-app my-repo/spring-boot-project:latest'
            }
        }
    }
}


