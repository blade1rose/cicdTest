pipeline {
    agent any
    
    environment {
        harborUser = 'admin'
        harborPasswd = 'Harbor12345'
        harborAddress = '192.168.157.136:80'
        harborRepo = 'repo'
    }

    stages {
        stage('拉取git仓库代码') {
            steps {
                checkout scmGit(branches: [[name: '${tag}']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/blade1rose/cicdTest.git']])
            }
        }
        stage('通过Maven构建项目') {
            steps {
                sh '/usr/local/maven/bin/mvn clean package-DskipTests'
            }
        }
        stage('通过docker制作自定义镜像') {
            steps {
                sh '''mv ./target/*.jar ./docker/
docker build -t ${JOB_NAME}:${tag} ./docker/'''
            }
        }
        stage('将自定义镜像推送到Harbor') {
            steps {
                sh '''docker login -u ${harborUser} -p ${harborPasswd} ${harborAddress}
docker tag ${JOB_NAME}:${tag} ${harborAddress}/${harborRepo}/${JOB_NAME}:${tag}
docker push ${harborAddress}/${harborRepo}/${JOB_NAME}:${tag}'''
            }
        }
        stage('通过Publish Over SSH通知目标服务器') {
            steps {
                echo '通过Publish Over SSH通知目标服务器 - SUCCESS'
            }
        }
    }
}
