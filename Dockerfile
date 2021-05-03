FROM openjdk:8
EXPOSE 8081
ADD target/tester-1.0-SNAPSHOT.jar tester-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","tester-1.0-SNAPSHOT.jar"]