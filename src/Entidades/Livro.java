package Entidades;

public class Livro {

    private int idLivro;
    private String nomeLivro;
    private String autor;
    private String Editora;
    private int dataLancamento;
    private int quantidade;
    private int ativo;

    public Livro() {
        this.ativo = 1;
    }

    public Livro(int idLivro, String nomeLivro, String autor, String editora, int dataLancamento, int quantidade, int ativo) {
        this.idLivro = idLivro;
        this.nomeLivro = nomeLivro;
        this.autor = autor;
        Editora = editora;
        this.dataLancamento = dataLancamento;
        this.quantidade = quantidade;
        this.ativo = ativo;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return Editora;
    }

    public void setEditora(String editora) {
        Editora = editora;
    }

    public int getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(int dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "idLivro=" + idLivro +
                ", nomeLivro='" + nomeLivro + '\'' +
                ", autor='" + autor + '\'' +
                ", Editora='" + Editora + '\'' +
                ", dataLancamento=" + dataLancamento +
                ", quantidade=" + quantidade +
                ", ativo=" + ativo +
                '}';
    }
}
