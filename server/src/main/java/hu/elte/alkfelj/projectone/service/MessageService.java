package hu.elte.alkfelj.projectone.service;

import hu.elte.alkfelj.projectone.entity.Message;
import hu.elte.alkfelj.projectone.entity.User;
import hu.elte.alkfelj.projectone.repository.MessageRepository;
import hu.elte.alkfelj.projectone.service.exceptions.UserNotValidException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

import static hu.elte.alkfelj.projectone.entity.User.Role.USER;

@Service
@SessionScope
@Data
public class MessageService {
    @Autowired
    private MessageRepository msgRepository;

    @Autowired
    private UserService userService;

    private Message msg;

    public List<Message> getMessagesByRoomId(int roomId) {
        return msgRepository.findByRoomId(roomId).get();
    }

    public Message getMessageById(int id) {
        return msgRepository.findById(id).get();
    }

    public Message addMessage(Message message) {
        if(userService.getUser().getRole().equals(User.Role.GUEST)){
            message.setSenderId(userService.getDefaultUser().getId());
        } else {
            message.setSenderId(userService.getUser().getId());
        }
        return msgRepository.save(message);
    }

    public void deleteMessage(Message message) {
        msgRepository.delete(message);
    }
}
