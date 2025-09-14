#!/bin/sh

echo "##################################################"
echo "# http://localhost:8080"
echo "##################################################"

./gradlew clean
./gradlew bootRun
