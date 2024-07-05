package cristianmartucci.U5_W3_D5_E.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "events")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private String description;
    private LocalDateTime date;
    private String place;
    private long quantity;

    @ManyToOne
    @JoinColumn(name = "user_organizer_id")
    private User userOrganizer;

    @ManyToMany(mappedBy = "eventList")
    private List<User> userList;

    public Event(String title, String description, LocalDateTime date, String place, long quantity, User userOrganizer) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.place = place;
        this.quantity = quantity;
        this.userOrganizer = userOrganizer;
        this.userList = new ArrayList<>();
    }

    public void addUser(User user){
        this.userList.add(user);
    }
}
