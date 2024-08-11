About Expense Tracker API
It is a production ready Spring Boot REST API built from the ground up. The simple goal of this API is to track the user's daily spending. 

Tech Stack
Java 17

Spring Boot 3+

JPA 3+

Hibernate 6+

Spring Security 6+

Maven

Lombok

Steps to Run the API Locally
1. Clone the application

git clone 
2. Create postgres database

create database expensetracker
3. Change postgres username and password as per your installation

open src/main/resources/application.properties

change spring.datasource.username and spring.datasource.password as per your postgres installation

4. Build the app using maven

mvn clean install
A new jar file will be generated at target/expensetrackerapi-1.0.0.jar

5. Run the app using maven

mvn spring-boot:run
Alternatively, you can use Java command to run the application

java -jar target/expensetrackerapi-1.0.0.jar
The app will start running at <http://localhost:8080/ >.

Explore Rest APIs
The app defines the following APIs.

POST /api/v1/register

POST /api/v1/login

POST /api/v1/profile

PUT /api/v1/profile

DELETE /api/v1/deactivate

POST /api/v1/expenses

GET /api/v1/expenses

GET /api/v1/expenses/{expenseId}

PUT /api/v1/expenses/{expenseId}

DELETE /api/v1/expenses?expenseId={expenseId}

POST /api/v1/categories

GET /api/v1/categories

DELETE /api/v1/categories/{categoryId}
You can test them using Postman or any other rest client.
