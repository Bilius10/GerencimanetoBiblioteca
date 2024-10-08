package DAO;

import Entidades.Livro;
import Entidades.LoginUsuario;
import Util.ConnectionMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginUsuarioDAO {

    public LoginUsuario createLoginUsuario(LoginUsuario loginUsuario) {

        try {

            Connection coon = ConnectionMysql.openConnection();

            String sqlLoginUsuario = "INSERT INTO loginusuario (Login, senha, Ativo, quantidadeEmprestimos)" +
                    "VALUES (?,?,?,?)";

            PreparedStatement statementLoginUsuario = coon.prepareStatement(sqlLoginUsuario);

            statementLoginUsuario.setString(1, loginUsuario.getLogin());
            statementLoginUsuario.setString(2, loginUsuario.getSenha());
            statementLoginUsuario.setInt(3, loginUsuario.getAtivo());
            statementLoginUsuario.setInt(4, loginUsuario.getQuantidadeEmprestimos());
            statementLoginUsuario.executeUpdate();

            System.out.println(loginUsuario.getLogin() + " foi criado");
            ConnectionMysql.closeConnection();

        } catch (SQLException e) {
            System.out.println("Erro ao criar loginUsuario: " + e.getMessage());
        }
        return loginUsuario;
    }

    public void atualizarQuantidadeEmprestimos(int id, int quantidade) {
        try {
            Connection coon = ConnectionMysql.openConnection();

            String sqlAtualizarEstoque = "UPDATE loginusuario set quantidadeEmprestimos = ? WHERE idLoginUsuario = ? LIMIT 1";

            PreparedStatement statementAtualizarEstoque = coon.prepareStatement(sqlAtualizarEstoque);
            statementAtualizarEstoque.setInt(1, quantidade);
            statementAtualizarEstoque.setInt(2, id);
            statementAtualizarEstoque.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar estoque " + e.getMessage());
        }
    }

    public LoginUsuario findByID(int id) {
        LoginUsuario loginUsuario = new LoginUsuario();
        try {

            Connection coon = ConnectionMysql.openConnection();

            String sqlFind = "SELECT * FROM loginusuario WHERE idLoginUsuario = ?";

            PreparedStatement statementFind = coon.prepareStatement(sqlFind);
            statementFind.setInt(1, id);
            ResultSet resultSetFind = statementFind.executeQuery();

            while (resultSetFind.next()) {
                loginUsuario.setIdLoginUsuario(id);
                loginUsuario.setLogin(resultSetFind.getString("Login"));
                loginUsuario.setSenha(resultSetFind.getString("senha"));
                loginUsuario.setQuantidadeEmprestimos(resultSetFind.getInt("quantidadeEmprestimos"));
                loginUsuario.setAtivo(resultSetFind.getInt("Ativo"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao achar livro: " + e.getMessage());
        }

        return loginUsuario;
    }

    public LoginUsuario aprovarLogin(String login, String senha) {
        LoginUsuario loginUsuario = new LoginUsuario();

        try {
            Connection coon = ConnectionMysql.openConnection();

            String sqlBusca = "SELECT idLoginUsuario, quantidadeEmprestimos, Ativo FROM loginusuario WHERE Login = ? and senha = ? LIMIT 1";

            PreparedStatement statementBusca = coon.prepareStatement(sqlBusca);
            statementBusca.setString(1, login);
            statementBusca.setString(2, senha);

            ResultSet resultSet = statementBusca.executeQuery();

            while (resultSet.next()) {
                loginUsuario.setLogin(login);
                loginUsuario.setSenha(senha);
                loginUsuario.setIdLoginUsuario(resultSet.getInt("idLoginUsuario"));
                loginUsuario.setQuantidadeEmprestimos(resultSet.getInt("quantidadeEmprestimos"));
                loginUsuario.setAtivo(resultSet.getInt("Ativo"));
            }

        } catch (SQLException e) {
            System.out.println("Erro na busca: " + e.getMessage());
        }
        return loginUsuario;
    }
}