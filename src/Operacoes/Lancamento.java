package Operacoes;

import Cadastros.CaixaTransacao;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lancamento {

    private int idLancamento;
    private int idTransacao;
    private int idConta;
    private String data;
    private double valorSaida;
    private double valorEntrada;

    CaixaTransacao caixaTransacao = new CaixaTransacao();
    public void criaLancamento(Lancamento lancamento, Connection c) {

        idTransacao = Integer.parseInt(JOptionPane.showInputDialog("Selecione a transacao: "));
        idConta = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo da conta: "));
        if (caixaTransacao.getEntradaSaida(idTransacao, c) == "1") {
            valorEntrada = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor da entrada: "));
        } else {
            valorSaida = Double.parseDouble((JOptionPane.showInputDialog("Informe o valor da saida: ")));
        }
        data = JOptionPane.showInputDialog("Informe a data do lancamento: ", "  /  /    ");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date inputData = null;

        try {
            inputData = dateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        PreparedStatement ps = null;
        String queryEntrada = "INSERT INTO myfinance.caixa (idtransacao, data, valorentrada,idconta) VALUES (?, ?, ?, ?)";
        String querySaida = "INSERT INTO myfinance.caixa (idtransacao, data, valorsaida,idconta) VALUES (?, ?, ?, ?)";
        try {
            if (caixaTransacao.getEntradaSaida(idTransacao, c) == "1") {
                ps = c.prepareStatement(queryEntrada);
                ps.setDouble(3, valorEntrada);
            } else {
                ps = c.prepareStatement(querySaida);
                ps.setDouble(3, valorSaida);
            }
            ps.setInt(1, idTransacao);
            ps.setDate(2, new java.sql.Date(inputData.getTime()));
            ps.setInt(4, idConta);
            ps.execute();
            ps.close();
            System.out.println("Lancamento inserido");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir registro: "+e.getMessage());
        }
    }

    public void editaLancamento(Connection c) {
        idLancamento = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do lancamento que voce deseja editar: "));

        idTransacao = Integer.parseInt(JOptionPane.showInputDialog("Selecione a nova transacao: "));
        if (caixaTransacao.getEntradaSaida(idTransacao, c) == "1") {
            valorEntrada = Double.parseDouble(JOptionPane.showInputDialog("Informe o novo valor da entrada: "));
        } else {
            valorSaida = Double.parseDouble((JOptionPane.showInputDialog("Informe o novo valor da saida: ")));
        }
        data = JOptionPane.showInputDialog("Informe a nova data do lancamento: ", "  /  /    ");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date setData = null;

        try {
            setData = dateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        PreparedStatement ps = null;
        String queryEntrada = "UPDATE myfinance.caixa SET idtransacao = ?, data = ?, valorentrada = ?, valorsaida = 0 WHERE idlancamento = ?";
        String querySaida = "UPDATE myfinance.caixa SET idtransacao = ?, data = ?, valorsaida = ? valorentrada = 0 WHERE idlancamento = ?";
        try {
            if (caixaTransacao.getEntradaSaida(idTransacao, c) == "1") {
                ps = c.prepareStatement(queryEntrada);
                ps.setDouble(3, valorEntrada);
            } else {
                ps = c.prepareStatement(querySaida);
                ps.setDouble(3, valorSaida);
            }
            ps.setInt(1, idTransacao);
            ps.setDate(2, new java.sql.Date(setData.getTime()));
            ps.setInt(4, idLancamento);
            ps.execute();
            ps.close();
            System.out.println("Lancamento atualizado");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar registro: "+e.getMessage());
        }
    }

    public void excluiLancamento(Connection c) {
        idLancamento = Integer.parseInt(JOptionPane.showInputDialog("Informe o codigo do lancamento a ser excluido"));

        PreparedStatement ps = null;
        String query = "DELETE FROM myfinance.caixa WHERE idlancamento = ?";
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idLancamento);
            ps.execute();
            ps.close();
            System.out.println("Lancamento excluido");
        } catch (SQLException e) {
            System.out.println("Erro ao remover registro: "+e.getMessage());
        }
    }
}
