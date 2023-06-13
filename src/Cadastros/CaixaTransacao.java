package Cadastros;

import javax.swing.*;
import java.sql.*;

public class CaixaTransacao {
    private int idTransacao;
    private String descricao;
    private int entradaSaida;

    public void criaTransacao(CaixaTransacao caixatransacao, Connection c){
        caixatransacao.descricao = JOptionPane.showInputDialog("Digite o nome da transacao: ");
        caixatransacao.entradaSaida = Integer.parseInt(JOptionPane.showInputDialog("Digite o tipo de movimento\n1-Entrada\n0-Saida"));

        PreparedStatement ps = null;
        String query = "INSERT INTO myfinance.caixa_transacao (descricao, entradasaida) VALUES (?, ?)";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, caixatransacao.descricao);
            ps.setInt(2, caixatransacao.entradaSaida);
            ps.execute();
            ps.close();
            System.out.println("Transacao criada");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir registro: "+e.getMessage());
        }
    };
    public void editaTransacao(){
        System.out.println("Transacao editada");
    };
    public void excluiTransacao() {
        System.out.println("Transacao excluido");
    };

    public String getEntradaSaida(int idTransacaoSelecionada,Connection c) {
        PreparedStatement ps = null;
        String query = "SELECT entradasaida FROM caixa_transacao WHERE idtransacao = ?";

        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idTransacaoSelecionada);
            ResultSet rs = ps.executeQuery();
            return String.valueOf(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";
    };
}
