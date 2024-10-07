package DAO;

import Entidades.Livro;
import Util.ConnectionMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LivroDAO {

    public Livro createLivro(Livro livro){

        try {

            Connection coon = ConnectionMysql.openConnection();

            String sqlLivro = "INSERT INTO livros (nomeLivro, Autor, Editora, dataLancamento, quantidade, ativo)"+
                    "VALUES (?,?,?,?,?,?)";

            PreparedStatement statementLivro = coon.prepareStatement(sqlLivro);
            statementLivro.setString(1, livro.getNomeLivro());
            statementLivro.setString(2, livro.getAutor());
            statementLivro.setString(3, livro.getEditora());
            statementLivro.setInt(4, livro.getDataLancamento());
            statementLivro.setInt(5, livro.getQuantidade());
            statementLivro.setInt(6, livro.getAtivo());

            statementLivro.executeUpdate();
            System.out.println(livro.getNomeLivro()+" foi adicionado");

            ConnectionMysql.closeConnection();

        }catch (SQLException e){
            System.out.println("Erro ao criar livro "+e.getMessage());
        }

        return livro;
    }

    public void atualizarQuantidadeEstoque(int id, int quantidade){
        try {
            Connection coon = ConnectionMysql.openConnection();

            String sqlAtualizarEstoque = "UPDATE livros set quantidade = ? WHERE idLivros = ? LIMIT 1";

            PreparedStatement statementAtualizarEstoque = coon.prepareStatement(sqlAtualizarEstoque);
            statementAtualizarEstoque.setInt(1, quantidade);
            statementAtualizarEstoque.setInt(2, id);
            statementAtualizarEstoque.executeUpdate();

        }catch (SQLException e){
            System.out.println("Erro ao atualizar estoque "+e.getMessage());
        }
    }
}
