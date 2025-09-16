package TELAS;

import java.awt.*;
import javax.swing.*;
import CLASSES.BackgroundPanel;
import CLASSES.RoundedButton;
import CLASSES.RoundedPasswordField;
import CLASSES.RoundedTextFieldPlaceholder;
import CLASSES.RoundedPanel; // Importa nosso painel customizado
import java.net.URL;

public class Login {
    private JFrame tela;
    private RoundedTextFieldPlaceholder txtcpf; // Usando o campo com placeholder
    private RoundedPasswordField jpsenha; // Usando o campo de senha arredondado
    private JLabel lblcpf, lblsenha, lblimagem, lblTitulo, lblmsn, lblmsn2, lblFeedback;
    private ImageIcon iconLogo;
    private RoundedButton btnlogin; // Usando o botão arredondado
    private Dimension novaAltura = new Dimension(280, 40); // Largura um pouco maior para o card menor

    public Login() {
        tela = new JFrame("Login de Usuário");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(800, 600); // Mesmo tamanho da tela de cadastro para transição suave
        tela.setResizable(false);

        // 1. Painel de Fundo
        URL urlFundo = getClass().getResource("/TELAS/img/background.png");
        Image imagemFundo = new ImageIcon(urlFundo).getImage();
        BackgroundPanel painelDeFundo = new BackgroundPanel(imagemFundo);
        painelDeFundo.setLayout(new GridBagLayout()); // Para centralizar o card

        // 2. Painel do Formulário ("card")
        RoundedPanel formPanel = new RoundedPanel(new GridBagLayout(), 20, new Color(100, 100, 100, 200));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40)); // Padding interno

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int linha = 0;

        // Logo
        URL urlLogo = getClass().getResource("/TELAS/img/logo.png");
        if (urlLogo != null) {
            iconLogo = new ImageIcon(urlLogo);
        }
        lblimagem = new JLabel(iconLogo);
        gbc.gridx = 0;
        gbc.gridy = linha++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5, 5, 15, 5);
        formPanel.add(lblimagem, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Título
        lblTitulo = new JLabel("Bem-vindo de volta!");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Montserrat", Font.BOLD, 28));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = linha++;
        gbc.insets = new Insets(5, 5, 20, 5);
        formPanel.add(lblTitulo, gbc);
        gbc.insets = new Insets(5, 5, 5, 5);

        // CPF
        lblcpf = new JLabel("CPF");
        lblcpf.setForeground(Color.WHITE);
        lblcpf.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = linha++;
        formPanel.add(lblcpf, gbc);

        txtcpf = new RoundedTextFieldPlaceholder(22, "Digite seu CPF");
        txtcpf.setPreferredSize(novaAltura);
        gbc.gridx = 0;
        gbc.gridy = linha++;
        formPanel.add(txtcpf, gbc);

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
        
        // Rótulo de Feedback
        lblFeedback = new JLabel(" ");
        lblFeedback.setForeground(new Color(255, 100, 100));
        lblFeedback.setFont(new Font("Montserrat", Font.ITALIC, 12));
        lblFeedback.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = linha++;
        gbc.insets = new Insets(10, 5, 5, 5);
        formPanel.add(lblFeedback, gbc);
        gbc.insets = new Insets(5, 5, 5, 5);

        // Botão de login
        btnlogin = new RoundedButton("Logar");
        gbc.gridx = 0;
        gbc.gridy = linha++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(btnlogin, gbc);
        
        // Link para Cadastro
        JPanel painelCadastroLink = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        painelCadastroLink.setOpaque(false);
        lblmsn = new JLabel("Não possui conta?");
        lblmsn.setForeground(Color.WHITE);
        lblmsn.setFont(new Font("Montserrat", Font.PLAIN, 12));
        lblmsn2 = new JLabel("Cadastre-se");
        lblmsn2.setForeground(Color.YELLOW);
        lblmsn2.setFont(new Font("Montserrat", Font.BOLD, 12));
        lblmsn2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        painelCadastroLink.add(lblmsn);
        painelCadastroLink.add(lblmsn2);
        gbc.gridx = 0;
        gbc.gridy = linha++;
        gbc.insets = new Insets(15, 5, 5, 5);
        formPanel.add(painelCadastroLink, gbc);
        
        // Adiciona o card ao fundo
        painelDeFundo.add(formPanel);
        tela.setContentPane(painelDeFundo);

        // Ações
        lblmsn2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                tela.dispose();
                new cadastro();
            }
        });

        btnlogin.addActionListener(e -> {
            String cpf = txtcpf.getText().trim();
            String senha = new String(jpsenha.getPassword()).trim();
            
            if (cpf.isEmpty() || senha.isEmpty()) {
                lblFeedback.setText("Por favor, preencha CPF e Senha.");
                return;
            }
            
            // Lógica de verificação no banco de dados (exemplo)
            // Você precisará criar um método em UsuarioDAO para verificar o login
            /* try {
                UsuarioDAO dao = new UsuarioDAO();
                if (dao.verificarLogin(cpf, senha)) {
                    JOptionPane.showMessageDialog(tela, "Login realizado!");
                    tela.dispose();
                    // new Home(); // sua tela principal
                } else {
                    lblFeedback.setText("CPF ou senha inválidos.");
                }
            } catch (Exception ex) {
                lblFeedback.setText("Erro de conexão com o banco.");
            }
            */
            
            // Lógica de placeholder por enquanto:
             if (cpf.equals("123") && senha.equals("123")) {
                 JOptionPane.showMessageDialog(tela, "Login realizado!");
             } else {
                 lblFeedback.setText("CPF ou senha inválidos.");
             }
        });

        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
    }
}