package Cadastros;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conta {
    private int idConta;
    private int idBanco;
    private String numero;
    private String agencia;

    public void criaConta(Conta conta, Connection c){
        conta.idBanco = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do banco dessa conta:"));
        conta.numero = JOptionPane.showInputDialog("Numero da conta:");
        conta.agencia = JOptionPane.showInputDialog("Agencia da conta: ");
        PreparedStatement ps = null;
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
    };
    public void editaConta(){
        System.out.println("Conta editado");
    };
    public void excluiConta() {
        System.out.println("Conta excluido");
    };
}
