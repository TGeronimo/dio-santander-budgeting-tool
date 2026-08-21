package dio.budgeting.infrasctructure.http.request;

import dio.budgeting.application.input.PersistUserInput;

public record RegisterRequest(
        String email,
        String password
) {

    public PersistUserInput toInput(){
        return new PersistUserInput(email(), password());
    }
}
