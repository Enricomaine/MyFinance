package Relatorios;

import Cadastros.Banco;
import Operacoes.Lancamento;

import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Relatorio {

    public void consultaBancos(Connection c) {
        int idbanco;
        String descricao;
        Statement st = null;
        String query = "SELECT * FROM banco";

        try {
            st = c.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()) {
                Banco banco = new Banco();
                idbanco = rs.getInt("idbanco");
                descricao = rs.getString("Descricao");
                System.out.println("id: "+idbanco+" Descricao: "+descricao);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saldoEmConta(Connection c) {
        int idconta;
        double saldo;
        Statement st = null;
        String query = "SELECT idconta, sum(VALORENTRADA)-sum(VALORSAIDA) AS saldo FROM caixa GROUP BY idconta";
        StringBuilder rt = new StringBuilder();
        try {
            st = c.createStatement();

            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                idconta = rs.getInt("idconta");
                saldo = rs.getDouble("saldo");

                rt.append("idconta: "+idconta+" Saldo: "+saldo+"\n");
            }
            JOptionPane.showMessageDialog(null, rt.toString());
        } catch (SQLException e) {
            System.out.println("Erro na consulta: "+e.getMessage());
        }
    }

    public void lancamentoPorPeriodo(Connection c) {
        PreparedStatement ps = null;
        int idlancamento, idTransacao;
        double valorsaida, valorEntrada;
        String DATA;
        String query = "SELECT * FROM CAIXA WHERE DATA BETWEEN ? AND ? ORDER BY DATA";
        StringBuilder rt = new StringBuilder();

        String dataInicial = JOptionPane.showInputDialog("Digite a data inicial: ","  /  /   ");
        String dataFinal = JOptionPane.showInputDialog("Digite a data final: ","  /  /   ");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date setDataInicial = null, setDataFinal = null;

        try {
            setDataInicial = dateFormat.parse(dataInicial);
            setDataFinal = dateFormat.parse(dataFinal);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            ps = c.prepareStatement(query);
            ps.setDate(1, new java.sql.Date(setDataInicial.getTime()));
            ps.setDate(2, new java.sql.Date(setDataFinal.getTime()));
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Lancamento lancamento = new Lancamento();
                idlancamento = rs.getInt("idlancamento");
                idTransacao = rs.getInt("idTransacao");
                DATA = rs.getString("DATA");
                valorsaida = rs.getDouble("valorsaida");
                valorEntrada = rs.getDouble("valorEntrada");
                rt.append("|idlancamento: "+idlancamento+"| idTransacao: "+idTransacao+"| DATA: "+DATA+"| valorsaida: "+valorsaida+"| valorEntrada: "+valorEntrada+"|\n");
            }
            JOptionPane.showMessageDialog(null, rt.toString());
            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Erro na consulta: "+e.getMessage());
        }
    }

    public void lancamentosPorTransacao(Connection c) {
        PreparedStatement ps = null;
        int idConta, idTransacao;
        double valor;
        String DATA;
        StringBuilder rt = new StringBuilder();
        String query = "SELECT " +
                "idconta, " +
                "idtransacao, " +
                "DATA, " +
                "CASE (SELECT entradasaida FROM CAIXA_TRANSACAO CT WHERE c.IDTRANSACAO = ct.idtransacao) " +
                "WHEN 0 THEN (SELECT VALORSAIDA FROM caixa) " +
                "WHEN 1 THEN (SELECT valorentrada FROM caixa) " +
                "END AS valor " +
                "FROM caixa c " +
                "WHERE " +
                "DATA BETWEEN ? AND ? " +
                "GROUP BY idconta,IDTRANSACAO, DATA " +
                "ORDER BY DATA ";

        String dataInicial = JOptionPane.showInputDialog("Digite a data inicial: ","  /  /   ");
        String dataFinal = JOptionPane.showInputDialog("Digite a data final: ","  /  /   ");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date setDataInicial = null, setDataFinal = null;

        try {
            setDataInicial = dateFormat.parse(dataInicial);
            setDataFinal = dateFormat.parse(dataFinal);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            ps = c.prepareStatement(query);
            ps.setDate(1, new java.sql.Date(setDataInicial.getTime()));
            ps.setDate(2, new java.sql.Date(setDataFinal.getTime()));
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Lancamento lancamento = new Lancamento();
                idConta = rs.getInt("idconta");
                idTransacao = rs.getInt("idTransacao");
                DATA = rs.getString("DATA");
                valor = rs.getDouble("valor");
                rt.append("|idconta: "+idConta+"| idTransacao: "+idTransacao+"| DATA: "+DATA+"| valor: "+valor);
            }
            JOptionPane.showMessageDialog(null, rt.toString());
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro na consulta: "+e.getMessage());
        }
    }
}

