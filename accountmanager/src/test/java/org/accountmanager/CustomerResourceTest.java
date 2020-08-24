package org.accountmanager;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import javax.json.Json;
import javax.json.JsonObject;

import static org.hamcrest.CoreMatchers.allOf;

/**
 * Customer Resource Endpoint Tests
 * 
 * @author Hamdija Haracic
 * @version v0.1
 * @since 20.08.2020
 */
@QuarkusTest
public class CustomerResourceTest {
    
    // SUCCESS TEST: getCustomerById - Customer Found
    @Test
    public void testGetCustomerByIdEndpoint_SUCCESS() {
        given()
          .when().get("/customers/1")
          .then()
             .statusCode(200)
             .body(allOf(containsString("CustomerName"), containsString("CustomerSurname")));
    }

    // ERROR TEST: getCustomerById - Customer Not Found
    @Test
    public void testGetCustomerByIdEndpoint_ERROR() {
        given()
          .when().get("/customers/2")
          .then()
             .statusCode(404);
    }

    // SUCCESS TEST: createCustomer
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
                .post("/customers")
            .then()
                .statusCode(200)
                .body(allOf(containsString("CustomerName"), containsString("CustomerSurname")));
    }

    // ERROR TEST: createCustomer
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
                .post("/customers")
            .then()
                .statusCode(500);
    }

    // SUCCESS TEST: listCustomers - one custome returned
    @Test
    public void testGetAllCustomersEndpoint_SUCCESS() {
        given()
          .when().get("/customers")
          .then()
             .statusCode(200)
             .body(allOf(containsString("CustomerName"), containsString("CustomerSurname")));
    }
}