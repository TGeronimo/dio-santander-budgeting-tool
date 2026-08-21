package dio.budgeting.infrasctructure.persistence.repository;

import dio.budgeting.domain.user.User;
import dio.budgeting.domain.user.UserRepository;
import dio.budgeting.infrasctructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    private final UserEntityRepository userEntityRepository;

    public JpaUserRepository(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userEntityRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return userEntityRepository.save(UserEntity.from(user)).toDomain();

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userEntityRepository.findByEmail(email).map(UserEntity::toDomain);
    }
}
