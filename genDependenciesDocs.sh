#!/bin/sh

echo '----- starting to generate `gradlew dependencies` to docs/dependencies/ ---- '

# cd api-gateway; ./gradlew dependencies > deps.api-gateway.txt; mv -f deps.* ../docs/dependencies/; cd ..
# cd config-server; ./gradlew dependencies > deps.config-server.txt; mv -f deps.* ../docs/dependencies/; cd ..
# cd webservice-registry; ./gradlew dependencies > deps.webservice-registry.txt; mv -f deps.* ../docs/dependencies/; cd ..
# cd zipkin-server; ./gradlew dependencies > deps.zipkin-server.txt ; mv -f deps.* ../docs/dependencies/; cd ..

cd base-common; ./gradlew dependencies > deps.base-common.txt; mv -f deps.* ../docs/dependencies/; cd ..
# cd auth-server; ./gradlew dependencies > deps.auth-server.txt; mv -f deps.* ../docs/dependencies/; cd ..
# cd user-webservice; ./gradlew dependencies > deps.user-webservice.txt; mv -f deps.* ../docs/dependencies/; cd ..
# cd trace-service; ./gradlew dependencies > deps.trace-service.txt; mv -f deps.* ../docs/dependencies/; cd ..
cd websocket-teaching; ./gradlew dependencies > deps.websocket-teaching.txt; mv -f deps.* ../docs/dependencies/; cd ..

echo 'DONE!'
