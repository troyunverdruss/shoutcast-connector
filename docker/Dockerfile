FROM java:8
WORKDIR /
COPY target/shoutcast-connector-0.0.1-SNAPSHOT.jar /opt/shoutcast-connector.jar
COPY target/shoutcast-connector-config.yml /opt/shoutcast-connector-config.yml
CMD ["/usr/bin/java", "-jar", "/opt/shoutcast-connector.jar", "server", "/opt/shoutcast-connector-config.yml"]