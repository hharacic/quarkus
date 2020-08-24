package org.accountmanager;

import java.util.List;
import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceException;

import io.quarkus.test.Mock;

/**
 * Mock of Account Service
 * 
 * @author Hamdija Haracic
 * @version v0.1
 * @since 20.08.2020
 */
@Mock
@ApplicationScoped
public class MockAccountService extends AccountService {

    private Account account;

    public MockAccountService() {
        this.account = new Account();
        this.account.id = Long.valueOf(1);
        this.account.customerId = Long.valueOf(1);
        this.account.balance = 100.00;
    }
    
    @Override
    public Account getAccountById(Long accountId) {
        if (accountId == 1) {
            return this.account;
        }

        return null;
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<Account>();
        
        accounts.add(this.account);

        return accounts;
    }

    @Override
    public List<Account> letCustomerAccounts(Long customerId) {
        List<Account> accounts = new ArrayList<Account>();
        
        if (customerId == 1) {
            accounts.add(this.account);
        }

        return accounts;
    }

    @Override
    public Account createAccount(Account account) throws PersistenceException{

        if (account.customerId == 1 && account.balance == 100.00) {
            return this.account;
        } else {
            throw new PersistenceException();
        }
    }
}