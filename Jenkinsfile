#!groovy

pipeline {
    agent any
    tools {
        jdk 'openjdk-171'
        maven 'maven-387'
      }
    stages {
        stage('Log JAVA_HOME') {
          steps {
            sh 'echo $JAVA_HOME'
          }
        }
        stage('Checkout') {
            steps {
                git branch: 'main', url:'https://github.com/GarethPark/gptheme'
            }
        }
        stage('Build') {
            steps {
                sh './mvnw clean package'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t your_username/your_spring_boot_app .'
            }
        }
        stage('Push to Docker Hub') {
            steps {
                withDockerRegistry([credentialsId: 'dockerhub', url: 'https://index.docker.io/v1/']) {
                    sh 'docker push your_username/your_spring_boot_app'
                }
            }
        }
    }
}