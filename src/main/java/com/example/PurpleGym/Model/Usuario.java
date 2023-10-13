package com.example.PurpleGym.Model;

import jakarta.persistence.*;

//import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = -2420346134960559062L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idusuario")
    private Integer id;
    @Column(name = "nome", length = 30, nullable = false, unique = false)
    private String nome;
    @Column(name = "E-mail", length = 30, nullable = false, unique = true)
    private String login;
    @Column(name = "senha", length = 30, nullable = false, unique = false)
    private String senha;
    @Column(name = "telefone", length = 30, nullable = false, unique = false)
    private Integer telefone;
    @Column(name = "datanascimento", length = 4, nullable = false, unique = false)
    private Date dataNascimento;
    @Column(name = "cep", length = 8, nullable = false, unique = false)
    private Integer cep;
    @Column(name = "rua", length = 30, nullable = false, unique = false)
    private String rua;
    @Column(name = "bairro", length = 30, nullable = false, unique = false)
    private String bairro;
    @Column(name = "cidade", length = 30, nullable = false, unique = false)
    private String cidade;
    @Column(name = "estado", length = 30, nullable = false, unique = false)
    private String estado;
    @Column(name = "pais", length = 30, nullable = false, unique = false)
    private String pais;
    @Column(name = "academia", length = 30, nullable = true, unique = false)
    private Boolean academia;
    @Column(name = "aluno", length = 30, nullable = true, unique = false)
    private Boolean aluno;

    public Usuario(Integer id, String nome, String login, String senha, Integer telefone, Date dataNascimento, Integer cep, String rua, String bairro, String cidade, String estado, String pais, Boolean academia, Boolean aluno) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.academia = academia;
    }

    public Usuario() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Boolean getAcademia() {
        return academia;
    }

    public void setAcademia(Boolean academia) {
        this.academia = academia;
    }

    public Boolean getAluno() {
        return aluno;
    }

    public void setAluno(Boolean aluno) {
        this.aluno = aluno;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", telefone=" + telefone +
                ", dataNascimento=" + dataNascimento +
                ", cep=" + cep +
                ", rua='" + rua + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", pais='" + pais + '\'' +
                ", academia=" + academia +
                ", aluno=" + aluno +
                '}';
    }
}