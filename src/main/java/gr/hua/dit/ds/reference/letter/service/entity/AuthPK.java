package gr.hua.dit.ds.reference.letter.service.entity;

import java.io.Serializable;
import java.util.Objects;

public class AuthPK implements Serializable {
    private String username;
    private String authority;

    public AuthPK() {
    }

    public AuthPK(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthPK)) return false;
        AuthPK authPK = (AuthPK) o;
        return Objects.equals(username, authPK.username) && Objects.equals(authority, authPK.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, authority);
    }
}