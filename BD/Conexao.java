package BD;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private final String url = "jdbc:mysql://localhost:3303/rys";
    private final String user = "root";
    private final String password = "alunolab";

    public Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }
}