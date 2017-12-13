package hu.elte.alkfelj.projectone.service;

import hu.elte.alkfelj.projectone.entity.Message;
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

    private Message msg;

    public List<Message> getMessagesByRoomId(int roomId) {
        return msgRepository.findByRoomId(roomId).get();
    }
}
