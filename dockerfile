FROM openjdk:17-oracle
MAINTAINER baeldung.com
COPY build/libs/studentserver-0.0.1-SNAPSHOT.jar studentserver-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/studentserver-0.0.1-SNAPSHOT.jar"]