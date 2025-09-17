package TELAS;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import BD.UsuarioDAO;
import CLASSES.BackgroundPanel; // <<< Importante ter esta classe no seu projeto
import CLASSES.RoundedButton;
import CLASSES.RoundedComboBox;
import CLASSES.RoundedPasswordField;
import CLASSES.RoundedTextFieldPlaceholder;

public class cadastro {

    private JFrame tela;
    private RoundedTextFieldPlaceholder txtcpf, txtnome, txtemail;
    private RoundedPasswordField jpsenha;
    private RoundedComboBox<String> genero;
    private JLabel lblFeedback;
    private RoundedButton btnCadastrar;

    // --- CORES ---
    private final Color COR_BOTAO_ESQUERDA = new Color(50, 180, 120);
    private final Color COR_TEXTO_PRINCIPAL = new Color(60, 60, 60);
    private final Color COR_TEXTO_SECUNDARIO = new Color(120, 120, 120);

    public cadastro() {
        tela = new JFrame("Cadastro de Usuários");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(1000, 700);
        tela.setResizable(false);
        tela.setLayout(new GridLayout(1, 2));

        // =====================================================================
        // PAINEL DA ESQUERDA (COM IMAGEM DE FUNDO SEM DISTORÇÃO)
        // =====================================================================
        
        // Carrega a imagem de fundo
        URL urlFundo = getClass().getResource("/TELAS/img/background3.jpg"); // Sua nova imagem
        Image imagemFundo = new ImageIcon(urlFundo).getImage();
        
        // Usa a classe BackgroundPanel que já corrige a distorção
        BackgroundPanel painelEsquerda = new BackgroundPanel(imagemFundo);
        painelEsquerda.setLayout(new GridBagLayout());
        painelEsquerda.setBorder(new EmptyBorder(20, 40, 20, 40));

        GridBagConstraints gbcEsquerda = new GridBagConstraints();
        gbcEsquerda.gridwidth = GridBagConstraints.REMAINDER;
        gbcEsquerda.anchor = GridBagConstraints.CENTER;
        
        // --- Conteúdo do Painel Esquerdo ---
        JPanel conteudoEsquerda = new JPanel();
        conteudoEsquerda.setLayout(new BoxLayout(conteudoEsquerda, BoxLayout.Y_AXIS));
        conteudoEsquerda.setOpaque(false);

        // Título "Bem-vindo!"
        JLabel lblTituloEsquerda = new JLabel("Bem-vindo!");
        lblTituloEsquerda.setFont(new Font("Montserrat", Font.BOLD, 36));
        lblTituloEsquerda.setForeground(Color.WHITE); // Alterado para branco
        lblTituloEsquerda.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtítulo
        JLabel lblSubtituloEsquerda = new JLabel("Já possui uma conta? Acesse agora mesmo.");
        lblSubtituloEsquerda.setFont(new Font("Montserrat", Font.PLAIN, 14));
        lblSubtituloEsquerda.setForeground(Color.WHITE); // Alterado para branco
        lblSubtituloEsquerda.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botão de Entrar (Login)
        RoundedButton btnEntrar = new RoundedButton("ENTRAR");
        btnEntrar.setFont(new Font("Montserrat", Font.BOLD, 16));
        btnEntrar.setBackground(COR_BOTAO_ESQUERDA);
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setPreferredSize(new Dimension(200, 50));
        btnEntrar.setMaximumSize(new Dimension(200, 50));
        btnEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEntrar.addActionListener(e -> {
            tela.dispose();
            new Login();
        });

        // Adicionando componentes
        conteudoEsquerda.add(lblTituloEsquerda);
        conteudoEsquerda.add(Box.createRigidArea(new Dimension(0, 10)));
        conteudoEsquerda.add(lblSubtituloEsquerda);
        conteudoEsquerda.add(Box.createRigidArea(new Dimension(0, 30)));
        conteudoEsquerda.add(btnEntrar);

        painelEsquerda.add(conteudoEsquerda, gbcEsquerda);


        // =====================================================================
        // PAINEL DA DIREITA (FORMULÁRIO DE CADASTRO)
        // =====================================================================
        JPanel painelDireita = new JPanel(new GridBagLayout());
        painelDireita.setBackground(Color.WHITE);
        painelDireita.setBorder(new EmptyBorder(40, 60, 40, 60));

        GridBagConstraints gbcDireita = new GridBagConstraints();
        gbcDireita.gridx = 0;
        gbcDireita.fill = GridBagConstraints.HORIZONTAL;
        gbcDireita.insets = new Insets(5, 0, 5, 0);
        gbcDireita.weightx = 1.0;

        int linha = 0;

        // Título "Cria sua conta"
        JLabel lblTituloDireita = new JLabel("Cria sua conta");
        lblTituloDireita.setFont(new Font("Montserrat", Font.BOLD, 32));
        lblTituloDireita.setForeground(COR_TEXTO_PRINCIPAL);
        lblTituloDireita.setHorizontalAlignment(SwingConstants.CENTER);
        gbcDireita.gridy = linha++;
        gbcDireita.insets = new Insets(5, 0, 20, 0);
        painelDireita.add(lblTituloDireita, gbcDireita);
        gbcDireita.insets = new Insets(5, 0, 5, 0);

        // Campos do formulário
        txtnome = new RoundedTextFieldPlaceholder(20, "Nome completo");
        gbcDireita.gridy = linha++;
        painelDireita.add(criarCampoComIcone(txtnome, "/TELAS/img/icons/user.png"), gbcDireita);

        txtcpf = new RoundedTextFieldPlaceholder(20, "CPF (apenas números)");
        gbcDireita.gridy = linha++;
        painelDireita.add(criarCampoComIcone(txtcpf, "/TELAS/img/icons/cpf.png"), gbcDireita);

        txtemail = new RoundedTextFieldPlaceholder(20, "Email");
        gbcDireita.gridy = linha++;
        painelDireita.add(criarCampoComIcone(txtemail, "/TELAS/img/icons/email.png"), gbcDireita);

        jpsenha = new RoundedPasswordField(20);
        gbcDireita.gridy = linha++;
        painelDireita.add(criarCampoComIcone(jpsenha, "/TELAS/img/icons/lock.png"), gbcDireita);

        genero = new RoundedComboBox<>(new String[]{" -- Selecione o Gênero -- ", "Masculino", "Feminino", "Outro"});
        gbcDireita.gridy = linha++;
        painelDireita.add(genero, gbcDireita);
        
        lblFeedback = new JLabel(" ");
        lblFeedback.setForeground(Color.RED);
        lblFeedback.setFont(new Font("Montserrat", Font.ITALIC, 12));
        gbcDireita.gridy = linha++;
        painelDireita.add(lblFeedback, gbcDireita);

        btnCadastrar = new RoundedButton("CADASTRAR");
        btnCadastrar.setFont(new Font("Montserrat", Font.BOLD, 16));
        btnCadastrar.setBackground(COR_BOTAO_ESQUERDA);
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setPreferredSize(new Dimension(0, 50));
        gbcDireita.gridy = linha++;
        gbcDireita.insets = new Insets(15, 0, 5, 0);
        painelDireita.add(btnCadastrar, gbcDireita);

        btnCadastrar.addActionListener(e -> cadastrarUsuario());
        
        tela.add(painelEsquerda);
        tela.add(painelDireita);

        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
    }
    
    private JPanel criarCampoComIcone(JComponent campo, String caminhoIcone) {
        JPanel painelCampo = new JPanel(new BorderLayout(10, 0));
        painelCampo.setOpaque(false);
        campo.setPreferredSize(new Dimension(100, 40));
        try {
            URL urlIcone = getClass().getResource(caminhoIcone);
            if (urlIcone != null) {
                ImageIcon iconeOriginal = new ImageIcon(urlIcone);
                Image imagemRedimensionada = iconeOriginal.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                JLabel lblIcone = new JLabel(new ImageIcon(imagemRedimensionada));
                lblIcone.setBorder(new EmptyBorder(0, 5, 0, 0));
                painelCampo.add(lblIcone, BorderLayout.WEST);
            }
        } catch (Exception e) {
            System.err.println("Ícone não encontrado: " + caminhoIcone);
            painelCampo.add(Box.createRigidArea(new Dimension(25, 0)), BorderLayout.WEST);
        }
        painelCampo.add(campo, BorderLayout.CENTER);
        return painelCampo;
    }

    private void cadastrarUsuario() {
        String cpf = txtcpf.getText().trim();
        String nome = txtnome.getText().trim();
        String email = txtemail.getText().trim();
        String senha = new String(jpsenha.getPassword()).trim();
        String generoSelecionado = (String) genero.getSelectedItem();

        if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || senha.isEmpty() || genero.getSelectedIndex() == 0) {
            lblFeedback.setText("Por favor, preencha todos os campos.");
            return;
        }
        if (cpf.length() != 11 || !cpf.matches("\\d+")) {
            lblFeedback.setText("CPF inválido! Deve conter 11 dígitos.");
            return;
        }
        if (!email.contains("@") || !email.contains(".")) {
            lblFeedback.setText("Formato de email inválido.");
            return;
        }
        try {
            UsuarioDAO dao = new UsuarioDAO();
            dao.salvar(cpf, email, nome, senha, generoSelecionado);
            JOptionPane.showMessageDialog(tela, "Usuário cadastrado com sucesso!");
            tela.dispose();
            new Login();
        } catch (Exception ex) {
            if (ex.getMessage().contains("Duplicate entry")) {
                lblFeedback.setText("Erro: CPF ou Email já cadastrado.");
            } else {
                lblFeedback.setText("Erro ao conectar ao banco de dados.");
            }
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new cadastro());
    }
}