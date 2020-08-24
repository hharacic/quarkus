package org.accountmanagerapi;

/**
 * Error Entity
 * 
 * @author Hamdija Haracic
 * @version v0.1
 * @since 24.08.2020
 */
public class Error {
    public String code;
    public String message;

    public Error() {
        
    }

    public Error(String code, String message) {
        this.code = code;
        this.message = message;
    }
}