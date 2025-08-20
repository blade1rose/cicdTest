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
                checkout scmGit(branches: [[name: 'refs/tags/${tag}']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/blade1rose/cicdTest.git']])
            }
        }
        stage('通过Maven构建项目') {
            steps {
                sh '/usr/local/maven/bin/mvn clean package -DskipTests'
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
        stage('远程执行k8s-master的kubectl命令') {
            steps {
                sh 'ssh root@192.168.157.143 kubectl apply -f pipeline.yml'
            }
        }
        stage('将yml文件传到k8s-master上') {
            steps {
                sshPublisher(publishers: [sshPublisherDesc(configName: 'k8s', 
transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: '', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: 'pipeline.yml')], 
usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
            }
        }
    }
    post {
        success {
            dingtalk (
                robot: 'Jenkins-DingDing',
                type: 'MARKDOWN',
                title: "success: ${JOB_NAME}",
                text: ["- 构建成功: ${JOB_NAME}! \n- 版本: ${tag} \n- 持续时间: ${currentBuild.durationString}"]
            )
        }
        failure {
            dingtalk (
                robot: 'Jenkins-DingDing',
                type: 'MARKDOWN',
                title: "success: ${JOB_NAME}",
                text: ["- 构建失败: ${JOB_NAME}! \n- 版本: ${tag} \n- 持续时间: ${currentBuild.durationString}"]
            )
        }
    }
}
