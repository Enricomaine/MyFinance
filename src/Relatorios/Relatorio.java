package Relatorios;

import Cadastros.Banco;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Relatorio {

    public void consultaBancos(Connection c) {
        Statement st = null;
        String query = "SELECT * FROM banco";

        try {
            st = c.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()) {
                Banco banco = new Banco();
                banco.setIdBanco(rs.getInt("idbanco"));
                banco.setDescricao(rs.getString("Descricao"));
                System.out.println("id: "+banco.getIdBanco()+" Descricao: "+banco.getDescricao());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saldoEmConta(Connection c) {
        String dataInicial, dataFinal;
        int idconta;
        double saldo;
        Statement st = null;
        String query = "SELECT idconta, sum(VALORENTRADA)-sum(VALORSAIDA) AS saldo FROM caixa WHERE DATA BETWEEN ? AND ? GROUP BY idconta";
        dataInicial = JOptionPane.showInputDialog("Insira a data inicial");
        dataFinal = JOptionPane.showInputDialog(("insira a data final"));

        try {
            st = c.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                idconta = rs.getInt("idconta");
                saldo = rs.getDouble("saldo");

                System.out.println("idConta: "+ idconta + " Saldo: "+saldo);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: "+e.getMessage());
        }
    }

    public void lancamentoPorPeriodo(Connection c) {
    }

    public void lancamentosPorTransacao(Connection c) {
    }
}
