FROM openjdk:17-jdk-alpine
RUN apk add --no-cache maven
WORKDIR /api
COPY . /api
RUN mvn clean package -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "/api/target/ccx-0.0.1-SNAPSHOT.jar"]