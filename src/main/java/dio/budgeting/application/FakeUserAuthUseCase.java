package dio.budgeting.application;

import dio.budgeting.domain.user.User;
import org.springframework.stereotype.Service;

@Service
public class FakeUserAuthUseCase {

    public User execute(String email) {
        if (email.equals("thiago@test.com")) {
            return new User("thiago@test.com", "asdf4a654sdf65");
        }
        return null;
    }
}
