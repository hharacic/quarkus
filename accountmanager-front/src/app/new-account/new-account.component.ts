import { Component, OnInit } from '@angular/core';
import { AccountService } from '../account.service';
import { CustomersService } from '../customers.service';
import { Customer } from '../customers.service';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-new-account',
    templateUrl: './new-account.component.html',
    styleUrls: ['./new-account.component.scss']
})
export class NewAccountComponent implements OnInit {

    customer$: Customer;
    success = false;
    error = false;
    customerId;

    constructor(
        private accountService: AccountService,
        private customerService: CustomersService,
        private route: ActivatedRoute
    ) { }

    ngOnInit(): void {
        this.route.paramMap.subscribe(
            params => {
                this.getCustomer(params.get('customerId'));
            }
        )
    }

    getCustomer(customer_id) {
        this.customerService.getCustomer(customer_id).subscribe(
            (response: Customer) => {
                this.customer$ = response;
                this.customerId = this.customer$.id;
                console.log(response);
            }
        );
    }

    save(account) {
        console.log(account);
        if (this.customer$) {
            this.accountService.createAccount(account).subscribe(
                response => {
                    this.success = true;
                    this.error = false;
                    this.getCustomer(this.customer$.id);
                },
                error => {
                    this.success = false;
                    this.error = true;
                }
            )
        }
    }

}
