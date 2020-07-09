package inv.iashinyh.jpa.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
        name = "role",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"username", "role"})
        })
@NoArgsConstructor
@Setter
@Getter
@ToString
public class RoleEntity extends SystemEntity {
    private String username;
    private String role;
}
