package DAO;

import Entidades.LoginUsuario;
import Util.ConnectionMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginUsuarioDAO {

    public LoginUsuario createLoginUsuario(LoginUsuario loginUsuario){

        try {

            Connection coon = ConnectionMysql.openConnection();

            String sqlLoginUsuario = "INSERT INTO loginusuario (Login, senha, Ativo, quantidadeEmprestimos)"+
                    "VALUES (?,?,?,?)";

            PreparedStatement statementLoginUsuario = coon.prepareStatement(sqlLoginUsuario);

            statementLoginUsuario.setString(1, loginUsuario.getLogin());
            statementLoginUsuario.setString(2, loginUsuario.getSenha());
            statementLoginUsuario.setInt(3, loginUsuario.getAtivo());
            statementLoginUsuario.setInt(4, loginUsuario.getQuantidadeEmprestimos());
            statementLoginUsuario.executeUpdate();

            System.out.println(loginUsuario.getLogin()+" foi criado");
            ConnectionMysql.closeConnection();

        }catch (SQLException e){
            System.out.println("Erro ao criar loginUsuario: "+e.getMessage());
        }
        return loginUsuario;
    }

    public void atualizarQuantidadeEmprestimos(int id, int quantidade){
        try {
            Connection coon = ConnectionMysql.openConnection();

            String sqlAtualizarEstoque = "UPDATE loginusuario set quantidadeEmprestimos = ? WHERE idLoginUsuario = ? LIMIT 1";

            PreparedStatement statementAtualizarEstoque = coon.prepareStatement(sqlAtualizarEstoque);
            statementAtualizarEstoque.setInt(1, quantidade);
            statementAtualizarEstoque.setInt(2, id);
            statementAtualizarEstoque.executeUpdate();

        }catch (SQLException e){
            System.out.println("Erro ao atualizar estoque "+e.getMessage());
        }
    }
}
