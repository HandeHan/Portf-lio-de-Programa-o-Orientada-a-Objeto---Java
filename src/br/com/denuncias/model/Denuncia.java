package br.com.denuncias.model;

public class Denuncia {
    private int id;
    private int usuarioId;
    private String descricao;
    private String status; // ABERTA | RESOLVIDA

    public Denuncia(int id, int usuarioId, String descricao) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.descricao = descricao;
        this.status = "ABERTA";
    }

    public int getId() {
        return id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getStatus() {
        return status;
    }

    public void resolver() {
        this.status = "RESOLVIDA";
    }
}