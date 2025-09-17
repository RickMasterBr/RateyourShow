package BD;
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

    public void atualizar(String cpf, String email, String nome, String senha, String genero) throws Exception{
        StringBuilder sql = new StringBuilder("UPDATE usuarios SET ");
        ArrayList<Object> parametros = new ArrayList<>();

        if (email != null && !email.isBlank()) {
            sql.append("email = ?, ");
            parametros.add(email);
        }
        if (nome != null && !nome.isBlank()) {
            sql.append("nome = ?, ");
            parametros.add(nome);
        }
        if (senha != null && !senha.isBlank()) {
            sql.append("senha = ?, ");
            parametros.add(senha);
        }
        if (genero != null && !genero.isBlank()) {
            sql.append("genero = ?, ");
            parametros.add(genero);
        }

        if (parametros.isEmpty()) {
            throw new Exception("Nenhum dado foi informado para atualização.");
        }

        sql.setLength(sql.length() - 2);
        sql.append(" WHERE cpf = ?");
        parametros.add(cpf);
    
        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
    
            for (int i = 0; i < parametros.size(); i++) {
                stmt.setObject(i + 1, parametros.get(i));
            }
    
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new Exception("Nenhum usuário encontrado com o CPF informado.");
            }
        }
    }

    
}
