package dio.budgeting.application.output;

import dio.budgeting.domain.user.User;

public record UserOutput (
        String id,
        String email,
        String password
) {

    public static UserOutput from(User user) {
        return new UserOutput(
                user.getId().uuid().toString(),
                user.getEmail(),
                user.getPassword());
    }
}
