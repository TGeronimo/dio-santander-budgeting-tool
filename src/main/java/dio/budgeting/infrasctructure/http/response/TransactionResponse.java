package dio.budgeting.infrasctructure.http.response;

import dio.budgeting.application.output.TransactionOutput;

import java.time.Instant;

public record TransactionResponse(String id,
                                  String description,
                                  String category,
                                  double amount,
                                  Instant createdAt) {

    public static TransactionResponse from(TransactionOutput output) {
        return new TransactionResponse(
                output.id(),
                output.description(),
                output.category(),
                output.amount(),
                output.createdAt());
    }
}
