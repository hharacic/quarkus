package org.accountmanager;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

/**
 * Transaction Entity
 * 
 * @author Hamdija Haracic
 * @version v0.1
 * @since 20.08.2020
 */
@Entity
public class Transaction extends PanacheEntityBase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Long accountId;
    public Double transactionAmount;

    // In real life I would add standard fields that every entity contains: status, description, created, created by, modified... 
}