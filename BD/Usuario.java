package BD;

public class Usuario {
    private final String cpf;
    private String nome;
    private String email;
    private String genero;

    public Usuario(String cpf, String nome, String email, String genero) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.genero = genero;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}
