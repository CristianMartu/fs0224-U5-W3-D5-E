package cristianmartucci.U5_W3_D5_E.services;

import cristianmartucci.U5_W3_D5_E.entities.Event;
import cristianmartucci.U5_W3_D5_E.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.util.UUID;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event save(Event event){
        return this.eventRepository.save(event);
    }

    public Page<Event> getAllEvent(int pageNumber, int pageSize, String sortBy){
        if(pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return this.eventRepository.findAll(pageable);
    }

    public Event findByID(UUID eventId){
        return this.eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException());
    }

    public void delete(UUID eventId) {
        Event event = this.findByID(eventId);
        this.eventRepository.delete(event);
    }
}
