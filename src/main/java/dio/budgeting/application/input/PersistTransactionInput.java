package dio.budgeting.application.input;

import dio.budgeting.domain.Category;
import org.springframework.ai.tool.annotation.ToolParam;

import java.time.Instant;

public record PersistTransactionInput(@ToolParam(description = "Descrição da despesa.") String description,
                                      @ToolParam(description = "Categoria da despesa") Category category,
                                      @ToolParam(description = "Valor da despesa") long amount) {
}
