package hu.elte.alkfelj.projectone.repository;

import hu.elte.alkfelj.projectone.entity.RoomMember;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomMemberRepository extends CrudRepository<RoomMember, Integer> {
    Optional<List<RoomMember>> findByRoomIdAndUserId(int roomId, int userId);
    Optional<List<RoomMember>> findByUserId(int userId);
}