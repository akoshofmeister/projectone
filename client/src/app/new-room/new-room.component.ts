import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import {Room} from "../types/Room";
import {RoomService} from "../services/room.service";

@Component({
  selector: 'new-room',
  templateUrl: './new-room.component.html',
  styleUrls: ['./new-room.component.css']
})
export class NewRoomComponent {
  newRoomForm: FormGroup;
  constructor(private fb: FormBuilder,
              private router: Router,
              private roomService: RoomService) {
    this.newRoomForm = this.fb.group({
      name: ['',Validators.required],
    });
  }

  newroom() {
    const val = this.newRoomForm.value;
    if (val.name) {
      const room = new Room();
      room.name = val.name;
      this.roomService.create(room)
        .subscribe(
          (resp) => {
            console.log("Room created", resp);
            this.router.navigateByUrl(`/room/${resp.id}`);
          }
        );
    }
  }
}
