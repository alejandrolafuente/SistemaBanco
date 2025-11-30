# FROM ubuntu:latest AS build

# RUN apt-get update
# RUN apt-get install openjdk-21-jdk -y
# COPY . .

# RUN apt-get install maven -y
# RUN mvn clean install 

# FROM eclipse-temurin:21-jdk

# EXPOSE 8080

# COPY --from=build /target/bankserver-0.0.1-SNAPSHOT.jar app.jar

# ENTRYPOINT [ "java", "-jar", "app.jar" ]
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests  

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/bankserver-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]