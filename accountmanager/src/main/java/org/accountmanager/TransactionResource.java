package org.accountmanager;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Account Resource exposes REST endpoints for Account management
 * 
 * @author Hamdija Haracic
 * @version v0.1
 * @since 20.08.2020
 */
@Path("/transactions")
public class TransactionResource {
    
    @Inject
    private ITransactionService transactionService;

    /**
     * Retrieve transaction by transaction ID and expose as REST GET method
     * Accepts JSON as input and returns JSON as output
     * 
     * @param transactionId (Transaction.id)
     * @return Response: JSON - (200) Instance of Transaction or (404) Empty Object
     */
    @GET
    @Path("/{transactionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransaction(@PathParam("transactionId") Long transactionId) {
        Transaction transaction = this.transactionService.getTransactionById(transactionId);
        
        if (transaction != null) {
            return Response.status(200).entity(transaction).build();
        } else {
            return Response.status(404).entity("[]").build();
        }
    }

    /**
     * Retrieve list of all transaction instances and expose as REST GET Method
     * Accepts JSON as input and returns JSON as output
     * 
     * @return Response: JSON - (200) List of transaction instances or empty Object
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listTransactions() {
        return Response.status(200).entity(this.transactionService.listAllTransactions()).build();
    }

    /**
     * Retrieve list of all transactions belonging to specific Account and expose as REST GET Method
     * Accepts JSON as input and returns JSON as output
     * 
     * @param accountId (Account.id)
     * @return Response: JSON - (200) List of transaction instances or empty Object
     */
    @GET
    @Path("/accounttransactions/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAccountTransactions(@PathParam("accountId") Long accountId) {
        return Response.status(200).entity(this.transactionService.listAllAccountTransactions(accountId)).build();
    }

    /**
     * Create Tranasction endpoint and exposed as REST Post Method
     * Accepts JSON as input and returns JSON as output
     * 
     * @param accountId (Account.id)
     * @param transactionAmount (Transaction.transactionAmount)
     * @return Response: JSON - (200) Newly created Transaction or (500) Empty Object
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createTransaction(Transaction transaction) {
        try {
            return Response.status(200).entity(this.transactionService.createTransaction(transaction)).build();
        } catch (PersistenceException e) {
            //In real life catch and log error

            //To keep simple return code 500 and empty JSON object.
            return Response.status(500).entity("[]").build();
        }
    }
}