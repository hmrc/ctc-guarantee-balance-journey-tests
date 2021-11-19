#!/usr/bin/env bash

sbt -Dbrowser=remote-chrome -Denvironment=local "testOnly ctc.utils.runners.Runner"
