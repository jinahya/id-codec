#!/bin/sh

uuid=`uuidgen`
echo uuid: $uuid
encoded=$(mvn --quiet compile exec:java -Dexec.mainClass=com.github.jinahya.codec.IdEncoder -Dexec.args="$uuid")
echo encoded: $encoded
decoded=$(mvn --quiet compile exec:java -Dexec.mainClass=com.github.jinahya.codec.IdDecoder -Dexec.args="$encoded")
echo decoded: $decoded

long=`od -N 8 -t dL -An /dev/urandom | tr -d " "`
echo long: $long
encoded=$(mvn --quiet compile exec:java -Dexec.mainClass=com.github.jinahya.codec.IdEncoder -Dexec.args="$long")
echo encoded: $encoded
decoded=$(mvn --quiet compile exec:java -Dexec.mainClass=com.github.jinahya.codec.IdDecoder -Dexec.args="$encoded")
echo decoded: $decoded

