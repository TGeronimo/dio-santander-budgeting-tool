package dio.budgeting.infrasctructure.persistence.entity;

import dio.budgeting.domain.transaction.IdGenerator;
import dio.budgeting.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    private UUID id;
    private String email;
    private String password;

    public static UserEntity from(User user) {
        return new UserEntity(
                new IdGenerator().uuid(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public User toDomain(){
        return new User(
                new IdGenerator(this.id),
                this.email,
                this.password
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;
        return id.equals(that.id) && email.equals(that.email) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
