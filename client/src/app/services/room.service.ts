import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {Routes, ServerRoutes} from "../../routes";
import 'rxjs/add/operator/map';
import {Room} from "../types/Room";

@Injectable()
export class RoomService {
  constructor(private http: Http) {
  }

  create(room: Room) {
    return this.http.post(ServerRoutes.routeTo(Routes.ROOMS), room).map(res=>res.json());
  }

  getRoomsForUser() {
    return this.http.get(`${ServerRoutes.routeTo(Routes.ROOMS)}/forUser`).map(res=>res.json());
  }

  getById(id: number) {
    return this.http.get(`${ServerRoutes.routeTo(Routes.ROOMS)}/?roomid=${id}`).map(res=>res.json());
  }
}
