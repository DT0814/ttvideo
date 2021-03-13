FROM java:8
VOLUME /temp
ADD  ttvideo-0.0.2-SNAPSHOT.jar ttvideo-0.0.2-SNAPSHOT.jar
RUN bash -c 'touch /ttvideo-0.0.2-SNAPSHOT.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Xmx1024m","-Xms1024m","/ttvideo-0.0.2-SNAPSHOT.jar"]
