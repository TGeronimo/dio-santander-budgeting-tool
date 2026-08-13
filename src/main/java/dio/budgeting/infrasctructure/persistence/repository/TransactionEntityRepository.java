package dio.budgeting.infrasctructure.persistence.repository;

import dio.budgeting.domain.Category;
import dio.budgeting.infrasctructure.persistence.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionEntityRepository extends CrudRepository<TransactionEntity, UUID> {
    List<TransactionEntity> findAllByCategory(Category category);
}
