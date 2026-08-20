package dio.budgeting.infrasctructure.security.authentication;

import dio.budgeting.application.FakeUserAuthUseCase;
import dio.budgeting.domain.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    private final FakeUserAuthUseCase fakeUserAuthUseCase;

    public AuthUserDetailsService(FakeUserAuthUseCase fakeUserAuthUseCase) {
        this.fakeUserAuthUseCase = fakeUserAuthUseCase;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = fakeUserAuthUseCase.execute(email);

        if (user == null) {
            throw new UsernameNotFoundException("Usuário" + email + " não  encontrado!");
        }

        return new AuthUserDetails(user);
    }
}
