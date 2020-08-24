package org.accountmanager;

import java.util.List;

import javax.persistence.PersistenceException;

/**
 * Account Service that implement CRUD and Business logic is defined
 * 
 * @author Hamdija Haracic
 * @version v0.1
 * @since 20.08.2020
 */
public interface IAccountService {
    /**
     * Retrieve Account instance by Account ID
     * 
     * @param accountId (Account.id)
     * @return Instance of Account or empty Object
     */
    public Account getAccountById(Long accountId);

    /**
     * Retrieve all account instances
     * 
     * @return List of Accounts or empty Object
     */
    public List<Account> getAllAccounts();

    /**
     * Retrieve all accounts belogn to specific Customer
     * 
     * @param customerId (Customer.id)
     * @return List of account instances or empty Object
     */
    public List<Account> letCustomerAccounts(Long customerId);

    /**
     * Create new Customer Instance
     * 
     * @param customerId (Customer.id)
     * @param balance (Account.balance)
     * @return Instance of newly created Account or empty Object
     * @throws PersistenceException if account cannot be persisted
     */
    public Account createAccount(Account account) throws PersistenceException;

    //In real life, other CRUD operations and Business Logic related with Account entity would be here...
}