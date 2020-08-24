import { Component, OnInit } from '@angular/core';
import { CustomersService } from '../customers.service';
import { Customer } from '../customers.service';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-customer',
    templateUrl: './customer.component.html',
    styleUrls: ['./customer.component.scss']
})
export class CustomerComponent implements OnInit {

    customer$: Customer;

    constructor(
        private customersService: CustomersService,
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
        this.customersService.getCustomer(customer_id).subscribe(
            (response: Customer) => {
                this.customer$ = response;
                console.log(response);
            }
        );
    }
}
