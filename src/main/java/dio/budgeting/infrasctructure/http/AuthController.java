package dio.budgeting.infrasctructure.http;

import dio.budgeting.application.RegisterUserUseCase;
import dio.budgeting.infrasctructure.http.request.LoginRequest;
import dio.budgeting.infrasctructure.http.request.RefreshRequest;
import dio.budgeting.infrasctructure.http.request.UserRegisterRequest;
import dio.budgeting.infrasctructure.http.response.AuthResponse;
import dio.budgeting.infrasctructure.http.response.UserResponse;
import dio.budgeting.infrasctructure.security.jwt.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RegisterUserUseCase registerUserUseCase;

    public AuthController(JwtService jwtService, AuthenticationManager authenticationManager, RegisterUserUseCase registerUserUseCase) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRegisterRequest userRequest) {
        var user = registerUserUseCase.register(userRequest.toInput());

        return ResponseEntity.ok(UserResponse.from(user));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                );
        Authentication auth = authenticationManager.authenticate(authToken);

        String accessToken = jwtService.generateAccessToken(auth.getName());
        String refreshToken = jwtService.generateRefreshToken(auth.getName());

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest refreshRequest) {

        String email = jwtService.extractSubject(refreshRequest.refreshToken());

        String accessToken = jwtService.generateAccessToken(email);
        String refreshToken = jwtService.generateRefreshToken(email);

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

}

