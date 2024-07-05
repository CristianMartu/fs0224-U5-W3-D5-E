package cristianmartucci.U5_W3_D5_E.services;

import cristianmartucci.U5_W3_D5_E.entities.User;
import cristianmartucci.U5_W3_D5_E.exceptions.BadRequestException;
import cristianmartucci.U5_W3_D5_E.exceptions.NotFoundException;
import cristianmartucci.U5_W3_D5_E.payloads.UserDTO;
import cristianmartucci.U5_W3_D5_E.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(UserDTO userDTO){
        if (this.userRepository.findByEmail(userDTO.email()).isEmpty()){
            User user = new User(userDTO.email(), passwordEncoder.encode(userDTO.password()));
            return this.userRepository.save(user);
        } else throw new BadRequestException("Email già in uso!");
    }

    public User findByID(UUID userId){
        return this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }
}
