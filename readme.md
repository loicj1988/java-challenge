Employee API challenge application
===

  This service is an Employee API challenge. It provides end points to managed employees. 

---

## Libraries used

  1. Spring Boot 2.1.4
  2. Json webtoken
  3. Swagger API
  4. Hibernate Validation API
  5. Spring Boot Test

---

## Features

  These services can perform, Employee CRUD operations.

---

## Running

  1. Maven build : mvn clean install
  2. Application execution : java -jar target/api-demo-[version].jar

---

## Versions

  - 0.0.1-SNAPSHOT : API improved for java challenge 

---

## Improvements possible

  - Create a Dockerfile to create a docker image for this microservice
  - Add kubernetes deployment files
  - Separate Authenticate APIs to a different project (microservice)
  - Save authorized users in database and encode their password 
  - Export JWT Secret to environment variable
  - Handle more Exceptions to customize response
  - Implement logging
  - Add more tests for controller

---

## Contributors

@ Loic Jacquel <loic.jacquel@gmail.com>

---

## License & copyrights

MIT license or something else ...