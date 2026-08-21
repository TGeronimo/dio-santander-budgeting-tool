package dio.budgeting.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    boolean existsByEmail(String email);
    User save(User user);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}
