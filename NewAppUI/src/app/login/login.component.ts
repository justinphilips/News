import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import{LoginService} from '../login.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers:[LoginService]
})
export class LoginComponent implements OnInit {

  user: any = {};
  loading = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private loginService: LoginService,
    private userService: UserService
  ) { }

  ngOnInit() {
  }
  // login service
  login() {
    this.loading = true;
    this.loginService.login(this.user)
        .subscribe(
            data => {
              this.userService.userId =data.userId;
               this.router.navigate(['dashboard']);
            },
            error => {
                //this.alertService.error(error.error.message);
                alert("Invalid Credentials");
                this.loading = false;
            });
  }

}
