FROM gradle:jdk17 AS builder
COPY --chown=gradle:gradle . /src
WORKDIR /src
RUN gradle build

FROM openjdk:17-jdk
COPY --from=builder /build/libs/template-1.0.0.jar /app/app.jar
WORKDIR /app
CMD ["java", "-jar", "app.jar"]
