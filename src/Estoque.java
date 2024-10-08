import DAO.DevolucaoDAO;
import DAO.EmprestimoDAO;
import DAO.LivroDAO;
import Entidades.Devolucao;
import Entidades.Emprestimo;
import Entidades.Livro;
import Entidades.LoginUsuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Estoque {

    Devolucao devolucao = new Devolucao();
    DevolucaoDAO devolucaoDAO = new DevolucaoDAO();

    Emprestimo emprestimo = new Emprestimo();
    EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

    Livro livro = new Livro();
    LivroDAO livroDAO = new LivroDAO();
    List<Livro> livros = new ArrayList<>();

    public void rodar(LoginUsuario loginUsuario, Scanner teclado){

        int opcao = 0;
        while (opcao != 5){
            System.out.println("\n 1-Lançar livro \n 2-Fazer Emprestimo \n 3-Fazer devolução \n 4-Listar biblioteca \n 5-Sair");
            opcao = teclado.nextInt();
            System.out.println(" ");

            switch (opcao) {
                case 1:
                    System.out.print("Nome do Livro: ");
                    livro.setNomeLivro(teclado.next());
                    System.out.print("Nome do Autor: ");
                    livro.setAutor(teclado.next());
                    System.out.print("Nome do Editora: ");
                    livro.setEditora(teclado.next());
                    System.out.print("Nome do Data de lançamento: ");
                    livro.setDataLancamento(teclado.nextInt());
                    System.out.print("Nome do Quantidade: ");
                    livro.setQuantidade(teclado.nextInt());
                    livroDAO.createLivro(livro);

                    break;
                case 2:
                    System.out.print("ID usuario: ");
                    emprestimo.setIdUsuario(teclado.nextInt());
                    System.out.print("ID livro: ");
                    emprestimo.setIdLivro(teclado.nextInt());
                    System.out.print("Data do emprestimo: ");
                    String dataEmprestimo = teclado.next();
                    emprestimo.setDataDevolucao(java.sql.Date.valueOf(dataEmprestimo.replace("/", "-")));
                    System.out.print("Data da devolução: (ano-mes-dia)");
                    String dataDevolucao = teclado.next();
                    emprestimo.setDataDevolucao(java.sql.Date.valueOf(dataDevolucao.replace("/", "-")));
                    emprestimoDAO.createEmprestimo(emprestimo);

                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Saindo");
                    break;
            }
        }

    }
}
