package org.accountmanager;

import java.util.List;
import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceException;

import io.quarkus.test.Mock;

/**
 * Mock of Transaction Service
 * 
 * @author Hamdija Haracic
 * @version v0.1
 * @since 20.08.2020
 */
@Mock
@ApplicationScoped
public class MockTransactionService extends TransactionService {

    private Transaction transaction;

    public MockTransactionService() {
        this.transaction = new Transaction();
        this.transaction.id = Long.valueOf(1);
        this.transaction.accountId = Long.valueOf(1);
        this.transaction.transactionAmount = 100.00;
    }

    @Override
    public Transaction getTransactionById(Long transactionId) {
        if (transactionId == 1) {
            return this.transaction;
        } else {
            return null;
        }
    }

    @Override
    public List<Transaction> listAllTransactions() {
        List<Transaction> transactions = new ArrayList<Transaction>();

        transactions.add(this.transaction);

        return transactions;
    }

    @Override
    public List<Transaction> listAllAccountTransactions(Long accountId) {
        List<Transaction> transactions = new ArrayList<Transaction>();

        if (accountId == 1) {
            transactions.add(this.transaction);
        }

        return transactions;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) throws PersistenceException {
        if (transaction.accountId == 1 && transaction.transactionAmount == 100.00) {
            return this.transaction;
        } else {
            throw new PersistenceException();
        }
    }
}