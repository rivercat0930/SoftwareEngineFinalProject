package SoftwareEngineFinalProject.user;

import jakarta.persistence.*;

@SuppressWarnings("ALL")
@Entity
@Table(name = "users_test")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false, name = "firstName")
    private String firstName;

    @Column(length = 45, nullable = false, name = "Email")
    private String email;

    @Column(length = 45, nullable = false, name = "Clas")
    private String clas;

    @Column(length = 45, nullable = false, name = "password")
    private String password;

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getClas() {
        return clas;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", Email='" + getEmail() + '\'' +
                ", Clas='" + getClas() + "\'" +
                "}";
    }
}
