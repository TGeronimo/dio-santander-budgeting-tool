package dio.budgeting;

import dio.budgeting.infrasctructure.security.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JwtAuthenticationFilterIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtService jwtService;

    @Test
    void shouldAllowAccessWithValidToken() throws Exception {
        String token = jwtService.generateAccessToken("thiago@test.com");

        mockMvc.perform(get("/transactions/GROCERIES")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
}

