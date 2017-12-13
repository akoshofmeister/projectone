package hu.elte.alkfelj.projectone.service;

import hu.elte.alkfelj.projectone.entity.Room;
import hu.elte.alkfelj.projectone.entity.RoomMember;
import hu.elte.alkfelj.projectone.repository.RoomMemberRepository;
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
    @Autowired
    private RoomMemberRepository roomMemberRepository;

    @Autowired
    private UserService userService;

    private Room room;

    public List<Room> getRoomsByOwnerId(int roomId) {
        return roomRepository.findByOwnerId(roomId).get();
    }

    public Room getRoomById(int roomId) {
        return roomRepository.findById(roomId).get();
    }

    public Room addRoom(Room room) {
        room.setOwnerId(userService.getUser().getId());
        Room r = roomRepository.save(room);
        RoomMember roomMember = new RoomMember(r.getId(), userService.getUser().getId());
        roomMemberRepository.save(roomMember);
        return r;
    }

    public Room editRoom(Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoom(Room room) {
        roomRepository.delete(room);
    }
}
