package pl.wipek.shared.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Krzysztof Adamczyk on 26.10.2017.
 */
@Entity
@XmlRootElement
public class Users implements Serializable {
    private String idUsers;
    private String login;
    private String password;
    private String token;

    @Id
    @Column(name = "ID_USERS", nullable = false)
    @JsonProperty("idUsers")
    public String getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(String idUsers) {
        this.idUsers = idUsers;
    }

    @Basic
    @Column(name = "LOGIN", nullable = false, length = 25)
    @JsonProperty("login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = true, length = 255)
    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "TOKEN", nullable = true, length = 255)
    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (idUsers != null ? !idUsers.equals(users.idUsers) : users.idUsers != null) return false;
        if (login != null ? !login.equals(users.login) : users.login != null) return false;
        if (password != null ? !password.equals(users.password) : users.password != null) return false;
        if (token != null ? !token.equals(users.token) : users.token != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUsers != null ? idUsers.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Users{" +
                "idUsers='" + idUsers + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
