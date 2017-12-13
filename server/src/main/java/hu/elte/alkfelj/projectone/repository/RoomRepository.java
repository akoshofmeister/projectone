package hu.elte.alkfelj.projectone.repository;

import hu.elte.alkfelj.projectone.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {
    Optional<List<Room>> findByOwnerId(int ownerId);
    Optional<Room> findById(int id);
}