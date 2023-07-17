FROM amazoncorretto:17-alpine-jdk
MAINTAINER Nicode
COPY build/libs/gestion-enfermeria-0.0.1-SNAPSHOT.jar nicode-gestion-enf.jar
ENTRYPOINT ["java", "-jar", "nicode-gestion-enf.jar"]