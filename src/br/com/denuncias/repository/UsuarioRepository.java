package br.com.denuncias.repository;

import br.com.denuncias.model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    private List<Usuario> usuarios = new ArrayList<>();
    private int sequence = 1;

    public UsuarioRepository() {
        // Usuário admin padrão
        usuarios.add(new Usuario(sequence++, "Admin", "admin@admin.com", "123", true));
    }

    public Usuario cadastrar(String nome, String email, String senha, boolean admin) {
        Usuario usuario = new Usuario(sequence++, nome, email, senha, admin);
        usuarios.add(usuario);
        return usuario;
    }

    public Usuario buscarPorEmailSenha(String email, String senha) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equals(email) && u.getSenha().equals(senha))
                .findFirst()
                .orElse(null);
    }
}