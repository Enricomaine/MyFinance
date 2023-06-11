package Cadastros;

import java.sql.*;

public class Banco {
    private int idBanco;
    private String descricao;

    public void criaBanco(Banco banco, Connection c){
        PreparedStatement ps = null;
        String query = "INSERT INTO myfinance.banco (descricao) VALUES (?)";
        try {
            ps = c.prepareStatement(query);
            ps.setString(1, banco.descricao);
            ps.execute();
            ps.close();
            System.out.println("Banco inserido!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir registro: "+e.getMessage());
        }
    };
    public void editaBanco(){
        System.out.println("Banco editado");
    };
    public void excluiBanco() {
        System.out.println("Banco excluido");
    };
}
