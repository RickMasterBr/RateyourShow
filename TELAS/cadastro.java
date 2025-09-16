package TELAS;

import java.awt.*;
import java.net.URL;
import javax.swing.*;
import BD.UsuarioDAO;
import CLASSES.BackgroundPanel;
import CLASSES.RoundedButton;
import CLASSES.RoundedComboBox;
import CLASSES.RoundedPasswordField;
import CLASSES.RoundedTextFieldPlaceholder;
import CLASSES.RoundedPanel; // --- NOVO: Importa nosso painel customizado

public class cadastro {

    private JFrame tela;
    private RoundedTextFieldPlaceholder txtcpf, txtnome, txtemail;
    private RoundedPasswordField jpsenha;
    private RoundedComboBox<String> genero;
    private JLabel lblimagem, lblmsn, lblmsn2, lblcpf, lblnome, lblemail, lblsenha, lblgenero;
    
    // --- MELHORIA: Título principal e rótulo de feedback ---
    private JLabel lblTitulo;
    private JLabel lblFeedback; 
    
    private RoundedButton btnconfirmar;
    private ImageIcon iconLogo;
    private Dimension novaAltura = new Dimension(200, 38); // Altura padronizada

    public cadastro() {
        tela = new JFrame("Cadastro de Usuários");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(900, 700); // Aumentei a altura para melhor espaçamento
        tela.setResizable(false);

        // --- ESTRUTURA PRINCIPAL ---
        // 1. Painel de Fundo (com a imagem)
        URL urlFundo = getClass().getResource("/TELAS/img/background.png");
        Image imagemFundo = new ImageIcon(urlFundo).getImage();
        BackgroundPanel painelDeFundo = new BackgroundPanel(imagemFundo);
        painelDeFundo.setLayout(new GridBagLayout()); // Para centralizar o card

        // 2. Painel do Formulário (o "card" flutuante)
        // Usando nosso novo RoundedPanel com um fundo preto semi-transparente
        RoundedPanel formPanel = new RoundedPanel(new GridBagLayout(), 20, new Color(100, 100, 100, 200));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40)); // Padding interno

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int linha = 0;

        // --- MELHORIA: Título principal ---
        lblTitulo = new JLabel("Crie sua Conta");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Montserrat", Font.BOLD, 28));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = linha++;
        gbc.insets = new Insets(5, 5, 20, 5); // Mais espaço abaixo do título
        formPanel.add(lblTitulo, gbc);
        gbc.insets = new Insets(5, 5, 5, 5); // Reseta insets

        // --- ORGANIZAÇÃO EM DUAS COLUNAS (Nome e CPF na mesma linha) ---
        gbc.gridwidth = 1; // Voltamos para uma coluna por componente

        // Nome
        lblnome = new JLabel("Nome");
        lblnome.setForeground(Color.WHITE);
        lblnome.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = linha;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(lblnome, gbc);

        // CPF
        lblcpf = new JLabel("CPF");
        lblcpf.setForeground(Color.WHITE);
        lblcpf.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = linha++;
        formPanel.add(lblcpf, gbc);

        // Campo Nome
        txtnome = new RoundedTextFieldPlaceholder(15, "Digite seu nome");
        txtnome.setPreferredSize(novaAltura);
        gbc.gridx = 0;
        gbc.gridy = linha;
        formPanel.add(txtnome, gbc);

        // Campo CPF
        txtcpf = new RoundedTextFieldPlaceholder(15, "Digite seu CPF");
        txtcpf.setPreferredSize(novaAltura);
        gbc.gridx = 1;
        gbc.gridy = linha++;
        formPanel.add(txtcpf, gbc);

        // Email
        lblemail = new JLabel("Email");
        lblemail.setForeground(Color.WHITE);
        lblemail.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = linha++;
        gbc.gridwidth = 2; // Ocupa duas colunas
        formPanel.add(lblemail, gbc);

        txtemail = new RoundedTextFieldPlaceholder(22, "Digite seu email");
        txtemail.setPreferredSize(novaAltura);
        gbc.gridx = 0;
        gbc.gridy = linha++;
        formPanel.add(txtemail, gbc);

        // Senha
        lblsenha = new JLabel("Senha");
        lblsenha.setForeground(Color.WHITE);
        lblsenha.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = linha++;
        formPanel.add(lblsenha, gbc);

        jpsenha = new RoundedPasswordField(22);
        jpsenha.setPreferredSize(novaAltura);
        gbc.gridx = 0;
        gbc.gridy = linha++;
        formPanel.add(jpsenha, gbc);

        // Gênero
        lblgenero = new JLabel("Gênero");
        lblgenero.setForeground(Color.WHITE);
        lblgenero.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = linha++;
        formPanel.add(lblgenero, gbc);
        
        genero = new RoundedComboBox<>(new String[] { " -- Selecione o Gênero -- ", "Masculino", "Feminino", "Outro" });
        genero.setPreferredSize(novaAltura); // Altura padronizada
        gbc.gridx = 0;
        gbc.gridy = linha++;
        formPanel.add(genero, gbc);
        gbc.gridwidth = 1;


        // --- MELHORIA: Rótulo de Feedback (substitui JOptionPane) ---
        lblFeedback = new JLabel(" "); // Começa com um espaço para ocupar altura
        lblFeedback.setForeground(new Color(255, 100, 100)); // Cor de erro
        lblFeedback.setFont(new Font("Montserrat", Font.ITALIC, 12));
        lblFeedback.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = linha++;
        gbc.insets = new Insets(10, 5, 5, 5);
        formPanel.add(lblFeedback, gbc);
        gbc.insets = new Insets(5, 5, 5, 5);

        // Botão Confirmar
        btnconfirmar = new RoundedButton("Cadastre-se");
        gbc.gridx = 0;
        gbc.gridy = linha++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE; // Para não esticar o botão
        formPanel.add(btnconfirmar, gbc);
        
        // Link para Login
        JPanel painelLoginLink = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        painelLoginLink.setOpaque(false);
        lblmsn = new JLabel("Já está registrado?");
        lblmsn.setForeground(Color.WHITE);
        lblmsn.setFont(new Font("Montserrat", Font.PLAIN, 12));
        lblmsn2 = new JLabel("Faça o login");
        lblmsn2.setForeground(Color.YELLOW);
        lblmsn2.setFont(new Font("Montserrat", Font.BOLD, 12));
        lblmsn2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        painelLoginLink.add(lblmsn);
        painelLoginLink.add(lblmsn2);
        gbc.gridx = 0;
        gbc.gridy = linha++;
        gbc.insets = new Insets(15, 5, 5, 5); // Espaço acima do link
        formPanel.add(painelLoginLink, gbc);

        // --- MONTAGEM FINAL ---
        // Adiciona o painel do formulário ao painel de fundo (que o centralizará)
        painelDeFundo.add(formPanel);
        
        // Define o painel de fundo como o conteúdo principal da tela
        tela.setContentPane(painelDeFundo);
        
        // --- AÇÕES ---
        btnconfirmar.addActionListener(e -> cadastrarUsuario());
        
        lblmsn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                tela.dispose();
                new Login();
            }
        });

        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
    }
    
    // --- MÉTODO DE CADASTRO refatorado para usar o lblFeedback ---
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
        if (cpf.length() != 11 || !cpf.matches("\\d+")) { // Valida se tem 11 dígitos numéricos
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
            JOptionPane.showMessageDialog(tela, "Usuário cadastrado com sucesso!"); // Sucesso ainda pode ser um popup
            tela.dispose();
            new Login();
        } catch (Exception ex) {
            // Tratamento de erro mais específico do banco
            if (ex.getMessage().contains("Duplicate entry")) {
                lblFeedback.setText("Erro: CPF ou Email já cadastrado.");
            } else {
                lblFeedback.setText("Erro ao conectar ao banco de dados.");
            }
            ex.printStackTrace(); // Para depuração no console
        }
    }
}