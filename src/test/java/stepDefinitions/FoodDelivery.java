package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FoodDelivery {

    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;

    @BeforeEach
    public void setup() {
        // Base URI setup
        RestAssured.baseURI = "http://localhost:3000";

        // Request Specification
        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                //.log(org.restassured.filter.log.LogDetail.ALL)
                .build();

        // Response Specification
        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                //.log(org.restassured.filter.log.LogDetail.ALL)
                .build();
    }

    

    @Test
    public void testRegisterUser() {
        String newUser = "{ \"username\": \"Mukesh\", \"password\": \"pass1\", \"email\": \"user1@example.com\" }";

        given()
            .spec(requestSpec)
            .body(newUser)
        .when()
            .post("/users/register")
        .then()
            .spec(responseSpec)
            .statusCode(201)
            .body("username", equalTo("Mukesh"))
            .body("email", equalTo("user1@example.com"));
    }

    @Test
    public void testLoginUser() {
        String loginUser = "{ \"username\": \"Mukesh\", \"password\": \"pass1\" }";

        given()
            .spec(requestSpec)
            .body(loginUser)
        .when()
            .post("/users/login")
        .then()
            .spec(responseSpec)
            .statusCode(200)
            .body("message", equalTo("Login successful"));
    }

    @Test
    public void testGetRestaurantById() {
        given()
            .spec(requestSpec)
        .when()
            .get("/restaurants/1")
        .then()
            .spec(responseSpec)
            .statusCode(200)
            .body("name", equalTo("A2B Restaurant"));
    }

    @Test
    public void testCreateOrder() {
        String newOrder = "{ \"userId\": 1, \"restaurantId\": 1, \"foodItems\": [\"Biriyani\", \"Pepper Chicken\"], \"totalPrice\": 20.0 }";

        given()
            .spec(requestSpec)
            .body(newOrder)
        .when()
            .post("/orders")
        .then()
            .spec(responseSpec)
            .statusCode(201)
            .body("totalPrice", equalTo(20));
    }

    @Test
    public void testSearchFoodsByName() {
        given()
            .spec(requestSpec)
            .queryParam("name", "Briyani")
        .when()
            .get("/foods/search")
        .then()
            .spec(responseSpec)
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("[0].name", equalTo("Briyani"));
    }

    @Test
    public void testUpdateUserProfile() {
        String updatedProfile = "{ \"username\": \"NaveenRaj\", \"email\": \"NaveenRaj05@example.com\" }";

        given()
            .spec(requestSpec)
            .body(updatedProfile)
        .when()
            .put("/users/1")
        .then()
            .spec(responseSpec)
            .statusCode(200)
            .body("username", equalTo("NaveenRaj"))
            .body("email", equalTo("NaveenRaj05@example.com"));
    }

    @Test
    public void testDeleteUser() {
        given()
            .spec(requestSpec)
        .when()
            .delete("/users/5")
        .then()
            .spec(responseSpec)
            .statusCode(404);
    }
}
