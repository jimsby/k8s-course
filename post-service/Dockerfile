FROM maven:3.8.7-eclipse-temurin-19-alpine AS builder

# add pom.xml and source code
ADD ./pom.xml pom.xml
ADD ./src src/

# package jar
RUN mvn clean package -Dmaven.test.skip=true

# Second stage: minimal runtime environment
FROM eclipse-temurin:19-jre-alpine

# copy jar from the first stage
COPY --from=builder target/post-service-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]