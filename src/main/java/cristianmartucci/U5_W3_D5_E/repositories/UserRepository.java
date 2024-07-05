package cristianmartucci.U5_W3_D5_E.repositories;

import cristianmartucci.U5_W3_D5_E.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
