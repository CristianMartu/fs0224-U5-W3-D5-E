package cristianmartucci.U5_W3_D5_E.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException{
    public NotFoundException(UUID id) {
        super(id + " non trovato!");
    }

    public NotFoundException(String message){
        super(message);
    }
}
