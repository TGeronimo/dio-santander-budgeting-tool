package dio.budgeting.infrasctructure.http.response;

import dio.budgeting.application.output.TransactionOutput;
import dio.budgeting.application.output.UserOutput;

public record UserResponse(
        String id,
        String email,
        String password
) {

    public static UserResponse from(UserOutput output) {
        return new UserResponse(
          output.id(),
          output.email(),
        output.password()
        );
    }
}
