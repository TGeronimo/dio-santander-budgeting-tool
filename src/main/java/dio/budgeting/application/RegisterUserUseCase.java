package dio.budgeting.application;

import dio.budgeting.application.input.PersistUserInput;
import dio.budgeting.application.output.UserOutput;
import dio.budgeting.domain.user.User;

public interface RegisterUserUseCase {
    UserOutput register(PersistUserInput userInput);
}
