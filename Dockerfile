# which version
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
ARG sonarurl
ARG sonartoken

# set work dir
WORKDIR /app

# copy files
COPY pom.xml .
COPY src ./src
RUN mvn clean verify package sonar:sonar -Dsonar.projectKey=1sm1rk_hlwiki -Dsonar.projectName='1sm1rk_hlwiki' -Dsonar.host.url=${sonarurl} -Dsonar.token=${sonartoken}

# build concrete image
FROM eclipse-temurin:21

# set the same workdir
WORKDIR /app

# copy jar from previous stage
COPY --from=build /app/target/*.jar app.jar

# eXpose port
# EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
