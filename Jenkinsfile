#!groovy

pipeline {
    agent {
        label 'maven'
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/spring-projects/spring-boot.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t my-spring-boot-app .'
            }
        }
        stage('Push Docker Image') {
            steps {
                sh 'docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'
                sh 'docker push my-spring-boot-app'
            }
        }
    }
}
