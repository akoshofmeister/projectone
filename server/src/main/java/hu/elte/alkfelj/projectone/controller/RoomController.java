package hu.elte.alkfelj.projectone.controller;

import hu.elte.alkfelj.projectone.entity.Room;
import hu.elte.alkfelj.projectone.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import hu.elte.alkfelj.projectone.service.annotations.Role;
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

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping()
    public ResponseEntity<Room> getRoomById(@RequestParam("roomid") int roomId) {
        return ResponseEntity.ok(roomService.getRoomById(roomId));
    }
}