/** This module is used for routing throughout the application */
import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { WatchlistComponent } from './watchlist/watchlist.component';
import { AdminComponent } from './admin/admin.component';


const routes: Routes = [
    
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    {path:'register', component:RegisterComponent },
    {path:'dashboard', component: DashboardComponent},
    {path:'watchlist', component: WatchlistComponent},
    {path:'admin', component: AdminComponent}
   
   
];


@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
  })
export class AppRoute{}