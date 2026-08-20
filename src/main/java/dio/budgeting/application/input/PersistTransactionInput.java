package dio.budgeting.application.input;

import dio.budgeting.domain.transaction.Category;
import org.springframework.ai.tool.annotation.ToolParam;

public record PersistTransactionInput(
        @ToolParam(description = "Descrição da despesa.") String description,
        @ToolParam(description = "Categoria da despesa") Category category,
        @ToolParam(description = "Valor da despesa, em centavos. E.: 4 reais = 400; 50 reais = 5000.") long amount) {
}
