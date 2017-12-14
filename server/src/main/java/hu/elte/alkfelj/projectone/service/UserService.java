package hu.elte.alkfelj.projectone.service;

import hu.elte.alkfelj.projectone.entity.RoomMember;
import hu.elte.alkfelj.projectone.entity.User;
import hu.elte.alkfelj.projectone.repository.RoomMemberRepository;
import hu.elte.alkfelj.projectone.repository.UserRepository;
import hu.elte.alkfelj.projectone.service.exceptions.UserNotValidException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import static hu.elte.alkfelj.projectone.entity.User.Role.USER;

@Service
@SessionScope
@Data
public class UserService {
    private UserRepository userRepository;
    private RoomMemberRepository roomMemberRepository;

    private User user;
    public User defaultUser;

    public User login(User user) throws UserNotValidException {
        if (isValid(user)) {
            return this.user = userRepository.findByUsername(user.getUsername()).get();
        }
        throw new UserNotValidException();
    }

    public User register(User user) {
        user.setRole(USER);
        this.user = userRepository.save(user);
        RoomMember roomMember = new RoomMember(0, this.user.getId());
        roomMemberRepository.save(roomMember);
        return user;
    }

    @Autowired
    public UserService(UserRepository userRepository, RoomMemberRepository roomMemberRepository) {
        this.userRepository = userRepository;
        this.roomMemberRepository = roomMemberRepository;
        User user = new User("guest", "guest", User.Role.GUEST);
        user.setRole(User.Role.GUEST);
        this.defaultUser = this.userRepository.save(user);
        this.user = this.defaultUser;
    }

    public boolean isValid(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).isPresent();
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId).get();
    }

    public boolean isLoggedIn() {
        return user != null;
    }
}
