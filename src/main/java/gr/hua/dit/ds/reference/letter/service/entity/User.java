package gr.hua.dit.ds.reference.letter.service.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private int enabled;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private Collection<Authorities> authorities = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Collection<Authorities> getAuthorities() {return authorities;    }

    public void setAuthorities(Collection<Authorities> authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(Authorities authority) {
        this.authorities.add(authority);
    }

    public boolean hasRole(String roleName) {
        Iterator<Authorities> iterator = this.authorities.iterator();
        while (iterator.hasNext()) {
            Authorities role = iterator.next();
            if (role.getAuthority().equals(roleName)) {
                return true;
            }
        }
         
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", authorities=" + authorities +
                '}';
    }

}
