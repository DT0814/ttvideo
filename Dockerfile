FROM java:8
VOLUME /temp
COPY ttvideo-0.0.2-SNAPSHOT.jar ttvideo.jar
ENTRYPOINT [ "java","-agentlib:jdwp=transport=dt_socket,server=y,address=8000,suspend=n","-Djava.security.egd=file:/dev/./urandom","-jar","/ttvideo.jar" ]
