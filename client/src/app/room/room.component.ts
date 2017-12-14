import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {RoomService} from "../services/room.service";
import {MessagesService} from "../services/messages.service";
import {Room} from "../types/Room";
import {User} from "../types/User";
import {UserService} from "../services/user.service";
import {Message} from "../types/Message";

@Component({
  selector: 'room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {
  msgs: Message[] = [];
  userId: number;
  roomId: number;
  room: Room;
  currentMessage: string;

  constructor(private activatedRoute: ActivatedRoute,
              private userService: UserService,
              private roomService: RoomService,
              private messagesService: MessagesService) {
  }

  ngOnInit() {
    this.userId = this.userService.getUserId();
    this.activatedRoute.params.subscribe(params => {
      this.roomId = params['id'];
      this.roomService.getById(this.roomId).subscribe((res) => {
        this.room = res;
      })
    });
    this.updateMessages();
  }

  updateMessages() {
    this.messagesService.getRoomMessages(this.roomId).subscribe((res) => {
      this.msgs = res;
    });
  }

  sendMessage() {
    this.messagesService.add({
      content: this.currentMessage,
      timestamp: null,
      roomId: this.roomId
    }).subscribe(() => {
      this.updateMessages();
    });
    this.currentMessage = "";
  }
}
