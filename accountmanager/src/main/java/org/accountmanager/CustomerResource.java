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
 * Customer Resource, exposes REST Endpoints for the Customer management
 * 
 * @author Hamdija Haracic
 * @version v0.1
 * @since 20.08.2020
 */
@Path("/customers")
public class CustomerResource {

    @Inject
    ICustomerService customerService;;
    
    /**
     * 
     * Retrieve customer by customer ID Endpoint exposed as GET REST Method
     * Accepts JSON as input and retturns JSON as output
     * 
     * @param customerId (Customer.id)
     * @return Response: JSON - (200) Instance of Customer or (404) Empty Object
     */
    @GET
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("customerId") Long customerId) {
        Customer customer = this.customerService.getCustomerById(customerId);

        if (customer != null) {
            return Response.status(200).entity(customer).build();
        } else {
            return Response.status(404).entity("[]").build();
        }
    }

    /**
     * 
     * List all customers Endpoint exposed as GET REST method
     * Accepts JSON as input and retturns JSON as output
     * 
     * @return Response: JSON - List of Customers Instances or empty List
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listCustomers() {
        return Response.status(200).entity(this.customerService.getAllCustomers()).build();
    }

    /**
     * 
     * Create Customer Endpoint exposed as POST REST method
     * Accepts JSON as input and retturns JSON as output 
     * 
     * @param customer (Customer)
     * @return Response (Success: 200/Customer Instance) or (Error: 500/Empty JSON Object)
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createCustomer(Customer customer) {
        try {
            return Response.status(200).entity(this.customerService.createCustomer(customer)).build();
        } catch (PersistenceException e) {
            //In real life catch and log error

            //To keep simple return code 500 and empty JSON object.
            return Response.status(500).entity("[]").build();
        }
    }

    // In real life other Endpoints and Resource Logic methods would be implemented here
}