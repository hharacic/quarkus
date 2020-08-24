import { Component, OnInit } from '@angular/core';
import { CustomersService } from '../customers.service';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

    customers$;

    constructor(customersService: CustomersService) { 
        this.customers$ = customersService.getCustomers();
    }

    ngOnInit(): void {
    }

}
