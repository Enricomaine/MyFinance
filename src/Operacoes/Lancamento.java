package Operacoes;

import Cadastros.CaixaTransacao;
import Relatorios.Relatorio;
import Telas.InputData;

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
    Relatorio rel = new Relatorio();
    public void criaLancamento(Lancamento lancamento, Connection c) {

        idTransacao = Integer.parseInt(JOptionPane.showInputDialog(rel.consultaTransacoes(c)+"Selecione a transacao: "));
        idConta = Integer.parseInt(JOptionPane.showInputDialog(rel.consultaContas(c)+"Digite o codigo da conta: "));
        if (caixaTransacao.getTipoMovimento(idTransacao, c).equals("Entrada")) {
            valorEntrada = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor da entrada: "));
        } else {
            valorSaida = Double.parseDouble((JOptionPane.showInputDialog("Informe o valor da saida: ")));
        }
        data = InputData.InputData("Informa a data do lancamento");
        DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
        Date inputData = null;

        try {
            inputData = dateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        PreparedStatement ps;
        String queryEntrada = "INSERT INTO caixa (idtransacao, data, valorentrada, idconta) VALUES (?, ?, ?, ?)";
        String querySaida = "INSERT INTO caixa (idtransacao, data, valorsaida, idconta) VALUES (?, ?, ?, ?)";
        try {
            if (caixaTransacao.getTipoMovimento(idTransacao, c).equals("Entrada")) {
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

        idTransacao = Integer.parseInt(JOptionPane.showInputDialog(rel.consultaTransacoes(c)+"Selecione a nova transacao: "));
        if (caixaTransacao.getTipoMovimento(idTransacao, c).equals("Entrada")) {
            valorEntrada = Double.parseDouble(JOptionPane.showInputDialog("Informe o novo valor da entrada: "));
        } else {
            valorSaida = Double.parseDouble((JOptionPane.showInputDialog("Informe o novo valor da saida: ")));
        }
        data = InputData.InputData("Informe a nova data do lancamento");
        DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
        Date setData = null;

        try {
            setData = dateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        PreparedStatement ps;
        String queryEntrada = "UPDATE myfinance.caixa SET idtransacao = ?, data = ?, valorentrada = ?, valorsaida = 0 WHERE idlancamento = ?";
        String querySaida = "UPDATE myfinance.caixa SET idtransacao = ?, data = ?, valorsaida = ? valorentrada = 0 WHERE idlancamento = ?";
        try {
            if (caixaTransacao.getTipoMovimento(idTransacao, c).equals("Entrada")) {
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

        PreparedStatement ps;
        String query = "DELETE FROM caixa WHERE idlancamento = ?";
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
