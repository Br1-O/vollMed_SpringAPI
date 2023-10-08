package med.voll.api.model.repositories;

import med.voll.api.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String username);
}
