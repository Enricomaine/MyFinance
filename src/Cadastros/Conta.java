package Cadastros;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Relatorios.Relatorio;

public class Conta {
    private int idConta;
    private int idBanco;
    private String numero;
    private String agencia;

    Relatorio relatorio = new Relatorio();

    public void criaConta(Conta conta, Connection c) {
        conta.idBanco = Integer.parseInt(JOptionPane.showInputDialog(relatorio.consultaBancos(c)+"Digite o codigo do banco dessa conta:"));
        conta.numero = JOptionPane.showInputDialog("Numero da conta:");
        conta.agencia = JOptionPane.showInputDialog("Agencia da conta: ");
        PreparedStatement ps;
        String query = "INSERT INTO myfinance.conta (idbanco, numero, agencia) VALUES (?,?,?)";
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, conta.idBanco);
            ps.setString(2, conta.numero);
            ps.setString(3, conta.agencia);
            ps.execute();
            ps.close();
            System.out.println("Conta inserida");
        } catch (SQLException e) {
        System.out.println("Erro ao inserir registro: "+e.getMessage());
        }
    }
    public void editaConta(Connection c){
        idConta = Integer.parseInt(JOptionPane.showInputDialog(relatorio.consultaContas(c)+"Id para edicao: "));
        PreparedStatement ps;
        String query = "UPDATE conta SET numero = ?, agencia = ?, idbanco = ? WHERE idconta = ?";

        String numero = JOptionPane.showInputDialog("Informe o novo numero: ");
        String agencia = JOptionPane.showInputDialog("Informe a nova agencia: ");
        int idBanco = Integer.parseInt(JOptionPane.showInputDialog(relatorio.consultaBancos(c) + "Digite o novo banco dessa conta: "));

        try {
            ps = c.prepareStatement(query);
            ps.setString(1, numero);
            ps.setString(2, agencia);
            ps.setInt(3, idBanco);
            ps.setInt(4, idConta);
            ps.execute();
            ps.close();
            System.out.println("Conta alterada");
        } catch (SQLException e) {
            System.out.println("Erro ao alterar registro: "+e.getMessage());
        }
    }
    public void excluiConta(Connection c) {
        idConta = Integer.parseInt(JOptionPane.showInputDialog(relatorio.consultaContas(c)+"Informe o codigo da conta a ser removida"));

        PreparedStatement ps;
        String query = "DELETE FROM conta WHERE idconta = ?";
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idConta);
            ps.execute();
            ps.close();
            System.out.println("Conta excluida");
        } catch (SQLException e) {
            System.out.println("Erro ao remover registro: "+e.getMessage());
        }
    }
}
