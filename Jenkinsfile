pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/user/spring-boot-project.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Docker Build and Push') {
            steps {
                sh 'mvn docker:build'
                withDockerRegistry([credentialsId: 'dockerhub-credentials', url: 'https://index.docker.io/v1/']) {
                    sh 'docker push my-repo/spring-boot-project:latest'
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


