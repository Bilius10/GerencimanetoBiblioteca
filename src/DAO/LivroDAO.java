package DAO;

import Entidades.Livro;
import Util.ConnectionMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Livro> retornoEstoqueLivros(){
        List<Livro> livros = new ArrayList<>();
        try {
            Connection coon = ConnectionMysql.openConnection();

            String sqlEstoque = "SELECT * FROM livros WHERE Ativo = 1";

            PreparedStatement statementEstoque = coon.prepareStatement(sqlEstoque);
            ResultSet resultSetEstoque = statementEstoque.executeQuery();

            while (resultSetEstoque.next()){
                Livro livro = new Livro();
                livro.setIdLivro(resultSetEstoque.getInt("idLivros"));
                livro.setNomeLivro(resultSetEstoque.getString("nomeLivro"));
                livro.setQuantidade(resultSetEstoque.getInt("quantidade"));
                livro.setEditora(resultSetEstoque.getString("Editora"));
                livro.setAutor(resultSetEstoque.getString("Autor"));
                livro.setDataLancamento(resultSetEstoque.getInt("dataLancamento"));
                livro.setAtivo(resultSetEstoque.getInt("Ativo"));
                livros.add(livro);
            }


        }catch (SQLException e){
            System.out.println("Erro ao listar estoque: "+e.getMessage()     );
        }

        return livros;
    }

    public Livro findByID(int id){
        Livro livro = new Livro();
        try {

            Connection coon = ConnectionMysql.openConnection();

            String sqlfind = "SELECT * FROM livros WHERE idLivros = ?";

            PreparedStatement statementFind = coon.prepareStatement(sqlfind);
            statementFind.setInt(1, id);
            ResultSet resultSetFind = statementFind.executeQuery();

            while (resultSetFind.next()){
                livro.setIdLivro(id);
                livro.setNomeLivro(resultSetFind.getString("nomeLivro"));
                livro.setQuantidade(resultSetFind.getInt("quantidade"));
                livro.setEditora(resultSetFind.getString("Editora"));
                livro.setAutor(resultSetFind.getString("Autor"));
                livro.setDataLancamento(resultSetFind.getInt("dataLancamento"));
                livro.setAtivo(resultSetFind.getInt("Ativo"));
            }

        }catch (SQLException e){
            System.out.println("Erro ao achar livro: "+e.getMessage());
        }

        return livro;
    }
}
