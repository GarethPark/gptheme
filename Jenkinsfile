pipeline {
     agent {
         docker {
             image 'maven:3.6.3-jdk-8'
             args '-v /root/.m2:/root/.m2'
         }
     }
     stages {
         stage('Clone repository') {
             steps {
                 git 'https://github.com/GarethPark/gptheme.git'
             }
         }
         stage('Build with Maven') {
             steps {
                 sh 'mvn -B -DskipTests clean package'
             }
         }
         stage('Build and Push Docker Image') {
             steps {
                 sh 'docker build -t garethpark/gptheme:latest .'
                 sh 'docker push garethpark/gptheme:latest'
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


