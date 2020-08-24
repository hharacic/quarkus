import { Component, OnInit } from '@angular/core';
import { CustomersService } from '../customers.service';

@Component({
    selector: 'app-new-customer',
    templateUrl: './new-customer.component.html',
    styleUrls: ['./new-customer.component.scss']
})
export class NewCustomerComponent implements OnInit {

    success = false;
    error = false;
    constructor(private customersService: CustomersService) { }

    save(customer) {
        console.log(customer);
        this.customersService.create(customer).subscribe(
            response => {
                this.error = false;
                this.success = true;
            },
            error => {
                this.success = false;
                this.error = true;
            }
        );
    }

    ngOnInit(): void {
    }

}
