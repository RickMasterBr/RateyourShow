package TELAS;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import BD.Sessao;
import BD.Usuario;
import BD.UsuarioDAO;
import CLASSES.BackgroundPanel;
import CLASSES.RoundedButton;
import CLASSES.RoundedComboBox;
import CLASSES.RoundedPasswordField;
import CLASSES.RoundedTextFieldPlaceholder;
import CLASSES.ThemeManager;
import CLASSES.RoundedPanel;

public class Perfil {

    private JFrame tela;
    private RoundedTextFieldPlaceholder txtemail, txtnomeusuario;
    private RoundedPasswordField jpsenha;
    private RoundedComboBox<String> genero;
    private JLabel lblUsuario, lblEmail, lblNomeUsuario, lblSenha, lblGenero, lblAviso;
    private RoundedButton btnAlterarDados, btnExcluirConta;
    private ImageIcon iconUsuario;
    private Dimension novaAltura = new Dimension(250, 40);
    private Usuario u = Sessao.getUsuarioLogado();
    private UsuarioDAO dao;

    private String emailUsuarioLogado = u.getEmail();
    private String nomeUsuarioLogado = u.getNome();
    private String generoUsuarioLogado = u.getGenero();

    public Perfil() {
        dao = new UsuarioDAO();
        tela = new JFrame("RateyourShow - Perfil");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(800, 700); // Padronizando o tamanho da janela
        tela.setResizable(false);
        
        // --- ESTRUTURA PADRÃO ---
        // 1. Painel de Fundo
        URL urlFundo = getClass().getResource("/TELAS/img/background3.jpg");
        Image imagemFundo = new ImageIcon(urlFundo).getImage();
        BackgroundPanel painelDeFundo = new BackgroundPanel(imagemFundo);
        painelDeFundo.setLayout(new GridBagLayout());

        // 2. Painel "Card" para o conteúdo
        RoundedPanel formPanel = new RoundedPanel(new GridBagLayout(), 20, new Color(100, 100, 100, 200));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        formPanel.setBackground(new Color(255, 255, 255, 0)); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // Espaçamento entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int linha = 0;

        // --- CABEÇALHO DENTRO DO CARD ---
        JPanel painelHeader = new JPanel(new BorderLayout());
        painelHeader.setOpaque(false);

        // Ícone e nome de usuário
        URL urlUsuario = getClass().getResource("/TELAS/img/usuario.png");
        if(urlUsuario != null) {
            // Redimensionando o ícone para ficar melhor
            ImageIcon originalIcon = new ImageIcon(urlUsuario);
            Image scaledImage = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            iconUsuario = new ImageIcon(scaledImage);
        }
        lblUsuario = new JLabel(nomeUsuarioLogado, iconUsuario, SwingConstants.LEFT);
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("Montserrat", Font.BOLD, 16));
        painelHeader.add(lblUsuario, BorderLayout.WEST);

        // Link de Sair
        JLabel linkSair = new JLabel("Sair");
        linkSair.setForeground(Color.YELLOW);
        linkSair.setFont(new Font("Montserrat", Font.BOLD, 14));
        linkSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        linkSair.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Lógica para logout
                Sessao.encerrarSessao();
                tela.dispose();
                new Login();
            }
        });
        painelHeader.add(linkSair, BorderLayout.EAST);
        
        gbc.gridx = 0;
        gbc.gridy = linha++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 5, 20, 5); // Espaço abaixo do header
        formPanel.add(painelHeader, gbc);
        gbc.insets = new Insets(5, 10, 5, 10); // Reseta insets

        // --- CAMPOS DO FORMULÁRIO ---
        
        // Email
        lblEmail = new JLabel("Email");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbc.gridy = linha++;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(lblEmail, gbc);

        txtemail = new RoundedTextFieldPlaceholder(22, "");
        txtemail.setPreferredSize(novaAltura);
        txtemail.setText(emailUsuarioLogado);
        gbc.gridy = linha++;
        formPanel.add(txtemail, gbc);

        // Nome de Usuário
        lblNomeUsuario = new JLabel("Nome de Usuário");
        lblNomeUsuario.setForeground(Color.WHITE);
        lblNomeUsuario.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbc.gridy = linha++;
        formPanel.add(lblNomeUsuario, gbc);

        txtnomeusuario = new RoundedTextFieldPlaceholder(22, "");
        txtnomeusuario.setPreferredSize(novaAltura);
        txtnomeusuario.setText(nomeUsuarioLogado);
        gbc.gridy = linha++;
        formPanel.add(txtnomeusuario, gbc);

        // Senha
        lblSenha = new JLabel("Nova Senha (deixe em branco para não alterar)");
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbc.gridy = linha++;
        formPanel.add(lblSenha, gbc);

        jpsenha = new RoundedPasswordField(22);
        jpsenha.setPreferredSize(novaAltura);
        gbc.gridy = linha++;
        formPanel.add(jpsenha, gbc);

        // Gênero
        lblGenero = new JLabel("Gênero");
        lblGenero.setForeground(Color.WHITE);
        lblGenero.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbc.gridy = linha++;
        formPanel.add(lblGenero, gbc);
        
        genero = new RoundedComboBox<>(new String[]{ " -- Selecione o Gênero -- ", "Masculino", "Feminino", "Outro" });
        genero.setSelectedItem(generoUsuarioLogado);
        genero.setPreferredSize(novaAltura);
        gbc.gridy = linha++;
        formPanel.add(genero, gbc);

        // Aviso de Feedback
        lblAviso = new JLabel(" ", SwingConstants.CENTER); // Começa vazio
        lblAviso.setForeground(Color.WHITE);
        lblAviso.setFont(new Font("Montserrat", Font.ITALIC, 12));
        gbc.gridy = linha++;
        gbc.insets = new Insets(10, 5, 5, 5);
        formPanel.add(lblAviso, gbc);
        gbc.insets = new Insets(5, 10, 5, 10);

        // --- BOTÕES DE AÇÃO ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelBotoes.setOpaque(false);

        btnAlterarDados = new RoundedButton("Alterar Dados");
        btnAlterarDados.setPreferredSize(new Dimension(180, 42));
        painelBotoes.add(btnAlterarDados);

        btnExcluirConta = new RoundedButton("Excluir Conta");
        btnExcluirConta.setPreferredSize(new Dimension(180, 42));
        painelBotoes.add(btnExcluirConta);

        gbc.gridy = linha++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(painelBotoes, gbc);

        // --- MONTAGEM FINAL ---
        painelDeFundo.add(formPanel);
        tela.setContentPane(painelDeFundo);
        
        ThemeManager.applyTheme(tela);

        // Ações
        btnAlterarDados.addActionListener(e -> {
            try {
                String cpf = u.getCpf();
                String email = txtemail.getText();
                String nome = txtnomeusuario.getText();
                String senha = new String(jpsenha.getPassword());
                String generoNovo = genero.getSelectedItem().toString();
                dao.atualizar(cpf, email, nome, senha, generoNovo);
                JOptionPane.showMessageDialog(tela, "Dados atualizados com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(tela, "Erro: " + ex);
            }

        });

        btnExcluirConta.addActionListener(e -> {
            // Lógica futura aqui
            int confirm = JOptionPane.showConfirmDialog(tela, 
                "Tem certeza que deseja excluir sua conta?\nEsta ação é irreversível.", 
                "Confirmar Exclusão", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);
            
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    String cpf = u.getCpf();
                    dao.excluir(cpf);
                    Sessao.encerrarSessao();
                    JOptionPane.showMessageDialog(tela, "Conta excluída com sucesso!");

                    tela.dispose();
                    new Login();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(tela, "Erro: " + ex);
                }
            }
        });

        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
    }
}