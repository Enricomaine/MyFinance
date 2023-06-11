package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class mySQL {
    String host = "localhost:3306";
    String user = "root";
    String pass = "myfinance123";
    String banco = "myfinance";
    String url = "jdbc:mysql://" + host + "/" + banco;

    Connection c = null;

    public Connection getConexao() {
        try {
            c = DriverManager.getConnection(url, user, pass);
            System.out.println("Conectado");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar");
            System.out.println(e.getMessage());
        }
        return c;
    }

    public void fechaConexao() {
        if (c != null) {
            try {
                c.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Conexao Fechada");
    }
}
