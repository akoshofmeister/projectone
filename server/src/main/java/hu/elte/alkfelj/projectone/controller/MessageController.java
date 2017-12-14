package hu.elte.alkfelj.projectone.controller;

import hu.elte.alkfelj.projectone.entity.Message;
import hu.elte.alkfelj.projectone.entity.Room;
import hu.elte.alkfelj.projectone.entity.User;
import hu.elte.alkfelj.projectone.service.MessageService;
import hu.elte.alkfelj.projectone.service.RoomMemberService;
import hu.elte.alkfelj.projectone.service.RoomService;
import hu.elte.alkfelj.projectone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import hu.elte.alkfelj.projectone.service.annotations.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hu.elte.alkfelj.projectone.entity.User.Role.ADMIN;
import static hu.elte.alkfelj.projectone.entity.User.Role.GUEST;
import static hu.elte.alkfelj.projectone.entity.User.Role.USER;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;
    private final RoomService roomService;
    private final UserService userService;
    private final RoomMemberService roomMemberService;

    @Autowired
    public MessageController(MessageService messageService, RoomService roomService, UserService userService, RoomMemberService roomMemberService) {
        this.messageService = messageService;
        this.roomService = roomService;
        this.userService = userService;
        this.roomMemberService = roomMemberService;
    }

    @Role({USER, ADMIN, GUEST})
    @GetMapping()
    public ResponseEntity<List<Message>> getMessages(@RequestParam("roomid") int roomId) {
        User user = userService.getUser();
        Room room = roomService.getRoomById(roomId);
        if(room.getOwnerId() == user.getId() || user.getRole().equals(User.Role.ADMIN) ||
                (this.userService.getUser().getRole().equals(User.Role.GUEST) && roomId == 0) ||
                this.roomMemberService.userIsMemberOfRoom(user, room))
        {
            return ResponseEntity.ok(messageService.getMessagesByRoomId(roomId));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @Role({USER, ADMIN})
    @DeleteMapping()
    public ResponseEntity deleteMessage(@RequestParam("messageId") int msgId) {
        Message msg = messageService.getMessageById(msgId);
        Room room = roomService.getRoomById(msg.getRoomId());
        User user = userService.getUser();
        if(room.getOwnerId() == user.getId() || user.getRole().equals(User.Role.ADMIN)) {
            messageService.deleteMessage(msg);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Role({USER, ADMIN})
    @PostMapping()
    public ResponseEntity<Message> postMessage(@RequestBody Message message) {
        return ResponseEntity.ok(messageService.addMessage(message));
    }
}