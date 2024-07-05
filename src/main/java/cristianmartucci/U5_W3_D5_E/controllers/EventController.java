package cristianmartucci.U5_W3_D5_E.controllers;

import cristianmartucci.U5_W3_D5_E.entities.Event;
import cristianmartucci.U5_W3_D5_E.exceptions.BadRequestException;
import cristianmartucci.U5_W3_D5_E.payloads.EventDTO;
import cristianmartucci.U5_W3_D5_E.payloads.EventResponseDTO;
import cristianmartucci.U5_W3_D5_E.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('EVENT_ORGANIZER', 'NORMAL_USER')")
    public Page<Event> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy){
        return this.eventService.getAllEvent(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('EVENT_ORGANIZER')")
    public EventResponseDTO save(@RequestBody @Validated EventDTO eventDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return new EventResponseDTO(this.eventService.save(eventDTO).getId());
    }

    @PutMapping("/{eventId}")
    @PreAuthorize("hasAuthority('EVENT_ORGANIZER')")
    public Event update(@PathVariable UUID eventId, @RequestBody EventDTO body) {
        return this.eventService.update(eventId, body);
    }

    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('EVENT_ORGANIZER')")
    public void delete(@PathVariable UUID eventId) {
        this.eventService.delete(eventId);
    }
}
