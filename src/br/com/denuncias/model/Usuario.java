package br.com.denuncias.model;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private boolean administrador;

    public Usuario(int id, String nome, String email, String senha, boolean administrador) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.administrador = administrador;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public boolean isAdministrador() {
        return administrador;
    }
}