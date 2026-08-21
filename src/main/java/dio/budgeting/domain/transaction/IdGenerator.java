package dio.budgeting.domain.transaction;

import java.util.UUID;

public record IdGenerator(UUID uuid) {
    public IdGenerator() {
        this(UUID.randomUUID());
    }

}
