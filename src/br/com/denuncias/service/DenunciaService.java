package br.com.denuncias.service;

import br.com.denuncias.model.Denuncia;
import br.com.denuncias.repository.DenunciaRepository;

import java.util.List;

public class DenunciaService {

    private DenunciaRepository repo;

    public DenunciaService(DenunciaRepository repo) {
        this.repo = repo;
    }

    public Denuncia registrar(int usuarioId, String descricao) {
        return repo.registrar(usuarioId, descricao);
    }

    public List<Denuncia> listar() {
        return repo.listar();
    }

    public boolean resolver(int id) {
        Denuncia denuncia = repo.buscarPorId(id);
        if (denuncia != null && denuncia.getStatus().equals("ABERTA")) {
            denuncia.resolver();
            return true;
        }
        return false;
    }
}