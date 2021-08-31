package api.config;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class CustomUserRole implements GrantedAuthority {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "role")
    private String role;

    @Override
    public String getAuthority() {
        return this.id.toString();
    }
}
