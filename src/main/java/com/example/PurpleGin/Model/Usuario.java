package com.example.PurpleGin.Model;

import jakarta.persistence.*;

//import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = -2420346134960559062L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROPRIERATIO")
    private Long id;
    @Column(name = "usuario", length = 30, nullable = false, unique = true)
    private String login;
    @Column(name = "senha", length = 30, nullable = false, unique = false)
    private String senha;

    public Usuario(Long id, String login, String senha) {
        this.id = id;
        this.login = login;
        this.senha = senha;
    }

    public Usuario() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}