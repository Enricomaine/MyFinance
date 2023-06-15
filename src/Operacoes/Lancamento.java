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
    private String data;
    private double valorSaida;
    private double valorEntrada;

    CaixaTransacao caixaTransacao = new CaixaTransacao();
    public void criaLancamento(Lancamento lancamento, Connection c) {

        idTransacao = Integer.parseInt(JOptionPane.showInputDialog("Selecione a transacao: "));
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
        String queryEntrada = "INSERT INTO myfinance.caixa (idtransacao, data, valorentrada) VALUES (?, ?, ?)";
        String querySaida = "INSERT INTO myfinance.caixa (idtransacao, data, valorsaida) VALUES (?, ?, ?)";
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
            ps.execute();
            ps.close();
            System.out.println("Lancamento inserido");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir registro: "+e.getMessage());
        }
    }

    public void editaLancamento() {
    }

    public void excluiLancamento() {
    }
}
