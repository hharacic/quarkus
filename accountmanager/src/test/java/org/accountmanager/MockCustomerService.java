package org.accountmanager;

import java.util.List;
import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceException;

import io.quarkus.test.Mock;

/**
 * Mock of CustomerService
 * 
 * @author Hamdija Haracic
 * @version v0.1
 * @since 20.08.2020
 */
@Mock
@ApplicationScoped
public class MockCustomerService extends CustomerService {
    
    private Customer customer;

    public MockCustomerService() {
        this.customer = new Customer();
        this.customer.id = Long.valueOf(1);
        this.customer.name = "CustomerName";
        this.customer.surname = "CustomerSurname";
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        if (customerId == 1) {
            return this.customer;
        }

        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<Customer>();

        customers.add(this.customer);

        return customers;
    } 

    @Override
    public Customer createCustomer(Customer customer) throws PersistenceException{
        if (!customer.name.equals("") && !customer.surname.equals("")) {
            return this.customer;
        } else {
            throw new PersistenceException();
        }
    }
}