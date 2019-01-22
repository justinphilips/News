import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import {RegisterService} from '../register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers:[RegisterService]
})
export class RegisterComponent implements OnInit {
user:any={}
loading = false;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private registerService: RegisterService
  ) { }

  ngOnInit() {
  }
  // Register Service
  register() {
    this.loading = true;
    this.registerService.register(this.user)
        .subscribe(
            data => {
                //this.alertService.success('Registration successful', true);
                this.router.navigate(['login']);
            },
            error => {
                console.log(error.error.message)
                //this.alertService.error(error.error.message);
                if(error.error.message.includes('User already exist')) {
                    this.router.navigate(['login']);
                }

                this.loading = false;
            });
  }
}
