import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  private username: string = 'unknown';
  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.username = this.userService.getUsername();
  }

  logout() {
    this.userService.logout().subscribe(() => {
      console.log('User logged out');
      this.router.navigate(['/']);
    });
  }
}
