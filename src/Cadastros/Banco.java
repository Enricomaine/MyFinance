package Cadastros;

import Relatorios.Relatorio;

import javax.swing.*;
import java.sql.*;

public class Banco {
    private int idBanco;
    private String descricao;

    Relatorio rel = new Relatorio();


    public void criaBanco(Banco banco, Connection c){
        banco.descricao = JOptionPane.showInputDialog("Digite o nome do banco");
        PreparedStatement ps;
        String query = "INSERT INTO myfinance.banco (descricao) VALUES (?)";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, banco.descricao);
            ps.execute();
            ps.close();
            System.out.println("Banco inserido!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir registro: "+e.getMessage());
        }
    }
    public void editaBanco(Connection c){
        idBanco = Integer.parseInt(JOptionPane.showInputDialog(rel.consultaBancos(c)+"Id para edicao: "));
        PreparedStatement ps;
        String query = "UPDATE myfinance.banco SET descricao = ? WHERE idbanco = ?";

        String descricao = JOptionPane.showInputDialog("Informe a nova descricao: ");

        try {
            ps = c.prepareStatement(query);
            ps.setString(1, descricao);
            ps.setInt(2, idBanco);
            ps.execute();
            ps.close();
            System.out.println("Banco alterado");
        } catch (SQLException e) {
            System.out.println("Erro ao alterar registro: "+e.getMessage());
        }
    }
    public void excluiBanco(Connection c) {
        idBanco = Integer.parseInt(JOptionPane.showInputDialog(rel.consultaBancos(c)+"Informe o codigo do banco a ser removido"));

        PreparedStatement ps;
        String query = "DELETE FROM myfinance.banco WHERE idbanco = ?";
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idBanco);
            ps.execute();
            ps.close();
            System.out.println("Banco excluido");
        } catch (SQLException e) {
            System.out.println("Erro ao remover registro: "+e.getMessage());
        }
    }
}
