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

cd /path/to/app

- mvn test

This will run the test classes and output the results in the console.


Test case overview
1. checkGetStatusCode
    * Sends a GET request to fetch users.
    * Validates if the status code is 200 (OK).
2. checkContentType
    * Ensures the response Content-Type is application/json.
3. checkGetUser
    * Fetches users and validates that required fields (name, email, gender, status) are not null.
4. checkPostAPI
    * Reads a JSON request body from a json file.
    * Modifies the email dynamically (to avoid duplicates).
    * Sends a POST request to create a new user.
    * Asserts a 201 (Created) response.
5. checkPutUser
    * Reads a JSON body from a file.
    * Sends a PUT request to update a user’s status.
    * Asserts the status field is updated to "inactive".
6. checkDeleteUser
    * Creates a new user dynamically.
    * Deletes the user and asserts 204 (No Content).
    * Ensures that retrieving the deleted user returns 404 (Not Found).
7. CreateRandomUserId (Helper method)
    * Generates a random user dynamically for DELETE tests.
    * Ensures email uniqueness by appending a random number.

