import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {User} from "../types/User";
import {Routes, ServerRoutes} from "../../routes";
import 'rxjs/add/operator/map';

@Injectable()
export class UserService {
  private currentUser: User = null;
  isLoggedIn: boolean;

  constructor(private http: Http) {
  }

  login(user: User) {
    return this.http.post(ServerRoutes.routeTo(Routes.LOGIN), user)
      .map(res => {
        this.isLoggedIn = true;
        this.currentUser = res.json();
        return this.currentUser;
      })
  }

  register(user: User) {
    return this.http.post(ServerRoutes.routeTo(Routes.REGISTER), user)
      .map(res => {
        this.isLoggedIn = true;
        this.currentUser = res.json();
        return this.currentUser;
      })
  }

  logout() {
    return this.http.post(ServerRoutes.routeTo(Routes.LOGOUT), {})
      .map(res => {
        this.currentUser = new User();
        this.isLoggedIn = false;
      })
  }
}
