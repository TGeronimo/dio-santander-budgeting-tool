package dio.budgeting.infrasctructure.persistence.entity;

import dio.budgeting.domain.Category;
import dio.budgeting.domain.Transaction;
import dio.budgeting.domain.TransactionId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data //TODO: check this warning and improve the code
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
    @Id
    private UUID id;
    private String description;
    private long amount; // long, pois vamos trabalhar com centavos

    @Enumerated(EnumType.STRING) // sem a anotação, o BD definiu a coluna como tinyint
    private Category category;


    // Mapper
    public static TransactionEntity from(Transaction transaction) {
        return new TransactionEntity(transaction.getId().uuid(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getCategory());
    }

    public Transaction toDomain() {
        return new Transaction(
                new TransactionId(this.id),
                this.description,
                this.amount,
                this.category
        );
    }
}
