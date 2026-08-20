package dio.budgeting.infrasctructure.http;

import dio.budgeting.infrasctructure.http.request.LoginRequest;
import dio.budgeting.infrasctructure.http.response.AuthResponse;
import dio.budgeting.infrasctructure.security.jwt.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    AuthenticationManager authenticationManager;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                );
        Authentication auth = authenticationManager.authenticate(authToken);

        String jwt = jwtService.generateToken(authToken.getName());

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

}
