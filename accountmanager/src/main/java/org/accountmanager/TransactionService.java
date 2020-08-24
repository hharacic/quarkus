package org.accountmanager;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceException;

/**
 * Transaction Service that implements CRUD and Business logic of ITransactionService
 * 
 * @author Hamdija Haracic
 * @version v0.1
 * @since 20.08.2020
 */
@ApplicationScoped
public class TransactionService implements ITransactionService{
    
    public Transaction getTransactionById(Long transactionId) {
        return Transaction.findById(transactionId);
    }

    public List<Transaction> listAllTransactions() {
        return Transaction.listAll();
    }

    public List<Transaction> listAllAccountTransactions(Long accountId) {
        return Transaction.list("accountId", accountId);
    }

    public Transaction createTransaction(Transaction transaction) throws PersistenceException {
        transaction.persist();

        if (transaction.isPersistent()) {
            return transaction;
        } else {
            throw new PersistenceException();
        }
    }

    //In real life, other CRUD operations and Business Logic related with Transaction entity would be here...
}