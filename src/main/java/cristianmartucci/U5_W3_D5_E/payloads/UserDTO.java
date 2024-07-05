package cristianmartucci.U5_W3_D5_E.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserDTO(@NotEmpty(message = "Email obbligatoria")
                      @Email(message = "Email non valida")
                      String email,
                      @NotEmpty(message = "Password obbligatoria")
                      String password) {
}
