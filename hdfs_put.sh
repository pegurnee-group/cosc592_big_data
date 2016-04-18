#!/bin/bash

SOURCE='source_code'

if ! hdfs dfs -test -d $SOURCE; then
  hdfs dfs -mkdir $SOURCE

for file in $(find ./code -iname '*.java' -or -iname '*.c' -or -iname '*.js'); do
  REPO_NUM=$( echo $file | awk -F'[/]' '{print $4}' )
  if ! hdfs dfs -test -d $SOURCE/$REPO_NUM; then
    hdfs dfs -mkdir $SOURCE/$REPO_NUM
  fi
  hdfs dfs -put $file $SOURCE/$REPO_NUM/
done
