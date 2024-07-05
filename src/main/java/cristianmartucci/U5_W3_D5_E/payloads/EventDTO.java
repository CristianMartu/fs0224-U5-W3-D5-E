package cristianmartucci.U5_W3_D5_E.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record EventDTO(
        @NotBlank(message = "Titolo obbligatorio")
        String title,
        @NotBlank(message = "Descrizione obbligatoria")
        String description,
        @NotNull(message = "Data obbligatoria")
        LocalDateTime date,
        @NotBlank(message = "Luogo obbligatorio")
        String place,
        @NotNull(message = "Numero di partecipanti obbligatorio")
        long quantity,
        @NotNull(message = "UUID obbligatorio")
        @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$", message = "Formato UUID non valido")
        String userOrganizerId) {
}
