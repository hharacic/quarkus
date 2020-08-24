package org.accountmanager;

import java.util.List;

import javax.persistence.PersistenceException;

/**
 * Transaction Service where Transaction CRUD and Business logic is defined
 * 
 * @author Hamdija Haracic
 * @version v0.1
 * @since 20.08.2020
 */
public interface ITransactionService {
    /**
     * Retrieve Transaction instance by Transaction ID
     * 
     * @param accountId (Transaction.id)
     * @return Instance of Transaction or empty Object
     */
    public Transaction getTransactionById(Long transactionId);

    /**
     * Retrieve all Transaction Instances
     * 
     * @return List of Transaction instances or empty Object
     */
    public List<Transaction> listAllTransactions();

    /**
     * Retrieve all Transactions that bellong to specific Account
     * 
     * @param accountId (Account.id)
     * @return List of transaction instances or empty Object
     */
    public List<Transaction> listAllAccountTransactions(Long accountId);

    /**
     * Create Transaction Instance
     * 
     * @param accountId (Account.id)
     * @param transactionAmount (Transaction.transactionAmount)
     * @return Instance of Transaction or empty Object
     * @throws PersistenceException if transaction cannot be persisted
     */
    public Transaction createTransaction(Transaction transaction) throws PersistenceException;

    //In real life, other CRUD operations and Business Logic related with Transaction entity would be here...
}