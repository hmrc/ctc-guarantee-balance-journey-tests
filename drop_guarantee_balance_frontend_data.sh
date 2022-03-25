#!/usr/bin/env bash
{
  sleep 1
  echo "use ctc-guarantee-balance-frontend"
  echo "db['user-answers'].drop()"
} | mongo
