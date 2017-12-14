import { Component, OnInit } from '@angular/core';
import {UserService} from "../services/user.service";
import {Router} from "@angular/router";
import {Room} from "../types/Room";
import {RoomService} from "../services/room.service";
import {RoomMember} from "../types/RoomMember";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  private username: string = 'unknown';
  private rooms: Room[];
  constructor(private userService: UserService,
              private roomService: RoomService,
              private router: Router) { }

  ngOnInit() {
    this.username = this.userService.getUsername();

    this.roomService.getRoomsForUser().subscribe(res =>{
      let r = res;
      for(let i of r) {
        this.roomService.getById(i.roomId).subscribe((room) => {
          i.name = room.name;
        });
      }
      this.rooms = r;
    });
  }

  logout() {
    this.userService.logout().subscribe(() => {
      console.log('User logged out');
      this.router.navigate(['/']);
    });
  }
}
