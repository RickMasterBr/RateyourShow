package TELAS;

import java.awt.*;
import javax.swing.*;

import CLASSES.BackgroundPanel;
import CLASSES.RoundedButton;
import CLASSES.RoundedPasswordField;
import CLASSES.RoundedTextFieldPlaceholder;

import java.net.URL;

public class Login {
    private JFrame tela;
    private JTextField txtcpf;
    private JLabel lblcpf, lblsenha, lblimagem, lbltitulo, lblmsn, lblmsn2, lblmsn3;
    private ImageIcon iconLogo;
    private JPasswordField jpsenha;
    private JButton btnlogin;
    private Dimension novaAltura = new Dimension(200, 40);

    public Login() {
        tela = new JFrame("Login de Usuário");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(500, 400);
        tela.setLayout(new GridBagLayout());
        tela.setResizable(false);
        tela.getContentPane().setBackground(new Color(100, 100, 100));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        int linha = 0;

        // Fundo
        URL urlFundo = getClass().getResource("/TELAS/img/background.png");
        Image imagemFundo = new ImageIcon(urlFundo).getImage();
        // Supondo que você tenha uma classe BackgroundPanel que aceita uma Imagem
        JPanel painelPrincipal = new BackgroundPanel(imagemFundo);
        painelPrincipal.setLayout(new GridBagLayout()); // Adicionado layout ao painel principal

        // Logo
        iconLogo = new ImageIcon("TELAS/img/logo.png");
        lblimagem = new JLabel();
        lblimagem.setIcon(iconLogo);
        lblimagem.setHorizontalTextPosition(SwingConstants.CENTER);
        lblimagem.setVerticalTextPosition(SwingConstants.BOTTOM);
        gbc.gridx = 0;
        gbc.gridy = linha;
        painelPrincipal.add(lblimagem, gbc);
        linha++;

        // Título
        lbltitulo = new JLabel("Login");
        lbltitulo.setForeground(Color.YELLOW);
        // FONTE APLICADA (maior e em negrito)
        lbltitulo.setFont(new Font("Montserrat", Font.BOLD, 30));
        gbc.gridx = 0;
        gbc.gridy = linha;
        painelPrincipal.add(lbltitulo, gbc);
        linha++;

        // mensagem
        lblmsn = new JLabel("Não possui conta?");
        lblmsn.setForeground(Color.WHITE);
        lblmsn.setFont(new Font("Montserrat", Font.PLAIN, 12));

        lblmsn2 = new JLabel("Cadastre-se");
        lblmsn2.setForeground(Color.YELLOW);
        lblmsn2.setFont(new Font("Montserrat", Font.BOLD, 12));

        lblmsn2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblmsn2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                tela.dispose(); // Fecha login
                new cadastro(); // Abre cadastro
            }
        });

        // Usando um painel para juntar as duas mensagens
        JPanel painelCadastroLink = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        painelCadastroLink.setOpaque(false); // Deixa o painel transparente
        painelCadastroLink.add(lblmsn);
        painelCadastroLink.add(lblmsn2);
        gbc.gridx = 0;
        gbc.gridy = linha;
        painelPrincipal.add(painelCadastroLink, gbc);
        linha++;

        // CPF
        lblcpf = new JLabel("CPF");
        lblcpf.setForeground(Color.WHITE);
        // FONTE APLICADA
        lblcpf.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbc.anchor = GridBagConstraints.WEST; // Alinha os rótulos à esquerda
        gbc.insets = new Insets(5, 5, 0, 5); // Ajusta o espaçamento
        gbc.gridx = 0;
        gbc.gridy = linha;
        painelPrincipal.add(lblcpf, gbc);
        linha++;
        txtcpf = new RoundedTextFieldPlaceholder(22, "Digite seu CPF");
        txtcpf.setPreferredSize(novaAltura); // Aplica a nova altura
        gbc.insets = new Insets(0, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = linha;
        painelPrincipal.add(txtcpf, gbc);
        linha++;

        // Senha
        lblsenha = new JLabel("Senha");
        lblsenha.setForeground(Color.WHITE);
        // FONTE APLICADA
        lblsenha.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbc.insets = new Insets(5, 5, 0, 5);
        gbc.gridx = 0;
        gbc.gridy = linha;
        painelPrincipal.add(lblsenha, gbc);
        linha++;
        jpsenha = new RoundedPasswordField(22);
        jpsenha.setPreferredSize(novaAltura); // Aplica a nova altura
        gbc.insets = new Insets(0, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = linha;
        painelPrincipal.add(jpsenha, gbc);
        linha++;

        // Mensagem3
        lblmsn3 = new JLabel("ESQUECEU A SENHA?");
        lblmsn3.setForeground(Color.GREEN);
        lblmsn3.setFont(new Font("Montserrat", Font.ITALIC, 10));
        gbc.anchor = GridBagConstraints.CENTER; // Centraliza
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = linha;
        painelPrincipal.add(lblmsn3, gbc);
        linha++;

        // Botão de login
        btnlogin = new RoundedButton("Logar");
        btnlogin.setBackground(Color.WHITE);
        btnlogin.setForeground(Color.black);
        btnlogin.setFont(new Font("Montserrat", Font.BOLD, 14));
        gbc.fill = GridBagConstraints.HORIZONTAL; // Faz o botão preencher o espaço
        gbc.gridx = 0;
        gbc.gridy = linha;
        painelPrincipal.add(btnlogin, gbc);

        /* 
         btnlogin.addActionListener(e -> {
            String cpf = txtcpf.getText().trim();
            String senha = new String(jpsenha.getPassword()).trim();
            
            if (cpf.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(tela, "Preencha CPF e Senha!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Aqui depois você fará SELECT no banco para verificar usuário
            if (cpf.equals("12345678901") && senha.equals("123")) {
                JOptionPane.showMessageDialog(tela, "Login realizado!");
                tela.dispose();
                new Home(); // sua tela principal futura
            } else {
                JOptionPane.showMessageDialog(tela, "CPF ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        */
        
        tela.setContentPane(painelPrincipal);

        // visualizar tela
        tela.setLocationRelativeTo(null); // Centraliza a tela
        tela.setVisible(true);
    }
}
