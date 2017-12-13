package hu.elte.alkfelj.projectone.service;


import hu.elte.alkfelj.projectone.entity.Room;
import hu.elte.alkfelj.projectone.entity.RoomMember;
import hu.elte.alkfelj.projectone.entity.User;
import hu.elte.alkfelj.projectone.repository.RoomMemberRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Service
@SessionScope
@Data
public class RoomMemberService {
    @Autowired
    private RoomMemberRepository roomMemberRepository;

    public boolean userIsMemberOfRoom(User user, Room room) {
        return roomMemberRepository.findByRoomIdAndUserId(room.getId(), user.getId()).get().size() > 0;
    }

    public List<RoomMember> getRoomsForUser(User user) {
        return roomMemberRepository.findByUserId(user.getId()).get();
    }
}
