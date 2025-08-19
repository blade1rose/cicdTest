pipeline {
    agent any
    
    environment {
        key = 'value'
    }

    stages {
        stage('拉取git仓库代码') {
            steps {
                echo '拉取git仓库代码 - SUCCESS'
            }
        }
        stage('通过Maven构建项目') {
            steps {
                echo '通过Maven构建项目 - SUCCESS'
            }
        }
        stage('通过docker制作自定义镜像') {
            steps {
                echo '通过docker制作自定义镜像 - SUCCESS'
            }
        }
        stage('将自定义镜像推送到Harbor') {
            steps {
                echo '将自定义镜推送到Harbor - SUCCESS'
            }
        }
        stage('通过Publish Over SSH通知目标服务器') {
            steps {
                echo '通过Publish Over SSH通知目标服务器 - SUCCESS'
            }
        }
    }
}
