package dio.budgeting.infrasctructure.persistence.repository;

import dio.budgeting.domain.user.User;
import dio.budgeting.domain.user.UserRepository;
import dio.budgeting.infrasctructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
        return userEntityRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        var entity = UserEntity.from(user);
        var saved = userEntityRepository.save(entity);
        return saved.toDomain();
    }

    @Override
    public List<User> findAll() {
        var list = new ArrayList<User>();
        for (UserEntity e : userEntityRepository.findAll()) {
            list.add(e.toDomain());
        }
        return list;
    }

    @Override
    public Optional<User> findByEmail(String email) {

        return userEntityRepository.findByEmail(email).map(UserEntity::toDomain);
    }
}
