package dio.budgeting.application;

import dio.budgeting.application.input.PersistUserInput;
import dio.budgeting.application.output.UserOutput;
import dio.budgeting.domain.user.User;
import dio.budgeting.domain.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserOutput register(PersistUserInput userInput) {
        if (userRepository.existsByEmail(userInput.email())) {
            throw new IllegalArgumentException("Email já cadastrado!");
        }

        String hashed = passwordEncoder.encode(userInput.password());

        var user = userRepository.save(new User(userInput.email(), hashed));

        return UserOutput.from(user);
    }
}
