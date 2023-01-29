FROM maven:3.6.0-jdk-8-slim as build
#FROM maven:3.8.6-amazoncorretto-17 as build

WORKDIR /app

COPY src /app/src

COPY pom.xml /app

RUN mvn -f /app/pom.xml clean package -Dspring.profiles.active=prod

FROM apline:3.13

ENV MYSOL_HOST  110.40.230.65
ENV MYSOL_USER_NAME root
ENV MYSOL_PASSWORD 123456
ENV DATABASE_NAME jilijili-music

RUN  apk add --update --on-cache openjdk18-jre-base \
     && rm -f /var/cache/apk/*

WORKDIR /app

COPY --from=build /app/target/wang-jilijili-0.0.1-SNAPSHOT.jar    .

EXPOSE 80

CMD ["java","-jar","/app/wang-jilijili-0.0.1-SNAPSHOT.jar"]
