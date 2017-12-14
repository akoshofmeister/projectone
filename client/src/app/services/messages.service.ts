import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {Routes, ServerRoutes} from "../../routes";
import 'rxjs/add/operator/map';
import {Message} from "../types/Message";
import {UserService} from "./user.service";

@Injectable()
export class MessagesService {
  constructor(private http: Http,
              private userService: UserService) {
  }

  add(message: Message) {
    return this.http.post(ServerRoutes.routeTo(Routes.MESSAGES), message).map(res => res.json());
  }

  getRoomMessages(id: number) {
    return this.http.get(`${ServerRoutes.routeTo(Routes.MESSAGES)}/?roomid=${id}`).map(
      res => {
        let msgs = res.json();
        for (let msg of msgs) {
          this.userService.getUserById(msg.senderId).subscribe((res) => {
              msg.senderName = res.username;
            }
          )
        }
        return msgs;
      }
    );
  }
}
