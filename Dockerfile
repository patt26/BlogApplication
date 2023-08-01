FROM eclipse-temurin:17

LABEL mentainer="kharchepratik123@gmail.com"

WORKDIR /app

COPY target/BlogApplication-0.0.1-SNAPSHOT.jar /app/springboot-docker-blogapplication.jar

ENTRYPOINT["java","-jar","springboot-docker-blogapplication.jar"]