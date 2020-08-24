package org.accountmanager;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceException;

/**
 * Account Service that implements CRUD and Business logic of IAccountService
 * 
 * @author Hamdija Haracic
 * @version v0.1
 * @since 20.08.2020
 */
@ApplicationScoped
public class AccountService implements IAccountService {

    public Account getAccountById(Long accountId) {
        return Account.findById(accountId);
    }

    public List<Account> getAllAccounts() {
        return Account.listAll();
    }

    public List<Account> letCustomerAccounts(Long customerId) {
        return Account.list("customerId", customerId);
    }

    public Account createAccount(Account account) throws PersistenceException{
        account.persist();

        if (account.isPersistent()) {
            return account;
        } else {
            throw new PersistenceException();
        }
    }

    //In real life, other CRUD operations and Business Logic related with Account entity would be here...
}