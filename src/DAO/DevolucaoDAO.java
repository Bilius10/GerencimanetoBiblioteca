package DAO;

import Entidades.Devolucao;
import Util.ConnectionMysql;

import java.sql.*;


public class DevolucaoDAO {

    public Devolucao crateDevolucao(Devolucao devolucao){
        try {
            Connection coon = ConnectionMysql.openConnection();

            String sqlBuscaEmprestimo = "SELECT idEmprestimo from emprestimo WHERE LoginUsuario_idLoginUsuario = ?"+
                    " and Livros_idLivros = ? and Ativo = 1";

            PreparedStatement statementBuscaEmprestimo = coon.prepareStatement(sqlBuscaEmprestimo);
            statementBuscaEmprestimo.setInt(1, devolucao.getIdUsario());
            statementBuscaEmprestimo.setInt(2, devolucao.getIdLivro());

            ResultSet resultSetBuscaEmprestimo = statementBuscaEmprestimo.executeQuery();

            if(!resultSetBuscaEmprestimo.next()){
                System.out.println("Seu emprestimo ja foi devolvido ou o usuario não emprestou esse livro");
                return null;
            }


            String sqlBuscaLivro = "SELECT quantidade FROM livros WHERE idLivros = ? and Ativo = 1 LIMIT 1";

            PreparedStatement statementBuscaLivro = coon.prepareStatement(sqlBuscaLivro);
            statementBuscaLivro.setInt(1, devolucao.getIdLivro());

            ResultSet resultSetBuscaLivro = statementBuscaLivro.executeQuery();
            resultSetBuscaLivro.next();

            String sqlBuscaLoginUsuario = "SELECT quantidadeEmprestimos FROM loginusuario WHERE idLoginUsuario = ? and Ativo = 1 LIMIT 1";

            PreparedStatement statementBuscaLoginUsuario = coon.prepareStatement(sqlBuscaLoginUsuario);
            statementBuscaLoginUsuario.setInt(1, devolucao.getIdUsario());

            ResultSet resultSetLoginUsuario = statementBuscaLoginUsuario.executeQuery();
            resultSetLoginUsuario.next();

            String sqlDevolucao = "INSERT INTO devolucao (Livros_idLivros, LoginUsuario_idLoginUsuario, dataDevolucao)"+
                    " VALUES (?,?,?)";

            PreparedStatement statementDevolucao = coon.prepareStatement(sqlDevolucao);
            statementDevolucao.setInt(1, devolucao.getIdLivro());
            statementDevolucao.setInt(2, devolucao.getIdUsario());
            statementDevolucao.setDate(3, (Date) devolucao.getDataDevolucao());
            int rowsAffected = statementDevolucao.executeUpdate();

            if(rowsAffected > 0){

                EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
                LivroDAO livroDAO = new LivroDAO();
                LoginUsuarioDAO loginUsuarioDAO = new LoginUsuarioDAO();

                emprestimoDAO.atualizarAtivo(resultSetBuscaEmprestimo.getInt("idEmprestimo"));
                livroDAO.atualizarQuantidadeEstoque(devolucao.getIdLivro(), resultSetBuscaLivro.getInt("quantidade")+1);
                loginUsuarioDAO.atualizarQuantidadeEmprestimos(devolucao.getIdUsario(), resultSetLoginUsuario.getInt("quantidadeEmprestimos")-1);
            }

            System.out.println("Devolucao foi gerado");
            ConnectionMysql.closeConnection();

        }catch (SQLException e){
            System.out.println("Erro ao gerar devolução "+e.getMessage());
        }
        return devolucao;
    }
}
