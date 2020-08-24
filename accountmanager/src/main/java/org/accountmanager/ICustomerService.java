package org.accountmanager;

import java.util.List;

import javax.persistence.PersistenceException;

/**
 * Customer Service where Customer CRUD and Business logic is defined
 * 
 * @author Hamdija Haracic
 * @version v0.1
 * @since 20.08.2020
 */
public interface ICustomerService {

    /**
     * Retrive customer by customer ID
     * 
     * @param customerId (Customer.id)
     * @return Customer Instance or empty object
     */
    public Customer getCustomerById(Long customerId);

    /**
     * List Customers
     * 
     * @return List of customers
     */
    public List<Customer> getAllCustomers();

    /**
     * Create Customer Instance
     * 
     * @param name (Customer.name)
     * @param surname (Customer.surname)
     * @return Customer Instance of newly created customer
     * @throws PersistenceException if customer cannot be persisted
     */
    public Customer createCustomer(Customer customer) throws PersistenceException;

    //In real life, other CRUD operations and Business Logic related with Customer entity would be here...
}