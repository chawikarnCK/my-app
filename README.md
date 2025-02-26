RestAssured Project
Base URL:
https://gorest.co.in/public/v2


Getting Started
To get started with this project, clone the repository to your local machine 
import it into IDE  as a Maven project.

Prerequisites
To run this project, you'll need:

Java 8 or higher
Maven 3.6 or higher
An IDE (e.g. IntelliJ IDEA, Eclipse)
Installing
To install this project, follow these steps:

Clone the repository to your local machine:
git clone https://github.com/chawikarnCK/my-app.git

Import the project into your preferred IDE as a Maven project.
Running the Tests
To run the tests, you can use the following command in your terminal:

- mvn clean install 
- mvn test

This will run the test classes and output the results in the console.

Testcase Description 
1. checkGetStatusCode : Check Get request status should be 200 (success)
2. checkContentType : Check ContentType json
3. checkGetUser : Check data response
4. checkPostAPI : Check Post request success
5. checkPutUser : Check update data (status field)
6. checkDeleteUser : Check Delete request success  

