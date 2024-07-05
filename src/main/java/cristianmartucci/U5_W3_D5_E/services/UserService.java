package cristianmartucci.U5_W3_D5_E.services;

import cristianmartucci.U5_W3_D5_E.entities.User;
import cristianmartucci.U5_W3_D5_E.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        return this.userRepository.save(user);
    }

    public User findByID(UUID userId){
        return this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException());
    }
}
