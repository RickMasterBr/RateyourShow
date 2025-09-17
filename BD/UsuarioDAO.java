package BD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {
    private Conexao conexao;
    
    public UsuarioDAO(){
        conexao = new Conexao();
    }

    public void salvar(String cpf, String email, String nome, String senha, String genero) throws Exception{
        String sql = "INSERT INTO usuario (cpf, email, nome, senha, genero) VALUES (?,?,?,?,?)";
        try(Connection conn = conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1, cpf);
                stmt.setString(2, email);
                stmt.setString(3, nome);
                stmt.setString(4, senha);
                stmt.setString(5, genero);
                stmt.executeUpdate();
            }
    }

    public Usuario logar(String cpf, String senha) throws Exception{
        String sql = "SELECT * from usuario WHERE cpf = ? AND senha = ?";
        try(Connection conn = conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1, cpf);
                stmt.setString(2, senha);
                try (ResultSet rs = stmt.executeQuery()){
                    if (rs.next()) {
                        return new Usuario(rs.getString("cpf"), rs.getString("nome"), rs.getString("email"), rs.getString("genero"));
                    }
                }
            }
            return null;
    }
}
