package cristianmartucci.U5_W3_D5_E.payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime time) {
}
