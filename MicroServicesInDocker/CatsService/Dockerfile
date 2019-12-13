FROM java:8
#ADD target/cat-0.1.jar cat-0.1.jar
#RUN mvn clean install
COPY target/cat-0.1.jar cat-0.1.jar
#CMD ["cd", "CatsService", "&&", "java", "-jar", "*.jar"]
ENTRYPOINT ["java", "-jar", "cat-0.1.jar"]
