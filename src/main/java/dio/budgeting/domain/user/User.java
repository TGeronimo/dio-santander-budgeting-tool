package dio.budgeting.domain.user;

import dio.budgeting.domain.transaction.IdGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private IdGenerator id;
    private String email;
    private String password;

    public User (String email, String password) {
        this.id = new IdGenerator();
        this.email = email;
        this.password = password;
    }
}


