package dio.budgeting.infrasctructure.persistence.repository;

import dio.budgeting.domain.user.User;
import dio.budgeting.infrasctructure.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserEntityRepository extends CrudRepository<User, UUID> {
    boolean existsByEmail(String email);
    Optional<UserEntity> findByEmail(String email);

}
