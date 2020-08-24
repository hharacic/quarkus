package org.accountmanager;

import static io.restassured.RestAssured.given;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static org.hamcrest.CoreMatchers.not;

/**
 * Customer Resource Endpoint Tests
 * 
 * @author Hamdija Haracic
 * @version v0.1
 * @since 20.08.2020
 */
@QuarkusTest
public class AccountResourceTest {
    
    // SUCESS TEST: createAccount
    @Test
    void testCreateAccountEndpoint_SUCESS() {
        JsonObject body = Json.createObjectBuilder()
            .add("customerId", "1")
            .add("balance", "100.00")
            .build();

        given()
                .contentType("application/json")
                .body(body)
            .when()
                .post("/accounts")
            .then()
                .statusCode(200);
    }

    // ERROR TEST: createAccount
    @Test
    void testCreateAccountEndpoint_ERROR() {
        JsonObject body = Json.createObjectBuilder()
            .add("customerId", "2")
            .add("balance", "100.00")
            .build();

        given()
                .contentType("application/json")
                .body(body)
            .when()
                .post("/accounts")
            .then()
                .statusCode(not(200));
    }

    // SUCCESS TEST: listAllAccounts
    @Test
    void testListAllAccountsEndpoint_SUCCESS() {
        given()
          .when().get("/accounts")
          .then()
             .statusCode(200);
    }

    // SUCCESS TEST: listCustomerAccounts - One Account found
    @Test
    void testListCustomerAccountsEndpoint_SUCCESS() {
        given()
          .when().get("/accounts/customeraccounts/1")
          .then()
             .statusCode(200);
    }
    
    // SUCCESS TEST: getAccountById - Account Found
    @Test
    void testGetAccountByIdEndpoint_SUCCESS() {
        given()
          .when().get("/accounts/1")
          .then()
             .statusCode(200);
    }

    // ERROR TEST: getAccountById - Account Not Found
    @Test
    void testGetAccountByIdEndpoint_ERROR() {
        given()
          .when().get("/accounts/2")
          .then()
             .statusCode(404);
    }
}