package org.accountmanagerapi;

import javax.ws.rs.Produces;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.core.MediaType;

/**
 * Rest Clinet, used to access REST services for Account Management
 * 
 * @author Hamdija Haracic
 * @version v0.1
 * @since 24.08.2020
 */
@Path("")
@RegisterRestClient
public interface ApiService {
    

    /**
     * Create customer
     * @param Customer
     * @return Customer
     */
    @POST
    @Path("/customers")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Customer createCustomer(Customer customer);

    /**
     * Get customer by Customer ID
     * @param customerId
     * @return Customer
     */
    @GET
    @Path("/customers/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Customer getCustomer(@PathParam("customerId") Long customerId);

    /**
     * Get all customers
     * @return List<Customer>
     */
    @GET
    @Path("/customers")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    List<Customer> getCustomers(); 

    /**
     * Create Account
     * @param Account
     * @return Account
     */
    @POST
    @Path("/accounts")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Account createAccount(Account account);

    /**
     * Get account by account ID
     * @param accountId
     * @return Account
     */
    @GET
    @Path("/accounts/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Account getAccount(@PathParam("accountId") Long accountId);

    /**
     * Get all accounts
     * @return List<Account>
     */
    @GET
    @Path("/accounts")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    List<Account> getAccounts();

    /**
     * get customer accounts by customer ID
     * @param customerId
     * @return List<Accounts>
     */
    @GET
    @Path("/accounts/customeraccounts/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    List<Account> getCustomerAccounts(@PathParam("customerId") Long customerId);

    /**
     * Create transaction
     * @param Transaction
     * @return Transaction
     */
    @POST
    @Path("/transactions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Transaction createTransaction(Transaction transaction);

    /**
     * Get transaction by transaction ID
     * @param transactionId
     * @return Transaction
     */
    @GET
    @Path("/transactions/{transactionId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Transaction getTransaction(@PathParam("transactionId") Long transactionId);

    /**
     * Get all transactions
     * @return List<Transaction>
     */
    @GET
    @Path("/transactions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    List<Transaction> getTransactions();

    /**
     * Get Account transactions
     * @param accountId
     * @return List<Transaction>
     */
    @GET
    @Path("/transactions/accounttransactions/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    List<Transaction> getAccountTransactions(@PathParam("accountId") Long accountId);
}