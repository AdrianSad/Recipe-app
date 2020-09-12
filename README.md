# Recipe-app
Recipe application using spring framework, H2 and MySQL databases, thymeleaf and bootstrap. In addition there are severeal tests written using Mockito and JUnit.

### Databases Configuration

The h2 console is automatically exposed at http://localhost:8080/h2-console and it is possible to inspect the content of the database using the jdbc:h2:mem:testdb url.

Application can be run with a different profile:

`spring.profiles.active=default` for in-memory database (H2).

`spring.profiles.active=dev` for developer MySQL access.

`spring.profiles.active=prod` for production MySQL access.

<br/>
<br/>
<br/>
