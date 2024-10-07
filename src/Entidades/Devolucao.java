package Entidades;

import java.util.Date;

public class Devolucao {

    private int idDevolucao;
    private int idLivro;
    private int idUsario;
    private Date dataDevolucao;

    public Devolucao() {
    }

    public Devolucao(int idDevolucao, int idLivro, int idUsario, Date dataDevolucao) {
        this.idDevolucao = idDevolucao;
        this.idLivro = idLivro;
        this.idUsario = idUsario;
        this.dataDevolucao = dataDevolucao;
    }

    public int getIdDevolucao() {
        return idDevolucao;
    }

    public void setIdDevolucao(int idDevolucao) {
        this.idDevolucao = idDevolucao;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public int getIdUsario() {
        return idUsario;
    }

    public void setIdUsario(int idUsario) {
        this.idUsario = idUsario;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    @Override
    public String toString() {
        return "Devolucao{" +
                "idDevolucao=" + idDevolucao +
                ", idLivro=" + idLivro +
                ", idUsario=" + idUsario +
                ", dataDevolucao=" + dataDevolucao +
                '}';
    }
}
