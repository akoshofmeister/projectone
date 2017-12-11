import { Component } from '@angular/core';
import {UserService} from "./services/user.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  userService: UserService = null;

  constructor(userService: UserService) {
    this.userService = userService;
  }

  isLoggedIn() {
    return this.userService.isLoggedIn;
  }

  navVisible = true;
  ngAfterContentInit() {
    document.getElementById("sideNav").addEventListener("transitionend", (event) => {
      if (this.navVisible) {
        document.getElementById("togglerSecond").classList.remove("hidden");
      } else {
        document.getElementById("togglerFirst").classList.remove("hidden");
      }
    }, false);
  }

  toggle() {
    if (!this.navVisible) {
      document.getElementById("togglerFirst").classList.add("hidden");
    } else {
      document.getElementById("togglerSecond").classList.add("hidden");
    }
    this.navVisible = !this.navVisible;
  }
}
