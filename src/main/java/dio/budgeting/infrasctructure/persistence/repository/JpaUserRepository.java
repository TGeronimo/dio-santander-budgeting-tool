package dio.budgeting.infrasctructure.persistence.repository;

import dio.budgeting.domain.user.User;
import dio.budgeting.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    private UserEntityRepository userEntityRepository;

    public JpaUserRepository(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public Optional<User> findByEmail(String email) {

        return Optional.empty();
    }
}
