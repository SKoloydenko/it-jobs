FROM eclipse-temurin:17-jdk-jammy AS build
RUN mkdir -p /app
WORKDIR /app
COPY . /app
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre-jammy
ARG JAR_FILE=/app/target/*.jar
COPY --from=build $JAR_FILE /app/runner.jar
EXPOSE 8080
ENTRYPOINT java -jar /app/runner.jar
