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

Tests Coverage Report:
* After running test, the test report will be in “target/site/jacoco/index.html” with can be opened using a browse

## Using APIs locally
The application already has preliminary data needed to use discount APIs, these are:

* http://localhost:8080/api/sales/transaction/create
- body
```json
{
    "user": {"customerNumber": "0000004"},
    "bill":290,
    "saleCategory":"Services"
}
```
