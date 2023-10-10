package com.example.testespring;

import jakarta.persistence.*;

//import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Cachorro")
public class Usuario implements Serializable {
    private static final long serialVersionUID = -2420346134960559062L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROPRIERATIO")
    private Long id;
    @Column(name = "LOGIN", length = 30, nullable = false, unique = true)
    private String login;
    @Column(name = "SENHA", length = 6, nullable = false, unique = false)
    private String senha;
    @Column(name = "DATA_CADASTRO", nullable = false, unique = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dtCadastro;
    @Column(name = "IDADE", nullable = false, unique = false)
    private int idade;

    //Omitidos os métodos getters e setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (id != null ? !id.equals(usuario.id) : usuario.id != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}