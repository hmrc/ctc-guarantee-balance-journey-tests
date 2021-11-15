#!/bin/bash -e

# Scalafmt checks have been separated from the test command to avoid OutOfMemoryError in Jenkins
sbt scalafmtCheckAll scalafmtSbtCheck

sbt -Dbrowser=chrome -Denvironment=local "testOnly ctc.utils.runners.Runner_WIP"
