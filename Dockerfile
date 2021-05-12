#FROM openjdk:8
#EXPOSE 8081
#ADD target/tester-1.0-SNAPSHOT.jar tester-1.0-SNAPSHOT.jar
#ENTRYPOINT ["java","-jar","tester-1.0-SNAPSHOT.jar"]

FROM openjdk:8
WORKDIR /
ADD target/collabwrite-1.0-SNAPSHOT.jar app.jar
RUN useradd -m myuser
USER myuser
EXPOSE 8081
CMD java -jar -Dspring.profiles.active=prod app.jar