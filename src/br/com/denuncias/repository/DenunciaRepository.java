package br.com.denuncias.repository;

import br.com.denuncias.model.Denuncia;
import java.util.ArrayList;
import java.util.List;

public class DenunciaRepository {

    private List<Denuncia> denuncias = new ArrayList<>();
    private int sequence = 1;

    public Denuncia registrar(int usuarioId, String descricao) {
        Denuncia denuncia = new Denuncia(sequence++, usuarioId, descricao);
        denuncias.add(denuncia);
        return denuncia;
    }

    public List<Denuncia> listar() {
        return denuncias;
    }

    public Denuncia buscarPorId(int id) {
        return denuncias.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .orElse(null);
    }
}