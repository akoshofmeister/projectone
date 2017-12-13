package hu.elte.alkfelj.projectone.controller;

import hu.elte.alkfelj.projectone.entity.Room;
import hu.elte.alkfelj.projectone.entity.RoomMember;
import hu.elte.alkfelj.projectone.entity.User;
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
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;
    private final UserService userService;
    private final RoomMemberService roomMemberService;

    @Autowired
    public RoomController(RoomService roomService, UserService userService, RoomMemberService roomMemberService) {
        this.roomService = roomService;
        this.userService = userService;
        this.roomMemberService = roomMemberService;
    }

    @Role({USER, ADMIN, GUEST})
    @GetMapping()
    public ResponseEntity<Room> getRoomById(@RequestParam("roomid") int roomId) {
        if(userService.getUser().getRole().equals(User.Role.GUEST) && roomId != 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } else {
            return ResponseEntity.ok(roomService.getRoomById(roomId));
        }
    }

    @Role({USER, ADMIN})
    @PostMapping()
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        return ResponseEntity.ok(roomService.addRoom(room));
    }

    @Role({USER, ADMIN})
    @DeleteMapping()
    public ResponseEntity deleteRoom(@RequestBody Room room) {
        User user = userService.getUser();
        if(room.getOwnerId() == user.getId() || user.getRole().equals(User.Role.ADMIN)) {
            roomService.deleteRoom(room);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Role({USER, ADMIN})
    @PutMapping()
    public ResponseEntity<Room> editRoom(@RequestBody Room room) {
        User user = userService.getUser();
        if(room.getOwnerId() == user.getId() || user.getRole().equals(User.Role.ADMIN)) {
            Room r = roomService.editRoom(room);
            return ResponseEntity.ok(r);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Role({USER, ADMIN})
    @GetMapping("forUser")
    public ResponseEntity<List<RoomMember>> getRoomsForUser() {
        return ResponseEntity.ok(roomMemberService.getRoomsForUser(userService.getUser()));
    }
}