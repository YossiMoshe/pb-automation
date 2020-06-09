#!/bin/sh

JAR_PATH=`find /opt/app/ -name '*.jar' | tail -n 1`

echo "JAR_PATH - $JAR_PATH"

echo "ENVIRONMENT - $1"

echo "SELENIUM_HOST - $2"

echo "TEST_SUITE - $3"

#export DISPLAY=:20
#Xvfb :20 -screen 0 1366x768x16 &

java -Dselenide.debug=false -Denv=$1 -DisLocal=false -DseleniumHost=$2  -jar $JAR_PATH $3
