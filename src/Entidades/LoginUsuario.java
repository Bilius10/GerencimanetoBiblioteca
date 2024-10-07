package Entidades;

public class LoginUsuario {

    private int idLoginUsuario;
    private String login;
    private String senha;
    private int ativo;
    private int quantidadeEmprestimos;

    public LoginUsuario() {
        this.ativo = 1;
    }

    public LoginUsuario(int idLoginUsuario, String login, String senha, int ativo, int quantidadeEmprestimos) {
        this.idLoginUsuario = idLoginUsuario;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
        this.quantidadeEmprestimos = quantidadeEmprestimos;
    }

    public int getIdLoginUsuario() {
        return idLoginUsuario;
    }

    public void setIdLoginUsuario(int idLoginUsuario) {
        this.idLoginUsuario = idLoginUsuario;
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

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public int getQuantidadeEmprestimos() {
        return quantidadeEmprestimos;
    }

    public void setQuantidadeEmprestimos(int quantidadeEmprestimos) {
        this.quantidadeEmprestimos = quantidadeEmprestimos;
    }

    @Override
    public String toString() {
        return "LoginUsuario{" +
                "idLoginUsuario=" + idLoginUsuario +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", ativo=" + ativo +
                ", quantidadeEmprestimos=" + quantidadeEmprestimos +
                '}';
    }
}
