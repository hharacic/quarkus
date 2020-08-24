package org.accountmanager;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceException;

/**
 * Customer Service that implements CRUD and Business logic of ICustomerService
 * 
 * @author Hamdija Haracic
 * @version v0.1
 * @since 20.08.2020
 */
@ApplicationScoped
public class CustomerService implements ICustomerService {
    
    public Customer getCustomerById(Long customerId) {
        return Customer.findById(customerId);
    }

    public List<Customer> getAllCustomers() {
        return Customer.listAll();
    }

    public Customer createCustomer(Customer customer) throws PersistenceException{
        customer.persist();

        if (customer.isPersistent()) {
            return customer;
        } else {
            throw new PersistenceException();
        }
    }

    //In real life, other CRUD operations and Business Logic related with Customer entity would be here...
}