package BD;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
