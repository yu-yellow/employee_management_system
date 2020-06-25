package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "password")
@NamedQueries({
    @NamedQuery(
            name = "getAllPassword",
            query = "SELECT p FROM Password AS p ORDER BY p.password_id desc"
            ),
    @NamedQuery(
            name = "checkPassword",
            query = "SELECT p FROM Password AS p WHERE p.password_id = :code AND p.password = :pass"
            )
})

@Entity
public class Password {
    @Id
    @Column(name = "password_id")
    private String password_id;

    @Column(name = "password", length=64, nullable = false)
    private String password;


    //getter setter
    public String getPassword_id() {
        return password_id;
    }

    public void setPassword_id(String password_id) {
        this.password_id = password_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
