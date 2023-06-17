package Cadastros;

import javax.swing.*;
import java.sql.*;

public class CaixaTransacao {
    private int idTransacao;
    private String descricao;
    private int entradaSaida;

    public void criaTransacao(CaixaTransacao caixatransacao, Connection c){
        String[] opcoes = {"Entrada", "Saida"};
        String opcao;
        descricao = JOptionPane.showInputDialog("Digite o nome da transacao: ");
        entradaSaida = setEntradaSaida();
        PreparedStatement ps = null;
        String query = "INSERT INTO myfinance.caixa_transacao (descricao, entradasaida) VALUES (?, ?)";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, descricao);
            ps.setInt(2, entradaSaida);
            ps.execute();
            ps.close();
            System.out.println("Transacao criada");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir registro: "+e.getMessage());
        }
    };
    public void editaTransacao(Connection c){
        PreparedStatement ps = null;
        String query = "UPDATE CAIXA_TRANSACAO SET descricao = ? WHERE idtransacao = ?";
        idTransacao = Integer.parseInt(JOptionPane.showInputDialog("Digite a transacao a ser editada: "));
        descricao = JOptionPane.showInputDialog("Digite a nova descricao");
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, descricao);
            ps.setInt(2, idTransacao);
            ps.execute();
            ps.close();
            System.out.println("Transacao editada");
        } catch (SQLException e){
            System.out.println("Erro ao editar registro: "+e.getMessage());
        }
    };
    public void excluiTransacao(Connection c) {
        idTransacao = Integer.parseInt(JOptionPane.showInputDialog("Informe o codigo do banco a se removido"));

        PreparedStatement ps = null;
        String query = "DELETE FROM caixa_transacao WHERE idtransacao = ?";
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, idTransacao);
            ps.execute();
            ps.close();
            System.out.println("Transacao excluida");
        } catch (SQLException e) {
            System.out.println("Erro ao remover registro: "+e.getMessage());
        }
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

    public int setEntradaSaida() {
        String[] opcoes = {"Entrada", "Saida"};
        String opcao;
        opcao = (String) JOptionPane.showInputDialog(null,"Selecione uma das opcoes","Tipo de movimento",JOptionPane.QUESTION_MESSAGE,null,opcoes, opcoes[0]);
        if (opcao.equals("Entrada")) {
            return 1;
        } else {
            return 0;
        }
    }
}
