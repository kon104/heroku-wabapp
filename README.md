# heroku-wabapp
myself apps which execute in Heroku! there are various kind of experimental apps.

## for development environment

- run the app at the localhost
	- $ mvn spring-boot:run
	- $ mvn test
	- $ mvn test -Dtest=YConnectOpenIdConfigRepositoryTest

## the steps for creating pom.xml

1. creating pom.xml with Spring Initializr.
	- Project: Maven
	- Language: Java
	- Spring Boot: 3.3.3
	- Project Metadata:
		- Group: com.herokuapp
		- Artifact: kon104.webapp
		- Package name: com.herokuapp.kon104.webapp
		- Packaging: Jar
		- Java: 21
	- Dependences:
		- Spring Web
		- Spring Boot DevTools
		- Thymeleaf
		- Spring Security
		- Lombok
2. adding the dependency to pom.xml by hand
	```
	<dependency>
		<groupId>org.springframework</groupId> 
		<artifactId>spring-aop</artifactId>
	</dependency> 
	<dependency> 
		<groupId>org.aspectj</groupId> 
		<artifactId>aspectjweaver</artifactId>
	</dependency>
	```

