package TELAS;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import CLASSES.BackgroundPanel;
import CLASSES.RoundedButton;
import CLASSES.RoundedPasswordField;
import CLASSES.RoundedPanel;
import CLASSES.RoundedTextFieldPlaceholder;
import CLASSES.ThemeManager;
import java.net.URL;

public class Login {

    private JFrame tela;
    private RoundedTextFieldPlaceholder txtcpf;
    private RoundedPasswordField jpsenha;
    private JLabel lblFeedback;
    private RoundedButton btnlogin;

    // --- CORES PADRÃO ---
    private final Color COR_BOTAO = new Color(50, 180, 120);
    private final Color COR_FUNDO_DIREITA = new Color(240, 240, 240);
    private final Color COR_TEXTO_PRINCIPAL = new Color(60, 60, 60);

    public Login() {
        tela = new JFrame("Login de Usuário");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(1000, 700);
        tela.setResizable(false);
        tela.setLayout(new GridLayout(1, 2));

        // =====================================================================
        // PAINEL DA ESQUERDA (VISUAL)
        // =====================================================================
        URL urlFundo = getClass().getResource("/TELAS/img/background3.jpg");
        Image imagemFundo = new ImageIcon(urlFundo).getImage();
        BackgroundPanel painelEsquerda = new BackgroundPanel(imagemFundo);
        painelEsquerda.setLayout(new GridBagLayout());
        painelEsquerda.setBorder(new EmptyBorder(20, 40, 20, 40));

        GridBagConstraints gbcEsquerda = new GridBagConstraints();
        gbcEsquerda.gridwidth = GridBagConstraints.REMAINDER;
        gbcEsquerda.anchor = GridBagConstraints.CENTER;

        JPanel conteudoEsquerda = new JPanel();
        conteudoEsquerda.setLayout(new BoxLayout(conteudoEsquerda, BoxLayout.Y_AXIS));
        conteudoEsquerda.setOpaque(false);
        conteudoEsquerda.setName("conteudoEsquerda");

        JLabel lblTituloEsquerda = new JLabel("Olá, novamente!");
        lblTituloEsquerda.setFont(new Font("Montserrat", Font.BOLD, 36));
        lblTituloEsquerda.setForeground(Color.WHITE);
        lblTituloEsquerda.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtituloEsquerda = new JLabel("Ainda não tem uma conta? Crie uma agora.");
        lblSubtituloEsquerda.setFont(new Font("Montserrat", Font.PLAIN, 14));
        lblSubtituloEsquerda.setForeground(Color.WHITE);
        lblSubtituloEsquerda.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundedButton btnCadastreSe = new RoundedButton("CADASTRE-SE");
        btnCadastreSe.setFont(new Font("Montserrat", Font.BOLD, 16));
        btnCadastreSe.setPreferredSize(new Dimension(200, 50));
        btnCadastreSe.setMaximumSize(new Dimension(200, 50));
        btnCadastreSe.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCadastreSe.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastreSe.addActionListener(e -> {
            tela.dispose();
            new cadastro();
        });

        conteudoEsquerda.add(lblTituloEsquerda);
        conteudoEsquerda.add(Box.createRigidArea(new Dimension(0, 10)));
        conteudoEsquerda.add(lblSubtituloEsquerda);
        conteudoEsquerda.add(Box.createRigidArea(new Dimension(0, 30)));
        conteudoEsquerda.add(btnCadastreSe);
        painelEsquerda.add(conteudoEsquerda, gbcEsquerda);

        // =====================================================================
        // PAINEL DA DIREITA (FORMULÁRIO DE LOGIN)
        // =====================================================================
        JPanel painelDireita = new JPanel(new GridBagLayout());
        painelDireita.setBorder(new EmptyBorder(40, 60, 40, 60));

        GridBagConstraints gbcDireita = new GridBagConstraints();
        gbcDireita.gridx = 0;
        gbcDireita.gridwidth = 2;
        gbcDireita.fill = GridBagConstraints.HORIZONTAL;
        gbcDireita.insets = new Insets(5, 0, 5, 0);
        gbcDireita.weightx = 1.0;
        
        int linha = 0;
        
        JLabel lblTituloDireita = new JLabel("Acesse sua Conta");
        lblTituloDireita.setFont(new Font("Montserrat", Font.BOLD, 32));
        lblTituloDireita.setHorizontalAlignment(SwingConstants.CENTER);
        gbcDireita.gridy = linha++;
        gbcDireita.insets = new Insets(5, 0, 30, 0);
        painelDireita.add(lblTituloDireita, gbcDireita);
        gbcDireita.insets = new Insets(5, 0, 5, 0);

        JLabel lblcpf = new JLabel("CPF");
        lblcpf.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbcDireita.gridy = linha++;
        painelDireita.add(lblcpf, gbcDireita);

        txtcpf = new RoundedTextFieldPlaceholder(22, "Digite seu CPF");
        txtcpf.setPreferredSize(new Dimension(100, 40));
        gbcDireita.gridy = linha++;
        painelDireita.add(txtcpf, gbcDireita);

        JLabel lblsenha = new JLabel("Senha");
        lblsenha.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbcDireita.gridy = linha++;
        gbcDireita.insets = new Insets(10, 0, 5, 0);
        painelDireita.add(lblsenha, gbcDireita);
        gbcDireita.insets = new Insets(5, 0, 5, 0);

        jpsenha = new RoundedPasswordField(22);
        jpsenha.setPreferredSize(new Dimension(100, 40));
        gbcDireita.gridy = linha++;
        painelDireita.add(jpsenha, gbcDireita);
        
        lblFeedback = new JLabel(" ");
        lblFeedback.setForeground(Color.RED);
        lblFeedback.setFont(new Font("Montserrat", Font.ITALIC, 12));
        gbcDireita.gridy = linha++;
        painelDireita.add(lblFeedback, gbcDireita);

        btnlogin = new RoundedButton("LOGAR");
        btnlogin.setFont(new Font("Montserrat", Font.BOLD, 16));
        btnlogin.setPreferredSize(new Dimension(0, 50));
        gbcDireita.gridy = linha++;
        gbcDireita.insets = new Insets(15, 0, 5, 0);
        painelDireita.add(btnlogin, gbcDireita);

        btnlogin.addActionListener(e -> {
            String cpf = txtcpf.getText().trim();
            String senha = new String(jpsenha.getPassword()).trim();
            if (cpf.isEmpty() || senha.isEmpty()) {
                lblFeedback.setText("Por favor, preencha CPF e Senha.");
                return;
            }
            if (cpf.equals("123") && senha.equals("123")) {
                JOptionPane.showMessageDialog(tela, "Login realizado!");
                tela.dispose();
                new Home();
            } else {
                lblFeedback.setText("CPF ou senha inválidos.");
            }
        });

        // --- MONTAGEM FINAL ---
        tela.add(painelEsquerda);
        tela.add(painelDireita);

        ThemeManager.applyTheme(tela);

        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
    }
}