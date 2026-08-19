package dio.budgeting.infrasctructure.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/auth")
    public String auth() {
        return "Where the auth, dude!?";
    }
}
