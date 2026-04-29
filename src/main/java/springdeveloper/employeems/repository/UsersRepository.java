package springdeveloper.employeems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springdeveloper.employeems.model.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);
    boolean existsByUsername(String username);
}
