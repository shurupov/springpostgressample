# springpostgressample

This application was created for testing Spring Boot and Spring Data JPA

# Run application and build an executable JAR

You should have java 8 and docker installed and configured.

If you execute this the first time you should execute command

``./postgres/inti.sh && ./mvnw spring-boot:run``

The next times you should execute

``./postgres/start.sh && ./mvnw spring-boot:run``

During the first execution postgres docker container and test db are created.

To stop postgres docker container execute

``./postgres/stop.sh``

To destroy execute

``./postgres/destroy.sh`` 