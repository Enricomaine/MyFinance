package Cadastros;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CaixaTransacao {
    private int idTransacao;
    private String descricao;

    public void criaTransacao(CaixaTransacao caixatransacao, Connection c){
        caixatransacao.descricao = JOptionPane.showInputDialog("Digite o nome da transacao: ");
        PreparedStatement ps = null;
        String query = "INSERT INTO myfinance.caixa_transacao (descricao) VALUES (?)";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, caixatransacao.descricao);
            ps.execute();
            ps.close();
            System.out.println("Transacao criada");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir registro: "+e.getMessage());
        }
    };
    public void editaTransacao(){
        System.out.println("Transacao editado");
    };
    public void excluiTransacao() {
        System.out.println("Transacao excluido");
    };
}
