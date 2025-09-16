package TELAS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import CLASSES.BackgroundPanel;
import CLASSES.RoundedPanel;
import CLASSES.RoundedTextFieldPlaceholder;

public class Home {

    private JFrame tela;
    private String nomeUsuarioLogado = "Usuário"; // Isso viria do login

    // --- CONSTRUTOR ---
    // Em uma aplicação real, você passaria o nome do usuário aqui
    // Ex: public Home(String nomeUsuario) { ... }
    public Home() {
        tela = new JFrame("RateyourShow - Home");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(1000, 750); // Uma tela maior para a home
        tela.setResizable(false);

        // 1. Painel de Fundo
        URL urlFundo = getClass().getResource("/TELAS/img/background.png");
        Image imagemFundo = new ImageIcon(urlFundo).getImage();
        BackgroundPanel painelDeFundo = new BackgroundPanel(imagemFundo);
        painelDeFundo.setLayout(new GridBagLayout());

        // 2. Painel "Card" Principal
        RoundedPanel mainPanel = new RoundedPanel(new BorderLayout(20, 20), 20, new Color(50, 50, 50, 210));
        mainPanel.setBorder(new EmptyBorder(20, 25, 20, 25)); // Padding interno
        
        // --- Montagem dos Componentes ---
        
        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        mainPanel.add(createContentPanel(), BorderLayout.CENTER);
        
        // Adiciona o card ao fundo para centralização
        painelDeFundo.add(mainPanel);
        tela.setContentPane(painelDeFundo);
        
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
    }

    // --- PAINEL DO CABEÇALHO ---
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        // Mensagem de Boas-Vindas
        JLabel lblBemVindo = new JLabel("Olá, " + nomeUsuarioLogado + "!");
        lblBemVindo.setForeground(Color.WHITE);
        lblBemVindo.setFont(new Font("Montserrat", Font.BOLD, 22));

        // Painel de Ações do Usuário (Perfil e Sair)
        JPanel userActionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        userActionsPanel.setOpaque(false);
        
        // Ícone/Botão de Perfil
        URL urlUsuario = getClass().getResource("/TELAS/img/usuario.png");
        ImageIcon originalIcon = new ImageIcon(urlUsuario);
        Image scaledImage = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        JLabel lblIconPerfil = new JLabel(new ImageIcon(scaledImage));
        lblIconPerfil.setText("Perfil");
        lblIconPerfil.setForeground(Color.WHITE);
        lblIconPerfil.setFont(new Font("Montserrat", Font.BOLD, 14));
        lblIconPerfil.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblIconPerfil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // new Perfil(usuario); // Abre a tela de perfil
                 JOptionPane.showMessageDialog(tela, "Abrindo tela de Perfil...");
            }
        });

        // Botão Sair
        JLabel lblSair = new JLabel("Sair");
        lblSair.setForeground(Color.YELLOW);
        lblSair.setFont(new Font("Montserrat", Font.BOLD, 14));
        lblSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblSair.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tela.dispose();
                new Login();
            }
        });

        userActionsPanel.add(lblIconPerfil);
        userActionsPanel.add(lblSair);
        
        headerPanel.add(lblBemVindo, BorderLayout.WEST);
        headerPanel.add(userActionsPanel, BorderLayout.EAST);
        
        return headerPanel;
    }

    // --- PAINEL DE CONTEÚDO PRINCIPAL ---
    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); // Layout vertical

        // Barra de Pesquisa
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.setOpaque(false);
        searchPanel.setBorder(new EmptyBorder(20, 0, 40, 0));
        RoundedTextFieldPlaceholder txtPesquisa = new RoundedTextFieldPlaceholder(40, "Procure por um filme, série ou show...");
        txtPesquisa.setPreferredSize(new Dimension(400, 45));
        txtPesquisa.setFont(new Font("Montserrat", Font.PLAIN, 16));
        searchPanel.add(txtPesquisa);

        // Seção de Destaques
        JPanel destaquesPanel = createSectionPanel("Shows em Destaque");
        // Adicionando placeholders de conteúdo
        destaquesPanel.add(createShowCard("Show A"));
        destaquesPanel.add(Box.createHorizontalStrut(20)); // Espaçador
        destaquesPanel.add(createShowCard("Show B"));
        destaquesPanel.add(Box.createHorizontalStrut(20));
        destaquesPanel.add(createShowCard("Show C"));
        destaquesPanel.add(Box.createHorizontalStrut(20));
        destaquesPanel.add(createShowCard("Show D"));

        // Seção de Avaliações
        JPanel avaliacoesPanel = createSectionPanel("Suas Avaliações Recentes");
        avaliacoesPanel.add(createShowCard("Série X"));
        avaliacoesPanel.add(Box.createHorizontalStrut(20));
        avaliacoesPanel.add(createShowCard("Filme Y"));

        contentPanel.add(searchPanel);
        contentPanel.add(destaquesPanel);
        contentPanel.add(Box.createVerticalStrut(20)); // Espaçador vertical
        contentPanel.add(avaliacoesPanel);

        return contentPanel;
    }

    // --- MÉTODO AUXILIAR PARA CRIAR SEÇÕES ---
    private JPanel createSectionPanel(String title) {
        JPanel section = new JPanel();
        section.setOpaque(false);
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Montserrat", Font.BOLD, 18));
        lblTitle.setBorder(new EmptyBorder(0, 5, 10, 0));
        
        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
        content.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        section.add(lblTitle);
        section.add(content);

        return content; // Retorna o painel de conteúdo para adicionar os cards
    }

    // --- MÉTODO AUXILIAR PARA CRIAR "CARDS" DE SHOW (PLACEHOLDER) ---
    private JPanel createShowCard(String showTitle) {
        JPanel card = new JPanel();
        card.setOpaque(false);
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));
        card.setPreferredSize(new Dimension(180, 250));
        card.setMaximumSize(new Dimension(180, 250));

        // Placeholder para a imagem do poster
        JLabel lblPoster = new JLabel("Poster", SwingConstants.CENTER);
        lblPoster.setOpaque(true);
        lblPoster.setBackground(new Color(70, 70, 70));
        lblPoster.setForeground(Color.LIGHT_GRAY);
        lblPoster.setFont(new Font("Montserrat", Font.ITALIC, 16));

        // Título do show
        JLabel lblTitle = new JLabel(showTitle, SwingConstants.CENTER);
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Montserrat", Font.BOLD, 14));
        lblTitle.setBorder(new EmptyBorder(10, 5, 10, 5));

        card.add(lblPoster, BorderLayout.CENTER);
        card.add(lblTitle, BorderLayout.SOUTH);
        
        return card;
    }
}