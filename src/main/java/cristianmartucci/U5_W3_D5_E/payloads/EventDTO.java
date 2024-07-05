package cristianmartucci.U5_W3_D5_E.payloads;

import cristianmartucci.U5_W3_D5_E.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventDTO(
        @NotBlank(message = "Titolo obbligatorio")
        String title,
        @NotBlank(message = "Descrizione obbligatoria")
        String description,
        @NotBlank(message = "Data obbligatoria")
        LocalDateTime date,
        @NotBlank(message = "Luogo obbligatorio")
        String place,
        @NotBlank(message = "Numero di partecipanti obbligatorio")
        long quantity,
        @NotNull(message = "UUID obbligatorio")
        @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$", message = "Formato UUID non valido")
        String userOrganizerId) {
}
