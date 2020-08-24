import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CustomerComponent } from './customer/customer.component';
import { NewCustomerComponent } from './new-customer/new-customer.component';
import { NewAccountComponent } from './new-account/new-account.component';

const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'customers/:customerId', component: CustomerComponent },
    { path: 'customer/new', component: NewCustomerComponent },
    { path: 'customers/:customerId/account/new', component: NewAccountComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
