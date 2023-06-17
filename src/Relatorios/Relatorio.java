package Relatorios;

import Telas.InputData;

import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Relatorio {

    public String consultaBancos(Connection c) {
        int idBanco;
        String descricao;
        Statement st;
        String query = "SELECT * FROM vw_bancoscadastrados";

        try {
            st = c.createStatement();
            ResultSet rs = st.executeQuery(query);
            StringBuilder rt = new StringBuilder();

            while(rs.next()) {
                idBanco = rs.getInt("idbanco");
                descricao = rs.getString("Descricao");
                rt.append("id: "+idBanco+" Descricao: "+descricao+"\n");
            }
            return rt.toString();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public String consultaContas(Connection c) {
        int idConta;
        int idBanco;
        String numero;
        String agencia;
        Statement st;
        String query = "SELECT * FROM vw_contascadastradas";

        try {
            st = c.createStatement();
            ResultSet rs = st.executeQuery(query);
            StringBuilder rt = new StringBuilder();

            while(rs.next()) {
                idConta = rs.getInt("idconta");
                idBanco = rs.getInt("idbanco");
                numero = rs.getString("numero");
                agencia = rs.getString("agencia");
                rt.append("idconta: "+idConta+" idbanco: "+idBanco+" numero: "+numero+" agencia: "+agencia+"\n");
            }
            return rt.toString();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public String consultaTransacoes(Connection c) {
        int idTransacao;
        String descricao;
        Statement st;
        String query = "SELECT * FROM vw_transacoescadastradas";

        try {
            st = c.createStatement();
            ResultSet rs = st.executeQuery(query);
            StringBuilder rt = new StringBuilder();

            while(rs.next()) {
                idTransacao = rs.getInt("idTransacao");
                descricao = rs.getString("descricao");
                rt.append("idtransacao: "+idTransacao+" descricao: "+descricao+"\n");
            }
            return rt.toString();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public void saldoEmConta(Connection c) {
        int idconta;
        double saldo;
        Statement st;
        String query = "SELECT * from vw_saldocontas";
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
        PreparedStatement ps;
        int idlancamento, idTransacao;
        double valorsaida, valorEntrada;
        String DATA;
        String query = "SELECT * FROM CAIXA WHERE DATA BETWEEN ? AND ? ORDER BY DATA";
        StringBuilder rt = new StringBuilder();

        String dataInicial = InputData.InputData("Data Inicial");
        String dataFinal = InputData.InputData("Data Final");
        DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
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
        PreparedStatement ps;
        int idConta, idTransacao;
        double valor;
        String DATA;
        StringBuilder rt = new StringBuilder();
        String query = "SELECT distinct" +
                            " vw.* " +
                            " from " +
                            " vw_lancamentosportransacao vw, " +
                            " caixa c " +
                            " WHERE " +
                            " c.DATA BETWEEN ? AND ?";

        String dataInicial = InputData.InputData("Data inicial");
        String dataFinal = InputData.InputData("Data final");
        DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
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
                idConta = rs.getInt("idconta");
                idTransacao = rs.getInt("idTransacao");
                valor = rs.getDouble("valor");
                rt.append("|idconta: "+idConta+"| idTransacao: "+idTransacao+"| valor: "+valor);
            }
            JOptionPane.showMessageDialog(null, rt.toString());
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro na consulta: "+e.getMessage());
        }
    }
}

