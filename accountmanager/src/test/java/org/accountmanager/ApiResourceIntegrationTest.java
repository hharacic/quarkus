package org.accountmanager;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import javax.json.Json;
import javax.json.JsonObject;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;

/**
 * Test Account Manager API Endpoints
 * 
 * @author Hamdija Haracic
 * @version v0.1
 * @since 24.08.2020
 */
@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class ApiResourceIntegrationTest {
    
    //SUCCESS TEST: Create Customer
    @Test
    public void testCreateCustomerEndpoint_SUCCESS() {
        JsonObject body = Json.createObjectBuilder()
            .add("name", "CustomerName")
            .add("surname", "CustomerSurname")
            .build();

        given()
                .contentType("application/json")
                .body(body)
            .when()
                .post("/api/customers")
            .then()
                .statusCode(200)
                .body(allOf(containsString("CustomerName"), containsString("CustomerSurname")));
    }

    //ERROR TEST: Create Customer - Empty Values
    @Test
    public void testCreateCustomerEndpoint_ERROR() {
        JsonObject body = Json.createObjectBuilder()
            .add("name", "")
            .add("surname", "")
            .build();

        given()
                .contentType("application/json")
                .body(body)
            .when()
                .post("/api/customers")
            .then()
                .statusCode(not(200));
    }

    //SUCCESS TEST: Create Account - with balance 0
    @Test
    public void testCreateAccountEndpoint_SUCCESS1() {
        JsonObject body = Json.createObjectBuilder()
            .add("customerId", 1)
            .add("balance", 0)
            .build();

        given()
                .contentType("application/json")
                .body(body)
            .when()
                .post("/api/customers/1/accounts")
            .then()
                .statusCode(200);
    }

    //SUCCESS TEST: Create Account - with balance greater than 0
    @Test
    public void testCreateAccountEndpoint_SUCCESS2() {
        JsonObject body = Json.createObjectBuilder()
            .add("customerId", 1)
            .add("balance", 100.00)
            .build();

        given()
                .contentType("application/json")
                .body(body)
            .when()
                .post("/api/customers/1/accounts")
            .then()
                .statusCode(200);
    }

    //ERROR TEST: Create Account - Empty Values
    @Test
    public void testCreateAccountEndpoint_ERROR() {
        JsonObject body = Json.createObjectBuilder()
            .add("customerId", "")
            .add("balance", "")
            .build();

        given()
                .contentType("application/json")
                .body(body)
            .when()
                .post("/api/customers/1/accounts")
            .then()
                .statusCode(not(200));
    }

    //SUCCESS TEST: Get all customers
    @Test
    public void testGetCustomersEndpoint_SUCCESS() {
        given()
          .when().get("/api/customers")
          .then()
             .statusCode(200);
    }

    //SUCESS TEST: Get Customer by ID
    @Test
    public void testGetCustomerEndpoint_SUCCESS() {
        given()
          .when().get("/api/customers/1")
          .then()
             .statusCode(200);
    }

    //ERROR TEST: Get Customer by ID - Customer does not exist
    @Test
    public void testGetCustomerEndpoint_ERROR() {
        given()
          .when().get("/api/customers/2")
          .then()
             .statusCode(not(200));
    }
}