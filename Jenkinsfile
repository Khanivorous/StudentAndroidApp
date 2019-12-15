pipeline {
  agent any
  stages {
    stage('Compile') {
      steps {
        sh './gradlew compileDebugSources'
      }
    }
    stage('Unit test') {
      steps {
        sh './gradlew testDebugUnitTest jacocoTestReport'
        junit 'app/build/test-results/testDebugUnitTest/TEST-*.xml'
        jacoco(exclusionPattern: 'app/src/test*,app/src/androidTest*', classPattern: 'app/build/tmp/kotlin-classes/debug', execPattern: 'app/build/jacoco/*.exec', sourcePattern: 'app/src/main/java')
      }
    }
    stage('Build APK') {
      steps {
        sh './gradlew assembleDebug'
        archiveArtifacts 'app/build/outputs/apk/debug/*.apk'
      }
    }

    stage('email results') {
      steps {
        emailext(body: 'Job ${PROJECT_NAME} build ${BUILD_NUMBER} \n\nMore info at: ${BUILD_URL}', subject: 'Jenkins Build $PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!', to: 'taherul_khan@hotmail.co.uk')
      }
     }
  }
}