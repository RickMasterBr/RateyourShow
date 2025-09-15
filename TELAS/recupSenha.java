package TELAS;

import java.awt.*;
import javax.swing.*;

public class recupSenha {
    private JFrame tela;
    private JTextField txtemail;
    private JLabel lblemail, lbltitulo, lblimagem;
    private JButton btnenviar;
    private ImageIcon iconLogo;

    public recupSenha(){
        tela = new JFrame("Recuperação de senha");
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

        // Logo
        iconLogo = new ImageIcon("TELAS/img/logo.png");
        lblimagem = new JLabel();
        lblimagem.setIcon(iconLogo);
        lblimagem.setHorizontalTextPosition(SwingConstants.CENTER);
        lblimagem.setVerticalTextPosition(SwingConstants.BOTTOM);
        gbc.gridx = 0;  gbc.gridy = linha;
        tela.add(lblimagem, gbc);
        linha++;

        // Titulo
        lbltitulo = new JLabel("RECUPERAÇÃO DE SENHA");
        lbltitulo.setFont(new Font("Arial", Font.BOLD, 30));
        lbltitulo.setForeground(Color.WHITE);
        
    }
}
