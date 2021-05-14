pipeline {
    agent any
    environment {
        imageName = ""
    }
    stages {
        stage('Step 1 Git') {
            steps {
                git 'https://github.com/ParijatMoulik/CollabWrite.git'

            }
        }
         stage('Step 2 Maven') {
            steps {

                 sh 'mvn clean install -Pproduction'

            }
        }
         stage('Step 3 Test')
         {
             steps {

                 sh 'mvn test'
             }
             post{
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
             }
         }

         stage('Step 4 Docker Image')
          {
              steps {
                  script {
                    imageName = docker.build "spefinalproject/collabwrite:latest"
                    }
              }
          }

         stage('Step 5 Push Docker Image')
        {
            steps {
                script{
                  docker.withRegistry('', 'jenkins-docker') {
                       imageName.push()
                  }
                }
            }
        }
        stage('Step 6 Ansible'){
            steps{
                ansiblePlaybook becomeUser: null, colorized: true, disableHostKeyChecking: true, installation: 'Ansible', inventory: 'deploy-docker/inventory', playbook: 'deploy-docker/deploy-image.yml', sudoUser: null
                }

        }
    }
}

