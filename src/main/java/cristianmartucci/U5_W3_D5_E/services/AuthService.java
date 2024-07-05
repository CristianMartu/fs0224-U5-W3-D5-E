package cristianmartucci.U5_W3_D5_E.services;

import cristianmartucci.U5_W3_D5_E.entities.User;
import cristianmartucci.U5_W3_D5_E.exceptions.UnauthorizedException;
import cristianmartucci.U5_W3_D5_E.payloads.UserDTO;
import cristianmartucci.U5_W3_D5_E.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder passwordEncoder;;

    public String authGenerateToken(UserDTO userDTO){
        User user = this.userService.findByEmail(userDTO.email());
        if (this.passwordEncoder.matches(userDTO.password(), user.getPassword())){
            return this.jwtTools.createToken(user);
        } else throw new UnauthorizedException("Credenziali sbagliate!");
    }
}
