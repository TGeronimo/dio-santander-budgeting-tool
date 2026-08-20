package dio.budgeting;

import dio.budgeting.infrasctructure.security.jwt.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setup() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secret", "uma_chave_bem_grande_e_secreta_aqui");
    }

    @Test
    void shouldGenerateValidAccessToken() {
        String email = "thiago@test.com";

        String token = jwtService.generateAccessToken(email);

        assertNotNull(token);
        assertFalse(token.isBlank());

        String subject = jwtService.extractSubject(token);
        assertEquals(email, subject);
    }

    @Test
    void shouldGenerateValidRefreshToken() {
        String email = "thiago@test.com";

        String token = jwtService.generateRefreshToken(email);

        assertNotNull(token);
        assertFalse(token.isBlank());

        String subject = jwtService.extractSubject(token);
        assertEquals(email, subject);
    }

    @Test
    void shouldExtractSubjectCorrectly() {
        String email = "thiago@test.com";

        String token = jwtService.generateAccessToken(email);

        String subject = jwtService.extractSubject(token);

        assertEquals(email, subject);
    }
}

