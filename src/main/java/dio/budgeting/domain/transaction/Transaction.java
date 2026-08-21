package dio.budgeting.domain.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class Transaction {
    private IdGenerator id;
    private String description;
    private long amount; // long, pois vamos trabalhar com centavos
    private Category category;
    private Instant occurredAt;

    public Transaction(String description, long amount, Category category) {
        this.id = new IdGenerator();
        this.description = description;
        this.amount = amount;
        this.category = category;
    }
}
