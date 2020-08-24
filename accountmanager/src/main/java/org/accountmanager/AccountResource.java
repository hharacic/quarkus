package org.accountmanager;

import javax.ws.rs.Produces;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Account Resource exposes REST endpoints for Account management
 * 
 * @author Hamdija Haracic
 * @version v0.1
 * @since 20.08.2020
 */
@Path("/accounts")
public class AccountResource {
    
    @Inject
    private IAccountService accountService;

    /**
     * Retrieve account by account ID and exposes as REST GET method
     * Accepts JSON as input and retturns JSON as output
     * 
     * @param accountId (Account.id)
     * @return Instance of Account or empty Object
     */
    @GET
    @Path("/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAccount(@PathParam("accountId") Long accountId) {
        Account account = this.accountService.getAccountById(accountId);

        if (account != null) {
            return Response.status(200).entity(account).build();
        } else {
            return Response.status(404).entity("[]").build();
        } 
    }

    /**
     * Retrieves accounts list and exposes as REST GET Method
     * Accepts JSON as input and retturns JSON as output
     * 
     * @return List of Accounts or empty Object
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response listAccounts() {
        return Response.status(200).entity(this.accountService.getAllAccounts()).build();
    }

    /**
     * Retrieve List of account instances belonging to specific Customer
     * 
     * @param customerId (Customer.id)
     * @return List of Account instances or empty Object
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/customeraccounts/{customerId}")
    public Response listCustomerAccounts(@PathParam("customerId") Long customerId) {
        return Response.status(200).entity(this.accountService.letCustomerAccounts(customerId)).build();
    }

    /**
     * Create Account endpoint exposed as REST POST Method
     * Accepts JSON as input and retturns JSON as output
     * 
     * @param account (Account)
     * @return Instance of newly created Customer or empty Object
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createAccount(Account account) {
        try {
            return Response.status(200).entity(this.accountService.createAccount(account)).build();
        } catch (PersistenceException e) {
            //In real life catch and log error

            // To simplify return error code and empty JSON
            return Response.status(500).entity("[]").build();
        }
    }

    // In real life other Endpoints and Resource Logic methods would be implemented here
}