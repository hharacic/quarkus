import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})
export class CustomersService {

    customerEndpoint = '/api/customers';
    endpoint = environment.apiUrl + this.customerEndpoint;

    constructor(private http: HttpClient) { }

    getCustomers() {
        return this.http.get(this.endpoint);
    }
    
    getCustomer(customer_id) {
        return this.http.get(this.endpoint+"/"+customer_id);
    }

    create(customer) {
        //const postBody = new HttpParams().set("name", customer.name).set("surname", customer.surname);
        return this.http.post(this.endpoint, customer);
    }
}

export interface Customer {
    name: any,
    surname: any,
    id: any,
    balance: any,
    accounts: Account[]
}

export interface Account {
    id: any,
    balance: any,
    transactions: Transaction[]
}

export interface Transaction {
    id: any,
    account_id: any,
    amount: any
}
