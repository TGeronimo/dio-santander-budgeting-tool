package dio.budgeting.infrasctructure.http.response;

import dio.budgeting.application.output.UserOutput;

import java.util.UUID;

public record UserRegisterResponse (
        String id,
        String email,
        String password
){

    public static UserRegisterResponse from(UserOutput userOutput){
        return new UserRegisterResponse(
                userOutput.id(),
                userOutput.email(),
                userOutput.password()
        );
    }
}
