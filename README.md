# discount

## Requirements

For building and running the application you need:
- [JDK 11]
- [Maven 3]
- [MySQL 5]

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

1) http://localhost:8080/api/user/create (Already seeded 4 users)

-body
```json
{
    "fullName": "Monthusiotsile Ngwako",
    "customerNumber": "123456",
    "userType":{"id":"d825abc8-493e-4c34-83ce-d10330b32748"}
}
```
UserType list already seeded:
```json
[
    {
    "id":"d825abc8-493e-4c34-83ce-d10330b32748",
    "name":"Employee"
    },
    {
    "id":"21847cba-96dd-43f3-9e4d-0511ad8c1a0c",
    "name":"Affiliate"
    },
    {
    "id":"5ce111ad-afba-4077-ae72-5da9d625251b",
    "name":"Customer"
    }
]
```

2) http://localhost:8080/api/user/list/all

Return list of all users in the database

3) http://localhost:8080/api/sales/transaction/create
- body
```json
{
    "user": {"customerNumber": "0000004"},
    "bill":290,
    "saleCategory":"Services"
}
```
SaleCategory strings with enums:
* "Grocery"
* "Services"
* "Gardening"
* "Furniture"


4) http://localhost:8080/api/sales/transaction/list/all

Return list of all sales transactions in the database
