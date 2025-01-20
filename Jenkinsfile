pipeline {
    agent any
    environment {
        GITHUB_TOKEN=credentials('github_push_token')
        SONAR_ACCOUNT=credentials('sonar_account')
        IMAGE_NAME='1sm1rk/hlwiki'
        IMAGE_VERSION='0.0.1'
    }
    stages {
        stage('Docker Build') {
           agent any
           steps {
               sh 'docker build -t $IMAGE_NAME:$IMAGE_VERSION --build-arg sonarurl=$SONAR_ACCOUNT_USR --build-arg sonartoken=$SONAR_ACCOUNT_PSW .'
           }
        }
        
        stage('tag image for github') {
            steps {
                sh 'docker tag $IMAGE_NAME:$IMAGE_VERSION ghcr.io/$IMAGE_NAME:$IMAGE_VERSION'
            }
        }
        
        stage('login to GHCR') {
            steps {
                sh 'echo $GITHUB_TOKEN_PSW | docker login -u $GITHUB_TOKEN_USR --password-stdin ghcr.io'
            }
        }
        
        stage('push image to github') {
            steps {
                sh 'docker push ghcr.io/$IMAGE_NAME:$IMAGE_VERSION'
            }
        }
    }
    post {
        always {
            sh 'docker logout'
        }
    }
}
