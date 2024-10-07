package DAO;

import Entidades.Emprestimo;
import Util.ConnectionMysql;

import java.sql.*;

public class EmprestimoDAO {

    public Emprestimo createEmprestimo(Emprestimo emprestimo){

        try {

            Connection coon = ConnectionMysql.openConnection();

            String sqlBuscaLivro = "SELECT quantidade FROM livros WHERE idLivros = ? and Ativo = 1 and quantidade > 0 LIMIT 1";

            PreparedStatement statementBuscaLivro = coon.prepareStatement(sqlBuscaLivro);
            statementBuscaLivro.setInt(1, emprestimo.getIdLivro());

            ResultSet resultSetBuscaLivro = statementBuscaLivro.executeQuery();

            if (!resultSetBuscaLivro.next()) {
                System.out.println("Seu livro está desativado ou não está em estoque");
                return null;
            }


            String sqlBuscaLoginUsuario = "SELECT quantidadeEmprestimos FROM loginusuario WHERE idLoginUsuario = ? and Ativo = 1 and quantidadeEmprestimos < 3 LIMIT 1";

            PreparedStatement statementBuscaLoginUsuario = coon.prepareStatement(sqlBuscaLoginUsuario);
            statementBuscaLoginUsuario.setInt(1, emprestimo.getIdUsuario());

            ResultSet resultSetLoginUsuario = statementBuscaLoginUsuario.executeQuery();

            if(!resultSetLoginUsuario.next()){
                System.out.println("Seu usuario esta desativado ou atingiu o limite de emprestimos de livros");
                return null;
            }

            String sqlEmprestimo = "INSERT INTO emprestimo (dataEmprestimo, dataDevolucao, LoginUsuario_idLoginUsuario, Ativo, Livros_idLivros)"+
                    " VALUES (?,?,?,?,?)";

            PreparedStatement statementEmprestimo = coon.prepareStatement(sqlEmprestimo);
            statementEmprestimo.setDate(1, (Date) emprestimo.getDataEmprestimo());
            statementEmprestimo.setDate(2, (Date) emprestimo.getDataDevolucao());
            statementEmprestimo.setInt(3, emprestimo.getIdUsuario());
            statementEmprestimo.setInt(4, emprestimo.getAtivo());
            statementEmprestimo.setInt(5, emprestimo.getIdLivro());
            int rowsAffected = statementEmprestimo.executeUpdate();

            if(rowsAffected > 0){
                LivroDAO livroDAO = new LivroDAO();
                livroDAO.atualizarQuantidadeEstoque(emprestimo.getIdLivro(), resultSetBuscaLivro.getInt("quantidade")-1);

                LoginUsuarioDAO loginUsuarioDAO = new LoginUsuarioDAO();
                loginUsuarioDAO.atualizarQuantidadeEmprestimos(emprestimo.getIdUsuario(), resultSetLoginUsuario.getInt("quantidadeEmprestimos")+1);
            }

            System.out.println("Emprestimo foi gerado");
            ConnectionMysql.closeConnection();


        }catch (SQLException e){
            System.out.println("Erro ao gerar emprestimo "+e.getMessage());
        }

        return emprestimo;
    }

    public void atualizarAtivo(int id){
        try {
            Connection coon = ConnectionMysql.openConnection();

            String sqlAtualizarAtivo = "UPDATE emprestimo set Ativo = ? WHERE idEmprestimo = ? LIMIT 1";

            PreparedStatement statementAtualizarAtivo = coon.prepareStatement(sqlAtualizarAtivo);
            statementAtualizarAtivo.setInt(1, 0);
            statementAtualizarAtivo.setInt(2, id);
            statementAtualizarAtivo.executeUpdate();

        }catch (SQLException e){
            System.out.println("Erro ao atualizar estoque "+e.getMessage());
        }
    }
}
