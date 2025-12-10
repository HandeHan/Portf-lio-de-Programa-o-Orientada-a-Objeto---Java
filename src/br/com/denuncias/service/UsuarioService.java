package br.com.denuncias.service;

import br.com.denuncias.model.Usuario;
import br.com.denuncias.repository.UsuarioRepository;

public class UsuarioService {

    private UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public Usuario cadastrar(String nome, String email, String senha, boolean admin) {
        return repo.cadastrar(nome, email, senha, admin);
    }

    public Usuario login(String email, String senha) {
        return repo.buscarPorEmailSenha(email, senha);
    }
}