package Cadastros;

import Relatorios.Relatorio;

import javax.swing.*;
import java.sql.*;

public class CaixaTransacao {
    private int idTransacao;
    private String descricao;
    private String tipoMovimento;

    Relatorio rel = new Relatorio();

    public void criaTransacao(CaixaTransacao caixatransacao, Connection c) {
        descricao = JOptionPane.showInputDialog("Digite o nome da transacao: ");
        tipoMovimento = setTipoMovimento();
        PreparedStatement ps;
        String query = "INSERT INTO caixa_transacao (descricao, entradasaida) VALUES (?, ?)";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, descricao);
            ps.setString(2, setTipoMovimento());
            ps.execute();
            ps.close();
            System.out.println("Transacao criada");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir registro: " + e.getMessage());
        }
    }

    public void editaTransacao(Connection c) {
        PreparedStatement ps;
        String query = "UPDATE CAIXA_TRANSACAO SET descricao = ? WHERE idtransacao = ?";
        idTransacao = Integer.parseInt(JOptionPane.showInputDialog(rel.consultaTransacoes(c) + "Digite a transacao a ser editada: "));
        descricao = JOptionPane.showInputDialog("Digite a nova descricao");
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, descricao);
            ps.setInt(2, idTransacao);
            ps.execute();
            ps.close();
            System.out.println("Transacao editada");
        } catch (SQLException e) {
            System.out.println("Erro ao editar registro: " + e.getMessage());
        }
    }

    public void excluiTransacao(Connection c) {
        idTransacao = Integer.parseInt(JOptionPane.showInputDialog(rel.consultaTransacoes(c) + "Informe o codigo do banco a se removido"));

        PreparedStatement ps;
        String query = "DELETE FROM caixa_transacao WHERE idtransacao = ?";
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idTransacao);
            ps.execute();
            ps.close();
            System.out.println("Transacao excluida");
        } catch (SQLException e) {
            System.out.println("Erro ao remover registro: " + e.getMessage());
        }
    }

    public String getTipoMovimento(int idTransacaoSelecionada, Connection c) {
        PreparedStatement ps;
        String tipoMovimento;
        String query = "SELECT tipoMovimento FROM caixa_transacao WHERE idtransacao = ?";

        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idTransacaoSelecionada);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tipoMovimento = rs.getString("tipoMovimento");
            } else {
                tipoMovimento = "";
            }
            return tipoMovimento;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public String setTipoMovimento() {
        String[] opcoes = {"Entrada", "Saida"};
        return (String) JOptionPane.showInputDialog(null, "Selecione uma das opcoes", "Tipo de movimento", JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
    }
}
