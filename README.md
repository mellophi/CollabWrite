# CollabWrite

CollabWrite enables many users to write poems, stories, events, songs etc.collaboratively. It can be used by anyone to share their creation or thoughts in a social media kind of platform where other users can also contribute to his/her thoughts.Due to its collaborative feature, our project will provide the right platform for the writers, music composers etc. to do their job in a more effective manner and with better user experience.


## Features:

- User registration and login.
- Users can write about anything.
- Users can decide whether to make it collaborative or not.
- User can see his/her posts and posts where he/she is collaborating.
- Searching anyone’s post.


## Tools:

The front end of the project is handled by “Vaadin” Framework. The middle layer is built on “SpringBoot” Framework and it communicates with the MySQL database in the back-end.

Our project also uses DevOps software development methodology to automate the stages of application development lifecycle, i.e., development, testing, deployment, and monitoring. 

The various DevOps tools used in this project are listed below:
- Continuous Development (SCM): GitHub
- Continuous Build: Maven
- Continuous Testing: JUnit
- Continuous Integration: Jenkins
- Containerization: Dockerfile
- Continuous Deployment: Ansible
- Continuous Monitoring: Elasticsearch-Logstash-Kibana


## Install:

### Docker Image:

The docker image is available in [Docker Hub](https://hub.docker.com/repository/docker/spefinalproject/collabwrite)

To try out the CollabWrite container run the following command.

    docker run -it 8081:8081 spefinalprojecet/tester:latest

### Run it Locally:

-Installing openjdk 11 in Ubuntu

		$ sudo apt-get install openjdk-11-jdk

-Installing maven in Ubuntu

		$ sudo apt update
		$ sudo apt install maven

-Build the JAR file for the project
		
		mvn clean install -Pproduction

-Run the JAR file
		
		java -jar target/collabwrite-1.0-SNAPSHOT.jar 

