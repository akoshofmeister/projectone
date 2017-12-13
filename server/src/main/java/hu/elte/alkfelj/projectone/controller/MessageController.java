package hu.elte.alkfelj.projectone.controller;

import hu.elte.alkfelj.projectone.entity.Message;
import hu.elte.alkfelj.projectone.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import hu.elte.alkfelj.projectone.service.annotations.Role;
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

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping()
    public ResponseEntity<List<Message>> getMessages(@RequestParam("roomid") int roomId) {
        return ResponseEntity.ok(messageService.getMessagesByRoomId(roomId));
    }
}