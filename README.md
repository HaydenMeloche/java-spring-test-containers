# Test Continers in Spring Boot

This is a small sample repo showing how you can enable [testcontainers](https://www.testcontainers.org/) for your Spring Boot project.

## Why?
When we develop integration tests we want as little as possible (preferably nothing) to be mocked to ensure that our tests are truely coving the entire application.

In the Spring Boot community a common solution to this problem is to utilize a [H2 database.](https://www.h2database.com/html/main.html) H2 works great, Spring will automatically launch it in memory as part of your application context and the required tables will be created from your `@Entity` annoatations. Once all your tests are completed the database will be will be wiped out and no cleanup on your behalf is required.

While H2 is great, there is one large problem here... Unless your using H2 as your production database, we're running our integration test againest a database that is different from the database we are using in production. Ideally our tests should use the exact same database they would use in production (down to the version number).

Testcontainers solves this problem by integrating similiarily to H2 into your tests but utilizes Docker to provide any type of database/data store you require (even redis, kafka, rabbitMQ). 

## How?

Converting an existing project to use testcontainers is simple. You can checkout commit [c664e4](https://github.com/HaydenMeloche/java-spring-test-containers/commit/c664e41987d9903214527009b07810ae1f531783)
for an example of converting from H2 to a MSSQL database.

More documentation for other supported platforms can be found here https://www.testcontainers.org/

### Sample run
If you're interested in seeing testcontainers in action you can clone this project locally and run the `mvn test` command.

You can also check out the Travis CI logs here:
https://travis-ci.com/HaydenMeloche/java-spring-test-containers/builds/142286139

