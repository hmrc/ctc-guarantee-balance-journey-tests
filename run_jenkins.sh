#!/usr/bin/env bash

sbt -Dbrowser=chrome -Denvironment=local "testOnly ctc.utils.runners.Runner"
