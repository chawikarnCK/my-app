package com.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import datamodel.User;
import io.restassured.http.ContentType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;


public class App {

    private static final Log log = LogFactory.getLog(App.class);
    String baseURI = "https://gorest.co.in/public/v2";
    String accessToken = "989bd3ee9e13f8a3f3c6146f41fa591131d5fd4b5f52f526b25b627d886ada8c";


    @Test
    public void checkGetStatusCode() {
        var response = given().auth().oauth2(accessToken)
                .when().get(baseURI + "/users")
                .then()
                .statusCode(200);

        int statusCode = response.extract().statusCode();

        if (statusCode == 200) {
            System.out.println("Request successful");
        } else {
            System.out.println("request failed");
        }
    }

    @Test
    public void checkGetUserNotNull() {
        User users = new User();
        var response = given().auth().oauth2(accessToken)
                .when().get(baseURI + "/users")
                .then()
                .statusCode(200)
                .body("name", everyItem(notNullValue()))
                .body("email", everyItem(notNullValue()))
                .body("gender", everyItem(notNullValue()))
                .body("status", everyItem(notNullValue()));
        System.out.println("Checked Request Not null");

    }

    @Test
    public void checkContentType() {
        var response = given().auth().oauth2(accessToken)
                .when().get(baseURI + "/users")
                .then()
                .contentType("application/json");  // Check if Content-Type
    }


    @Test
    public void checkGetUser() throws IOException {
        var response = given().auth().oauth2(accessToken)
                .when()
                .get(baseURI + "/users")
                .then()
                .statusCode(200);
            System.out.println("Get request  successful");
             response.log().body();
    }


    @Test
    public void checkPostAPI() {

        int userid = 0;
        try {
            // Read JSON from file
            String jsonBody = new String(Files.readAllBytes(Paths.get("src/test/java/datamodel/PostBody.json")));

            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode jsonObject = (ObjectNode) objectMapper.readTree(jsonBody);

            // Generate a random number
            int randomNum = new Random().nextInt(100);

            // Modify JSON mail dynamically because email can not be duplicate
            jsonObject.put("email", "TestEmail1" + randomNum + "@example.com");
            String updatedJsonBody = objectMapper.writeValueAsString(jsonObject);

            var response = given()
                    .contentType(ContentType.JSON)
                    .auth().oauth2(accessToken)
                    .body(updatedJsonBody)
                    .when()
                    .post(baseURI + "/users")
                    .then()
                    .statusCode(201) // Expect success response
                    .extract().response();

            userid = response.jsonPath().getInt("id");
            System.out.println("POST : User created with ID: " + userid);
            System.out.println("Response: " + response.asString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void checkPutUser() throws IOException {
        // Read JSON from file
        String jsonBody = new String(Files.readAllBytes(Paths.get("src/test/java/datamodel/PutBody.json")));
        String Userid = "7725317";

        var response = given()
                .contentType(ContentType.JSON)
                .auth().oauth2(accessToken)
                .body(jsonBody)
                .when()
                .put(baseURI + "/users/" + Userid)
                .then()
                .statusCode(200)
                .body("status", equalTo("inactive"));
             System.out.println("PUT : Status Changed :  inactive");
    }

    @Test
    public void checkDeleteUser() throws IOException {
        int user = CreateRandomUserId(); // Create new user to delete
        var response = given()
                .contentType(ContentType.JSON)
                .auth().oauth2(accessToken)
                .when()
                .delete(baseURI + "/users/" + user)
                .then()
                .statusCode(204);
        System.out.println("Deleted ID : " + user);

        /// Check get id
        var Newresponse = given()
                .contentType(ContentType.JSON)
                .auth().oauth2(accessToken)
                .when()
                .get(baseURI + "/users/" + user)
                .then()
                .statusCode(404);

    }





    public int CreateRandomUserId() {

        int userid = 0;
        try {
            // Read JSON from file
            String jsonBody = new String(Files.readAllBytes(Paths.get("src/test/java/datamodel/PostBody.json")));

            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode jsonObject = (ObjectNode) objectMapper.readTree(jsonBody);

            // Generate a random number
            int randomNum = new Random().nextInt(100);

            // Modify JSON mail dynamically because email can not be duplicate
            jsonObject.put("email", "TestEmail1" + randomNum + "@example.com");
            String updatedJsonBody = objectMapper.writeValueAsString(jsonObject);

            var response = given()
                    .contentType(ContentType.JSON)
                    .auth().oauth2(accessToken)
                    .body(updatedJsonBody)
                    .when()
                    .post(baseURI + "/users")
                    .then()
                    .statusCode(201) // Expect success response
                    .extract().response();
            userid = response.jsonPath().getInt("id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userid;

    }
}






