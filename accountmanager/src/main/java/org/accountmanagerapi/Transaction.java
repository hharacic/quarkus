package org.accountmanagerapi;

/**
 * Transaction Entity
 * 
 * @author Hamdija Haracic
 * @version v0.1
 * @since 24.08.2020
 */
public class Transaction {
    public Long id;
    public Long accountId;
    public Double transactionAmount;

    public Transaction() {}

    public Transaction(Long accountId, Double transactionAmount) {
        this.accountId = accountId;
        this.transactionAmount = transactionAmount;
    }

    public boolean valid() {
        if (this.accountId == null || this.transactionAmount == null) {
            return false;
        }

        return true;
    }
}