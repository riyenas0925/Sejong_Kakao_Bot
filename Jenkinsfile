pipeline {
  agent any
  stages {
    stage('Build & Test') {
      steps {
        sh '''chmod +x ./gradlew
./gradlew clean build'''
        stash(name: 'build-test-artifacts', includes: '**/build/test-results/test/TEST-*.xml,build/libs/*.war')
      }
    }

    stage('Report & Publish') {
      steps {
        unstash 'build-test-artifacts'
        junit '**/build/test-results/test/TEST-*.xml'
        archiveArtifacts(artifacts: 'build/libs/*.war', onlyIfSuccessful: true)
      }
    }

    stage('Deploy') {
      steps {
        sh 'echo deploy!!!'
      }
    }

  }
}