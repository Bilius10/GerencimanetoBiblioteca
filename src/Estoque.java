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

    public void rodar(LoginUsuario loginUsuario){

        Scanner teclado = new Scanner(System.in);

        String opcao = "0";
        while (!opcao.equals("5")){
            System.out.println("\n 1-Lançar livro \n 2-Fazer Emprestimo \n 3-Fazer devolução \n 4-Listar biblioteca \n 5-Sair");
            opcao = teclado.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Nome do Livro: ");
                    String nomeLivro = teclado.nextLine();
                    livro.setNomeLivro(nomeLivro);

                    System.out.print("Nome do Autor: ");
                    String nomeAutor = teclado.nextLine();
                    livro.setAutor(nomeAutor);

                    System.out.print("Nome do Editora: ");
                    String nomeEditora = teclado.nextLine();
                    livro.setEditora(nomeEditora);

                    System.out.print("Data de lançamento: ");
                    int dataLancamento = teclado.nextInt();
                    livro.setDataLancamento(dataLancamento);

                    System.out.print("Quantidade: ");
                    int quantidade = teclado.nextInt();
                    livro.setQuantidade(quantidade);

                    livroDAO.createLivro(livro);

                    break;
                case "2":
                    System.out.print("ID usuario: ");
                    int idUsuarioEmprestimo = teclado.nextInt();
                    emprestimo.setIdUsuario(idUsuarioEmprestimo);

                    System.out.print("ID livro: ");
                    int idLivroEmprestimo = teclado.nextInt();
                    emprestimo.setIdLivro(idLivroEmprestimo);

                    System.out.print("Data do emprestimo: ");
                    String dataEmprestimo = teclado.next();
                    emprestimo.setDataEmprestimo(java.sql.Date.valueOf(dataEmprestimo.replace("/", "-")));

                    System.out.print("Data da devolução (ano-mes-dia): ");
                    String dataDevolucaoEmprestimo = teclado.next();

                    emprestimo.setDataDevolucao(java.sql.Date.valueOf(dataDevolucaoEmprestimo.replace("/", "-")));

                    emprestimoDAO.createEmprestimo(emprestimo);
                    break;
                case "3":
                    System.out.print("ID usuario: ");
                    int idUsuarioDevolucao = teclado.nextInt();
                    devolucao.setIdUsario(idUsuarioDevolucao);

                    System.out.print("ID livro: ");
                    int idLivroDevolucao = teclado.nextInt();
                    devolucao.setIdLivro(idLivroDevolucao);

                    System.out.print("Data da devolução: ");
                    String dataDevolucao = teclado.next();
                    devolucao.setDataDevolucao(java.sql.Date.valueOf(dataDevolucao.replace("/", "-")));

                    devolucaoDAO.crateDevolucao(devolucao);

                    break;
                case "4":
                    livros = livroDAO.retornoEstoqueLivros();
                    livros.forEach(System.out::println);

                    break;
            }
        }

    }
}
