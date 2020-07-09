package inv.iashinyh.jpa.entity;

import lombok.*;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"username"})
        })
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserEntity extends SystemEntity {

    private String username;
    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "username", referencedColumnName = "username", updatable = false)
    private List<RoleEntity> roles = new ArrayList<>();

    public void setRoles(List<RoleEntity> roles) {
        this.roles.clear();
        this.roles.addAll(roles);
    }
}
