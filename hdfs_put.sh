#!/bin/bash

for file in $(find ./code -iname '*.java' -or -iname '*.c' -or -iname '*.js'); do
  hdfs dfs -put $file source_code
done
