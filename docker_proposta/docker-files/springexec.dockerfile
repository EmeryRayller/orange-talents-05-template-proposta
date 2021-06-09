# Base Image ubuntu
FROM ubuntu:18.04

# Installing dependencies
RUN apt-get update
RUN apt-get install -y openjdk-11-jdk maven 

# coping the required application files
COPY ../../ /spring_boot_app
WORKDIR ../../spring_boot_app

# building the spring boot app
#RUN mvn clean install

WORKDIR /spring_boot_app

# running the spring boot app
CMD ["java", "-jar", "Spring_Boot_JPA-0.0.1-SNAPSHOT.jar"]