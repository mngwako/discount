# discount

## Requirements

For building and running the application you need:
- [JDK 11]
- [Maven 3]
- [MySQL]

## Running the application locally
Before running:

* Create MySQL database called "discount"
* Open "application.yml"
* Change "password" and "username" in datasource to your credentials

To run application:

```shell
mvn spring-boot:run
```
To run application unit tests:

```shell
mvn clean test
```
