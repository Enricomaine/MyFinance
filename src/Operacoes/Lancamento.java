package Operacoes;

import Cadastros.CaixaTransacao;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Lancamento {

    private int idLancamento;
    private int idTransacao;
    private String data;
    private double valorSaida;
    private double valorEntrada;

    CaixaTransacao caixaTransacao = new CaixaTransacao();
    public void criaLancamento(Lancamento lancamento, Connection c) throws ParseException {
        idTransacao = Integer.parseInt(JOptionPane.showInputDialog("Selecione a transacao: "));
        if (caixaTransacao.getEntradaSaida(idTransacao, c) == "1") {
            valorEntrada = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor da entrada: "));
        } else {
            valorSaida = Double.parseDouble((JOptionPane.showInputDialog("Informe o valor da saida: ")));
        }
        data = JOptionPane.showInputDialog("Informe a data do lancamento: ", "  /  /    ");
        Date data = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(lancamento.data);
        System.out.println(data);
    }

    public void editaLancamento() {
    }

    public void excluiLancamento() {
    }
}
