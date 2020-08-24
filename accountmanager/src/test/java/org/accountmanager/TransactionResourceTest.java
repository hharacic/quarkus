package org.accountmanager;

import static io.restassured.RestAssured.given;

import javax.json.Json;
import javax.json.JsonObject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static org.hamcrest.CoreMatchers.not;

@QuarkusTest
public class TransactionResourceTest {

    // SUCESS TEST: createTransaction
    @Test
    void testCreateTransactionEndpoint_SUCESS() {
        JsonObject body = Json.createObjectBuilder()
            .add("accountId", "1")
            .add("transactionAmount", "100.00")
            .build();

        given()
                .contentType("application/json")
                .body(body)
            .when()
                .post("/transactions")
            .then()
                .statusCode(200);
    }

    // ERROR TEST: createTransaction - empty Post
    @Test
    void testCreateTransactionEndpoint_ERROR() {
        JsonObject body = Json.createObjectBuilder()
            .add("accountId", "")
            .add("transactionAmount", "")
            .build();

        given()
                .contentType("application/json")
                .body(body)
            .when()
                .post("/transactions")
            .then()
                .statusCode(not(200));
    }

    // SUCCESS TEST: getAllTransactions
    @Test
    void testListTransactionsEndpoint_SUCCESS() {
        given()
          .when().get("/transactions")
          .then()
             .statusCode(200);
    }

    // SUCCESS TEST: getAccountTransactions
    @Test
    void testListCustomerTransactionsEndpoint_SUCCESS() {
        given()
          .when().get("/transactions/accounttransactions/1")
          .then()
             .statusCode(200);
    }

    // SUCCESS TEST: getTransactionById - Transaction Found
    @Test
    void testGetTransactionByIdEndpoint_SUCCESS() {
        given()
          .when().get("/transactions/1")
          .then()
             .statusCode(200);
    }

    // ERROR TEST: getTransactionById - Transaction Not Fount
    @Test
    void testGetTransactionByIdEndpoint_ERROR() {
        given()
          .when().get("/transactions/2")
          .then()
             .statusCode(404);
    }
}