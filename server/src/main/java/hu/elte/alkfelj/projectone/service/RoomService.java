package hu.elte.alkfelj.projectone.service;

import hu.elte.alkfelj.projectone.entity.Room;
import hu.elte.alkfelj.projectone.repository.RoomRepository;
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
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    private Room room;

    public List<Room> getRoomsByOwnerId(int roomId) {
        return roomRepository.findByOwnerId(roomId).get();
    }

    public Room getRoomById(int roomId) { return roomRepository.findById(roomId).get(); }
}
