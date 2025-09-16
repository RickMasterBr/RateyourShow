package TELAS;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.*;
import BD.UsuarioDAO;
import CLASSES.BackgroundPanel;
import CLASSES.RoundedButton;
import CLASSES.RoundedComboBox;
import CLASSES.RoundedPasswordField;
import CLASSES.RoundedTextFieldPlaceholder;

public class Perfil {

    private JFrame tela;
    private RoundedTextFieldPlaceholder txtemail, txtnomeusuario;
    private RoundedPasswordField jpsenha;
    private RoundedComboBox<String> genero;
    private JLabel lblUsuario, lblSair, lblEmail, lblNomeUsuario, lblSenha, lblGenero, lblAviso, lblLogo;
    private RoundedButton btnAlterarDados, btnExcluirConta;
    private ImageIcon iconUsuario, iconLogo;
    private Dimension novaAltura = new Dimension(200, 40);

    // DADOS DO USUÁRIO LOGADO - você precisaria passar isso do Login.
    private String emailUsuarioLogado = "steff@rateyourshow.com";
    private String nomeUsuarioLogado = "usuario_0";
    private String generoUsuarioLogado = "Homem cis";

    public Perfil() {
        tela = new JFrame("RateyourShow - Perfil");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(600, 700);
        tela.setResizable(false);
        
        URL urlFundo = getClass().getResource("/TELAS/img/background.png");
        Image imagemFundo = new ImageIcon(urlFundo).getImage();
        JPanel painelPrincipal = new BackgroundPanel(imagemFundo);
        painelPrincipal.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        int linha = 0;

        // Logo e Link de Sair (Não é sua conta? Faça login)
        JPanel painelHeader = new JPanel(new BorderLayout(20, 0));
        painelHeader.setOpaque(false);
        URL urlUsuario = getClass().getResource("/TELAS/img/usuario.png");
        if(urlUsuario != null) {
            iconUsuario = new ImageIcon(urlUsuario);
        } else {
            iconUsuario = new ImageIcon(); // Icone vazio se não encontrar
        }
        
        lblUsuario = new JLabel("Usuário", iconUsuario, SwingConstants.LEFT);
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("Montserrat", Font.PLAIN, 16));
        lblUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel linkLogin = new JLabel("Não é sua conta? Faça login");
        linkLogin.setForeground(new Color(173, 255, 47));
        linkLogin.setFont(new Font("Montserrat", Font.PLAIN, 14));
        linkLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        linkLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tela.dispose();
                new Login();
            }
        });

        painelHeader.add(lblUsuario, BorderLayout.WEST);
        painelHeader.add(linkLogin, BorderLayout.EAST);
        
        gbc.gridx = 0;
        gbc.gridy = linha;
        gbc.gridwidth = 2; // Ocupa a largura de 2 colunas
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painelPrincipal.add(painelHeader, gbc);
        linha++;

        // Logo central
        URL urlLogo = getClass().getResource("/TELAS/img/logo.png");
        if (urlLogo != null) {
            iconLogo = new ImageIcon(urlLogo);
        } else {
            iconLogo = new ImageIcon(); // Ícone vazio se não encontrar
        }
        lblLogo = new JLabel(iconLogo);
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = linha;
        gbc.anchor = GridBagConstraints.CENTER;
        painelPrincipal.add(lblLogo, gbc);
        linha++;

        // Campos de dados do usuário
        // Campo Email
        lblEmail = new JLabel("EMAIL");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbc.gridx = 0; gbc.gridy = linha; gbc.anchor = GridBagConstraints.WEST;
        painelPrincipal.add(lblEmail, gbc);
        linha++;
        txtemail = new RoundedTextFieldPlaceholder(22, "");
        txtemail.setText(emailUsuarioLogado);
        txtemail.setPreferredSize(novaAltura);
        gbc.gridy = linha;
        painelPrincipal.add(txtemail, gbc);
        linha++;

        // Campo Nome de Usuário
        lblNomeUsuario = new JLabel("NOME DE USUÁRIO");
        lblNomeUsuario.setForeground(Color.WHITE);
        lblNomeUsuario.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbc.gridy = linha;
        painelPrincipal.add(lblNomeUsuario, gbc);
        linha++;
        txtnomeusuario = new RoundedTextFieldPlaceholder(22, "");
        txtnomeusuario.setText(nomeUsuarioLogado);
        txtnomeusuario.setPreferredSize(novaAltura);
        gbc.gridy = linha;
        painelPrincipal.add(txtnomeusuario, gbc);
        linha++;

        // Campo Senha
        lblSenha = new JLabel("SENHA");
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbc.gridy = linha;
        painelPrincipal.add(lblSenha, gbc);
        linha++;
        jpsenha = new RoundedPasswordField(22);
        jpsenha.setPreferredSize(novaAltura);
        jpsenha.setEchoChar('*');
        gbc.gridy = linha;
        painelPrincipal.add(jpsenha, gbc);
        linha++;

        // ComboBox Gênero
        lblGenero = new JLabel("GÊNERO");
        lblGenero.setForeground(Color.WHITE);
        lblGenero.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbc.gridy = linha;
        painelPrincipal.add(lblGenero, gbc);
        linha++;
        genero = new RoundedComboBox<>(new String[]{"Homem cis", "Mulher cis", "Homem trans", "Mulher trans", "Não binário", "Outro", "Prefiro não informar"});
        genero.setSelectedItem(generoUsuarioLogado);
        genero.setPreferredSize(novaAltura);
        gbc.gridy = linha;
        painelPrincipal.add(genero, gbc);
        linha++;

        // Botões de Ação
        btnAlterarDados = new RoundedButton("Alterar dados");
        // Ajuste de cores para ser mais fiel à imagem (do cinza/ciano para o verde)
        btnAlterarDados.setBackground(new Color(0, 150, 136)); 
        btnAlterarDados.setForeground(Color.WHITE);
        btnAlterarDados.setFont(new Font("Montserrat", Font.BOLD, 14));
        btnAlterarDados.setPreferredSize(new Dimension(200, 45));
        gbc.gridy = linha; gbc.anchor = GridBagConstraints.CENTER;
        painelPrincipal.add(btnAlterarDados, gbc);
        linha++;

        btnExcluirConta = new RoundedButton("Excluir conta");
        btnExcluirConta.setBackground(new Color(255, 99, 71)); 
        btnExcluirConta.setForeground(Color.WHITE);
        btnExcluirConta.setFont(new Font("Montserrat", Font.BOLD, 14));
        btnExcluirConta.setPreferredSize(new Dimension(200, 45));
        gbc.gridy = linha;
        painelPrincipal.add(btnExcluirConta, gbc);
        linha++;
        
        // Aviso
        lblAviso = new JLabel("É necessária senha para realizar mudança dos dados ou excluir conta", SwingConstants.CENTER);
        lblAviso.setForeground(Color.WHITE);
        lblAviso.setFont(new Font("Montserrat", Font.PLAIN, 12));
        gbc.gridy = linha;
        painelPrincipal.add(lblAviso, gbc);
        linha++;

        // Ações dos botões - a lógica do backend ainda precisa ser implementada
        btnAlterarDados.addActionListener(e -> {
            JOptionPane.showMessageDialog(tela, "Funcionalidade 'Alterar dados' em desenvolvimento.");
        });

        btnExcluirConta.addActionListener(e -> {
            JOptionPane.showMessageDialog(tela, "Funcionalidade 'Excluir conta' em desenvolvimento.");
        });

        tela.setContentPane(painelPrincipal);
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
    }
}