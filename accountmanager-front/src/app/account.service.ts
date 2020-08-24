import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';


@Injectable({
    providedIn: 'root'
})
export class AccountService {

    customerEndpoint = '/api/customers/';
    endpoint = environment.apiUrl + this.customerEndpoint;

    constructor(private http: HttpClient) { }

    createAccount(account) {
        //const postBody = new HttpParams().set("customer_id", customer_id).set("balance", balance);
        return this.http.post(this.endpoint + account.customerId + "/accounts", account);
    }
}
