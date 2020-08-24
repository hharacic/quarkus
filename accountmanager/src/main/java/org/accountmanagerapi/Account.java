package org.accountmanagerapi;

import java.util.List;

/**
 * Account Entity
 * 
 * @author Hamdija Haracic
 * @version v0.1
 * @since 24.08.2020
 */
public class Account {
    public Long id;
    public Long customerId;
    public Double balance;
    public List<Transaction> transactions;

    public boolean valid() {
        if (this.customerId == null || this.customerId <= 0) {
            return false;
        }

        if (this.balance == null) {
            return false;
        }

        return true;
    }
}