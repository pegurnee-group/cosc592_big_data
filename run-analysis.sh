#!/bin/bash

#export JAVA_HOME=C:/Program Files/Java/jdk1.8.0_65
export PATH=${JAVA_HOME}/bin:${PATH}
export HADOOP_CLASSPATH=${JAVA_HOME}/lib/tools.jar

hadoop com.sun.tools.javac.Main project/src/src/*.java
jar cf GitHubbing.jar -C project/src .

hdfs dfs -rm -r -f java_out
hadoop jar GitHubbing.jar src/gitHubbing /user/cloudera/source_code_test /user/cloudera/java_out
