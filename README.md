# Todo Application Back-End
[![CircleCI](https://circleci.com/gh/gaetanBloch/todo-spring-back.svg?style=svg)](https://circleci.com/gh/gaetanBloch/todo-spring-back)
[![codecov](https://codecov.io/gh/gaetanBloch/todo-spring-back/branch/master/graph/badge.svg)](https://codecov.io/gh/gaetanBloch/todo-spring-back)

This application was built using [Java 11](https://jdk.java.net/11/), [Spring 5](https://spring.io/), [Spring Boot 2](https://spring.io/projects/spring-boot), [Hibernate ORM](https://hibernate.org/) and [H2 Database](https://www.h2database.com/html/main.html). It is a REST API back-end for a Todo List Application.

**The Full Stack Application with Angular front-end was deployed on Heroku, to access it, click [here](https://gbloch-todo-list.herokuapp.com/).**

To see the front-end of this application implemented with Angular, see [here](https://github.com/gaetanBloch/todo-angular-front).

## Running Application Process on your computer

1. Download the application by clicking [here](https://github.com/gaetanBloch/todo-spring-back/archive/master.zip)
2. Unzip the application
3. Dowload and Install [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
4. Set the environment variable `JAVA_HOME` to the root of your Java 11 jdk
5. Download and Install [Maven](https://maven.apache.org/download.cgi)
6. Open a terminal
7. Move to the root of the application
8. Run `mvn spring-boot:run`
9. Navigate to http://localhost:8080 with your favourite browser

## Application

### REST API Documentation generated by Swagger

![REST API Documentation](https://i.imgur.com/z2cq0GJ.png)
