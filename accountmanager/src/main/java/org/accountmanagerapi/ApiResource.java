package org.accountmanagerapi;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;

/**
 * Account Management API
 * 
 * *******************************************************************************************
 * For this exercise, I assumed that there are 3 Microservices, Customer, Account and Transaction.
 * Each of those MS in real life would be sepperate application with authonomus persistance and 
 * all other coresponding components
 * 
 * The Account Management API is created on top of those 3 Microservices to act as 
 * a gateway and provide business orchestration for thode microservices
 * 
 * The overall project demonstartes how to build simpe applications using Quarkus covering:
 * - Quarkus basics
 * - CDI
 * - Testing
 * - Persistance
 * - Rest Client Implementation
 * *******************************************************************************************
 * 
 * @author Hamdija Haracic
 * @version v0.1
 * @since 24.08.2020
 * 
 */
@Path("/api")
public class ApiResource {
    
    /**
     * Inject Rest Client
     */
    @Inject
    @RestClient
    ApiService apiService;

    /**
     * Create Customer API
     * 
     * @param Customer
     * @return Response: JSON - Customer or Error Object
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customers")
    @Transactional
    public Response createCustomer(Customer customer) {
        if(customer.valid()) {
            return Response.status(200).entity(this.apiService.createCustomer(customer)).build();
        }
        
        return Response.status(400).entity(new Error("400", "Invalid Request, please check request data!")).build();
    }

    /**
     * Get Gustomers API
     * 
     * @return Response: JSON - List<Customer> or Error Object
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customers")
    public Response getCustomers() {
        return Response.status(200).entity(this.apiService.getCustomers()).build();
    }

    /**
     * Get Customer by ID API
     * 
     * @param customerId
     * @return Response: JSON - Customer with (Account and Transactions if any) or Error Object
     */
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customers/{customerId}")
    public Response getCustomerById(@PathParam("customerId") Long customerId) {
        Customer customer = this.apiService.getCustomer(customerId);

        if (customer == null) {
            return Response.status(404).entity(new Error("404", "Customer not found!")).build();
        }

        customer.accounts = apiService.getCustomerAccounts(customerId);
        customer.balance = 0.00;

        customer.accounts.forEach(account->{
            account.transactions = apiService.getAccountTransactions(account.id);
            customer.balance += account.balance;
        });

        return Response.status(200).entity(customer).build();
    }

    /**
     * Create Customer Account API
     * 
     * @param Account
     * @return Response: JSON - Account (and Transaction if created) or Error Object
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/customers/{customerId}/accounts")
    @Transactional
    public Response createAccount(Account account) {
        if (!account.valid()) {
            return Response.status(400).entity(new Error("400", "Invalid Request, please check request data!")).build();
        }

        Account newAccount = apiService.createAccount(account);

        if (newAccount == null) {
            return Response.status(500).entity(new Error("400", "Error, please try again later!")).build();
        }

        if (account.balance > 0) {
            newAccount.transactions = new ArrayList<Transaction>(); 
            newAccount.transactions.add(this.apiService.createTransaction(new Transaction(newAccount.id, newAccount.balance)));
        } 

        return Response.status(200).entity(newAccount).build();
    }

    // Other methods can be implemented based on the needs...
}