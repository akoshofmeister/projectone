package hu.elte.alkfelj.projectone.repository;

import hu.elte.alkfelj.projectone.entity.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {
    Optional<List<Message>> findByRoomId(int roomId);
    Optional<Message> findById(int id);
}