package cristianmartucci.U5_W3_D5_E.controllers;

import cristianmartucci.U5_W3_D5_E.exceptions.BadRequestException;
import cristianmartucci.U5_W3_D5_E.payloads.UserDTO;
import cristianmartucci.U5_W3_D5_E.payloads.UserLoginResponseDTO;
import cristianmartucci.U5_W3_D5_E.payloads.UserResponseDTO;
import cristianmartucci.U5_W3_D5_E.services.AuthService;
import cristianmartucci.U5_W3_D5_E.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/user")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO save(@RequestBody @Validated UserDTO userDTO, BindingResult validationResult){
        if (validationResult.hasErrors()){
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        return new UserResponseDTO(this.userService.save(userDTO).getId());
    }

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserDTO userDTO){
        return new UserLoginResponseDTO(this.authService.authGenerateToken(userDTO));
    }
}
