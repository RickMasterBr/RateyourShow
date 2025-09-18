package TELAS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import BD.Sessao;
import BD.Usuario;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import CLASSES.BackgroundPanel;
import CLASSES.RoundedPanel;
import CLASSES.RoundedTextFieldPlaceholder;
import CLASSES.ThemeManager;

public class Home {

    private JFrame tela;
    private Usuario u = Sessao.getUsuarioLogado();

    public Home() {
        tela = new JFrame("RateyourShow - Home");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(1200, 800); // Aumentei um pouco para o novo layout
        tela.setResizable(false);
        tela.setLayout(new GridLayout(1, 2)); // << LAYOUT DIVIDIDO

        // =====================================================================
        // PAINEL DA ESQUERDA (VISUAL)
        // =====================================================================
        URL urlFundo = getClass().getResource("/TELAS/img/background3.jpg");
        Image imagemFundo = new ImageIcon(urlFundo).getImage();
        BackgroundPanel painelEsquerda = new BackgroundPanel(imagemFundo);
        painelEsquerda.setLayout(new GridBagLayout());
        
        JLabel lblWelcome = new JLabel("RateYourShow");
        lblWelcome.setFont(new Font("Montserrat", Font.BOLD, 48));
        lblWelcome.setForeground(Color.WHITE);
        painelEsquerda.add(lblWelcome); // Adiciona o texto de boas-vindas

        // =====================================================================
        // PAINEL DA DIREITA (CONTEÚDO PRINCIPAL)
        // =====================================================================
        JPanel painelDireita = new JPanel(new BorderLayout(20, 15));
        painelDireita.setBorder(new EmptyBorder(20, 25, 20, 25));

        painelDireita.add(createHeaderPanel(), BorderLayout.NORTH);
        painelDireita.add(createContentPanel(), BorderLayout.CENTER);

        // --- MONTAGEM FINAL ---
        tela.add(painelEsquerda);
        tela.add(painelDireita);

        ThemeManager.applyTheme(tela);

        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        JLabel lblBemVindo = new JLabel("Olá, " + u.getNome() + "!");
        lblBemVindo.setFont(new Font("Montserrat", Font.BOLD, 22));

        JPanel userActionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        userActionsPanel.setOpaque(false);



        JLabel lblIconPerfil = new JLabel("Perfil");
        try {
            URL urlUsuario = getClass().getResource("/TELAS/img/usuario.png");
            if (urlUsuario != null) {
                ImageIcon originalIcon = new ImageIcon(urlUsuario);
                Image scaledImage = originalIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
                lblIconPerfil.setIcon(new ImageIcon(scaledImage));
            }
        } catch(Exception e) {}
        
        lblIconPerfil.setFont(new Font("Montserrat", Font.BOLD, 14));
        lblIconPerfil.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblIconPerfil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tela.dispose();
                new Perfil();
            }
        });

        JLabel lblSair = new JLabel("Sair");
        lblSair.setForeground(Color.YELLOW);
        lblSair.setFont(new Font("Montserrat", Font.BOLD, 14));
        lblSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblSair.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sessao.encerrarSessao();
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

    private JScrollPane createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.setOpaque(false);
        searchPanel.setBorder(new EmptyBorder(20, 0, 40, 0));
        RoundedTextFieldPlaceholder txtPesquisa = new RoundedTextFieldPlaceholder(40, "Procure por um filme, série ou show...");
        txtPesquisa.setPreferredSize(new Dimension(400, 45));
        txtPesquisa.setFont(new Font("Montserrat", Font.PLAIN, 16));
        searchPanel.add(txtPesquisa);

        // Adicionando os painéis de seção
        contentPanel.add(searchPanel);
        contentPanel.add(createSectionPanel("Shows em Destaque", new String[]{"Show A", "Show B", "Show C", "Show D"}));
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(createSectionPanel("Suas Avaliações", new String[]{"Série X", "Filme Y"}));
        
        // Adiciona um painel de rolagem
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        return scrollPane;
    }

    private JPanel createSectionPanel(String title, String[] items) {
        JPanel section = new JPanel();
        section.setOpaque(false);
        section.setLayout(new BorderLayout(0, 10));
        section.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Montserrat", Font.BOLD, 18));
        lblTitle.setBorder(new EmptyBorder(0, 5, 10, 0));
        
        JPanel content = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        content.setOpaque(false);

        for (String itemTitle : items) {
            content.add(createShowCard(itemTitle));
        }
        
        section.add(lblTitle, BorderLayout.NORTH);
        section.add(content, BorderLayout.CENTER);

        return section;
    }

    private JPanel createShowCard(String showTitle) {
        JPanel card = new JPanel();
        card.setOpaque(true); // O card em si precisa ter uma cor
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));
        card.setPreferredSize(new Dimension(180, 250));
        card.setMaximumSize(new Dimension(180, 250));
        
        JLabel lblPoster = new JLabel("Poster", SwingConstants.CENTER);
        lblPoster.setOpaque(true);
        lblPoster.setBackground(new Color(70, 70, 70));
        lblPoster.setForeground(Color.LIGHT_GRAY);
        lblPoster.setFont(new Font("Montserrat", Font.ITALIC, 16));
        
        JLabel lblTitle = new JLabel(showTitle, SwingConstants.CENTER);
        lblTitle.setFont(new Font("Montserrat", Font.BOLD, 14));
        lblTitle.setBorder(new EmptyBorder(10, 5, 10, 5));

        card.add(lblPoster, BorderLayout.CENTER);
        card.add(lblTitle, BorderLayout.SOUTH);
        
        return card;
    }
}