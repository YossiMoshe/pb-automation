node('spot-linux'){
	
  stage('Checkout') {
    checkout scm
    sh "echo 'pb-automation' > pb-service-name"
  }

  stage('Setup') {
    sh 'git describe --always > result'
    def commit = readFile('result').trim()
    env.BUILD_NAME = '#' + env.BUILD_NUMBER + ' - ' + commit
    currentBuild.displayName = env.BUILD_NAME
    currentBuild.description = 'Build number, git tag'
  }

  stage('Prebuild'){
    def service_name = readFile('pb-service-name').trim()

    sh "aws ecr get-login --no-include-email | bash"
  
    sh "echo ${env.WORKSPACE}"
    
    def env_file_source = "s3://pb-micro-services-static/${service_name}"
    sh "aws s3 cp ${env_file_source}/.env-production .env"

    sh "docker build -f Dockerfile-build -t ${service_name}-build:latest . && docker run -v '${env.WORKSPACE}/dist:/opt/pb-automation/dist'  ${service_name}-build:latest" 

  }

  stage('Build') {
    def service_name = readFile('pb-service-name').trim()
    
    sh "docker build -f Dockerfile-distribute . -t ${service_name}-app:latest"
  }

  stage('Test') {
    sh "echo TODO: add tests"
  }

  stage('Deliver') {
    def branch_name_tag = BRANCH_NAME.replaceAll('[\\/-]','_')
    def commit = readFile('result').trim()
    def date = new Date().format( 'dd.MM.yyyy' )
    def service_name = readFile('pb-service-name').trim()
    sh "docker tag ${service_name}-app:latest 475376316304.dkr.ecr.us-east-1.amazonaws.com/${service_name}:${branch_name_tag}_${commit}_${date}"
    sh "docker push 475376316304.dkr.ecr.us-east-1.amazonaws.com/${service_name}:${branch_name_tag}_${commit}_${date}"
    if (branch_name_tag == 'master') {
      sh "docker tag ${service_name}-app:latest 475376316304.dkr.ecr.us-east-1.amazonaws.com/${service_name}:latest"
      sh "docker push 475376316304.dkr.ecr.us-east-1.amazonaws.com/${service_name}:latest"	    
    }    
  }

  stage('Update Suite Map'){

    def suite_map_destination = "s3://playbuzz-automation/suite_map.json"
    def suite_map_local_path = "suite_map.json"

    sh "if [ -f ${suite_map_local_path} ]; then echo 'Found map file, sending to S3....'; aws s3 cp ${suite_map_local_path} ${suite_map_destination} --acl public-read; fi"
 
 
    // def file = new File(suite_map_local_path)

    // if (file.exists()) {
    //   echo "Found map file, sending to S3...."
    //   sh "aws s3 cp ${suite_map_local_path} ${suite_map_destination}"
    // } else {
    //   echo "Could not find suite map file"
    //   sh "ls -l"
    // }
  }
}
