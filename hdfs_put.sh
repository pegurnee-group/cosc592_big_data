#!/bin/bash

for file in $(find ./code -iname '*.java'); do
  hdfs dfs -put $file source_code
done
