package org.accountmanagerapi;

import java.util.List;

/**
 * Customer Entity
 * 
 * @author Hamdija Haracic
 * @version v0.1
 * @since 24.08.2020
 */
public class Customer {
    public Long id;
    public String name;
    public String surname;
    public Double balance;
    public List<Account> accounts;

    public boolean valid() {
        if (this.name == null || this.name.length() <= 0) {
            return false;
        }

        if (this.surname == null || this.surname.length() <= 0) {
            return false;
        }

        return true;
    }
}