import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user.service";
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import {User} from "../types/user";

@Component({
  selector: 'login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  loginForm: FormGroup;
  registerForm: FormGroup;

  showRegister: boolean = false;
  constructor(private fb: FormBuilder,
              private userService: UserService,
              private router: Router) {
    this.loginForm = this.fb.group({
      username: ['',Validators.required],
      password: ['',Validators.required]
    });
    this.registerForm = this.fb.group({
      username: ['',Validators.required],
      password: ['',Validators.required]
    });
  }

  ngOnInit() {
  }

  toggleRegister() {
    this.showRegister = !this.showRegister;
  }

  login() {
    const val = this.loginForm.value;
    if (val.username && val.password) {
      this.userService.login(new User(val.username, val.password))
        .subscribe(
          () => {
            console.log("User is logged in");
            this.router.navigateByUrl('/');
          }
        );
    }
  }

  register() {
    const val = this.registerForm.value;
    if (val.username && val.password) {
      this.userService.register(new User(val.username, val.password))
        .subscribe(
          () => {
            console.log("User is registered");
            this.router.navigateByUrl('/');
            this.toggleRegister();
          }
        );
    }
  }
}
