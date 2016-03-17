# shoutcast-connector

This project is available as a precompiled Docker container, see the docker/run.sh script for that.

This is a basic web servlet based on Dropwizard that interacts with the shoutcast.com site. Here's the API in a nutshell
 * /stations (returns 10 stations by default)
 * /stations?count={count} (up to the max returned by shoutcast)
 * /stations/{id}/tune (will redirect to the stream's URL)
 * /stations/{id}/stream (will proxy the stream data if possible)
 * /tune?station={id}  (same as /stations/{id}/tune)
 
 
 To build everything, run ./build.sh
 
 To build just the jar, run mvn clean package
