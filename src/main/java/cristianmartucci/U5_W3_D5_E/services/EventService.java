package cristianmartucci.U5_W3_D5_E.services;

import cristianmartucci.U5_W3_D5_E.entities.Event;
import cristianmartucci.U5_W3_D5_E.entities.User;
import cristianmartucci.U5_W3_D5_E.exceptions.BadRequestException;
import cristianmartucci.U5_W3_D5_E.exceptions.NotFoundException;
import cristianmartucci.U5_W3_D5_E.payloads.EventDTO;
import cristianmartucci.U5_W3_D5_E.payloads.EventResponseDTO;
import cristianmartucci.U5_W3_D5_E.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserService userService;

    public Event save(EventDTO eventDTO){
        if (this.eventRepository.findByPlaceAndDate(eventDTO.place(), eventDTO.date()).isEmpty()){
            Event event = new Event(eventDTO.title(), eventDTO.description(), eventDTO.date(), eventDTO.place(), eventDTO.quantity(), this.userService.findByID(UUID.fromString(eventDTO.userOrganizerId())));
            return this.eventRepository.save(event);
        }else throw new BadRequestException("Evento già organizzato in data " + eventDTO.date() + " e luogo " + eventDTO.place());
    }

    public Page<Event> getAllEvent(int pageNumber, int pageSize, String sortBy){
        if(pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return this.eventRepository.findAll(pageable);
    }

    public Event findByID(UUID eventId){
        return this.eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException(eventId));
    }

    public Event update(UUID id, EventDTO updateEvent){
        Event event = this.findByID(id);
        event.setTitle(updateEvent.title());
        event.setPlace(updateEvent.place());
        event.setDate(updateEvent.date());
        event.setQuantity(updateEvent.quantity());
        event.setDescription(updateEvent.description());
        event.setUserOrganizer(this.userService.findByID(UUID.fromString(updateEvent.userOrganizerId())));
        return this.eventRepository.save(event);
    }

    public void delete(UUID eventId) {
        Event event = this.findByID(eventId);
        this.eventRepository.delete(event);
    }

//    public Event addUserToEvent(UUID currentUser, EventResponseDTO eventResponseDTO){
//        Event event = this.findByID(eventResponseDTO.eventId());
//        List<User> list = event.getUserList();
//        event.setUserList(event.getUserList(), event.);
//        event.addUser(this.userService.findByID(currentUser));
//        return this.eventRepository.save(event);
//    }
}
