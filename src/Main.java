import DAO.DevolucaoDAO;

import Entidades.Devolucao;

public class Main {
    public static void main(String[] args) {

        Devolucao devolucao = new Devolucao();
        devolucao.setIdLivro(1);
        devolucao.setIdUsario(1);
        devolucao.setDataDevolucao(java.sql.Date.valueOf("2024-11-01"));

        DevolucaoDAO devolucaoDAO = new DevolucaoDAO();
        devolucaoDAO.crateDevolucao(devolucao);
    }
}