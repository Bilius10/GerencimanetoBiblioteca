
import DAO.LoginUsuarioDAO;
import Entidades.LoginUsuario;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        Estoque estoque = new Estoque();

        LoginUsuario loginUsuario = new LoginUsuario();
        LoginUsuarioDAO loginUsuarioDAO = new LoginUsuarioDAO();

        System.out.println("Seja bem-vindo a biblioteca");

        System.out.print("Login: ");
        String login = teclado.next();
        System.out.print("Senha: ");
        String senha = teclado.next();

        loginUsuario = loginUsuarioDAO.aprovarLogin(login, senha);

        if(loginUsuario.getLogin() != null){
            estoque.rodar(loginUsuario, teclado);
        }
    }
}