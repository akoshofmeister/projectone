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
     this.http.get(ServerRoutes.routeTo(Routes.USERS))
      .map(res =>{
          this.currentUser = res.json();
          console.log(this.currentUser);
        }
      );
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
    return this.http.post(ServerRoutes.routeTo(Routes.LOGOUT), this.currentUser)
      .map(res => {
        this.currentUser = res.json();
        this.isLoggedIn = false;
      })
  }

  getUsername() {
    return this.currentUser.username;
  }

  getUserId() {
    return this.currentUser ? this.currentUser.id : -1;
  }

  getUserById(id: number) {
    return this.http.get(`${ServerRoutes.routeTo(Routes.USERS)}/${id}`)
      .map(res =>
        res.json()
      )
  }
}
