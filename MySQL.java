package MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MySQL {
    String host = "localhost";
    String user = "root";
    String pass = "";
    String banco = "myFinance";
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
