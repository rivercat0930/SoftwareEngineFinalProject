package com.mycompany.user;

import javax.persistence.*;

@SuppressWarnings("ALL")
@Entity
@Table(name = "users_test")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false, name = "firstName")
    private String firstName;

    @Column(length = 45, nullable = false, name = "Email") //add
    private String Email;

    @Column(length = 45, nullable = false, name = "Clas") //add
    private String Clas;

    @Column(length = 45, nullable = false, name = "password") //add
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return Email;
    } //add

    public void setEmail(String Email) {this.Email = Email;}//add

    public String getClas() {
        return Clas;
    } //add

    public void setClas(String Clas) {
        this.Clas = Clas;
    }//add

    public String getpassword() {
        return password;
    } //add

    public void setpassword(String password) {
        this.password = password;
    }//add

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", Email='" + getEmail() + '\'' + //add
                ", Clas='" + getClas() + '\'' + //add
                '}';
    }

}
