pipeline {
    //agent any
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
        stage('Build image') {
            steps {
               dockerImage = docker.build("monishavasu/my-react-app:latest")
            }
        }
        stage('Push image') {
            steps{
                withDockerRegistry([ credentialsId: "dockerhubaccount", url: "" ]) {
                dockerImage.push()
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


