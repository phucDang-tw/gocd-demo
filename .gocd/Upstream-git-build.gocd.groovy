/*
 * This file was automatically exported by the GoCD Groovy DSL Plugin.
 */

GoCD.script {
  pipelines {
    pipeline('Upstream-git-build') {
      group = 'dummy-test'
      labelTemplate = '${COUNT} - ${gitDemo[:7]}'
      lockBehavior = 'none'
      materials {
        git('gitDemo') {
          branch = 'master'
          shallowClone = false
          url = 'https://github.com/phucDang-tw/github-action-demo.git'
        }
      }
      stages {
        stage('Build') {
          artifactCleanupProhibited = false
          cleanWorkingDir = false
          fetchMaterials = true
          approval {
          }
          jobs {
            job('print-env') {
              timeout = 0
              tasks {
                exec {
                  commandLine = ['env']
                  runIf = 'passed'
                }
              }
            }
          }
        }
        stage('Extract-build-image-info') {
          artifactCleanupProhibited = false
          cleanWorkingDir = false
          fetchMaterials = true
          approval {
          }
          jobs {
            job('git') {
              timeout = 0
              artifacts {
                build {
                  destination = 'image.info'
                  source = 'image.info'
                }
              }
              tasks {
                exec {
                  commandLine = ['ls']
                  runIf = 'passed'
                }
                exec {
                  commandLine = ['git', 'status']
                  runIf = 'passed'
                }
                exec {
                  commandLine = ['git', 'rev-parse --short HEAD']
                  runIf = 'passed'
                }
                exec {
                  commandLine = ['echo', '${git rev-parse --short HEAD} > image.info']
                  runIf = 'passed'
                }
              }
            }
          }
        }
      }
    }
  }
}

