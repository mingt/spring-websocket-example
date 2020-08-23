#!/bin/sh

# build_type 有 release 或 test 等

cd base-common; ./gradlew clean build -P build_type=release; cd ..
cd websocket-teaching; ./gradlew clean build -P build_type=release; cd ..
