FROM maven:3.5.3-jdk-8-alpine as target
WORKDIR /app/EurekaService
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /build/src/
RUN mvn package

FROM maven:3.6.1-jdk-8-alpine

WORKDIR /app/EurekaService

COPY . /app
RUN mvn install

EXPOSE 9000

CMD ["cd", "EurekaService", "&&", "mvn", "spring-boot:run"]
COPY --from=target /build/target/*.jar /app/EurekaService