package dio.budgeting.infrasctructure.persistence.entity;

import dio.budgeting.domain.transaction.Category;
import dio.budgeting.domain.transaction.Transaction;
import dio.budgeting.domain.transaction.TransactionId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
    @Id
    private UUID id;
    private String description;
    private long amount; // long, pois vamos trabalhar com centavos

    @Enumerated(EnumType.STRING) // sem a anotação, o BD definiu a coluna como tinyint
    private Category category;

    @Column(
            name = "created_at",
            columnDefinition = "DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6)",
            insertable = false,
            updatable = false
    )
    private Instant createdAt;

    // Mapper
    public static TransactionEntity from(Transaction transaction) {
        return new TransactionEntity(transaction.getId().uuid(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getCategory(),
                transaction.getOccurredAt()
                );
    }

    public Transaction toDomain() {
        return new Transaction(
                new TransactionId(this.id),
                this.description,
                this.amount,
                this.category,
                this.createdAt
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionEntity that = (TransactionEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
