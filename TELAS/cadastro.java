package TELAS;

import java.awt.*;
import java.net.URL;

import javax.swing.*;

import CLASSES.BackgroundPanel;
import CLASSES.RoundedButton;
import CLASSES.RoundedComboBox;
import CLASSES.RoundedPasswordField;
import CLASSES.RoundedTextField;

public class cadastro {

    private JFrame tela;
    private JTextField txtcpf, txtnome, txtemail;
    private JPasswordField jpsenha;
    private JComboBox<String> genero;
    private JLabel lblimagem, lblmsn, lblmsn2, lblcpf, lblnome, lblemail, lblsenha, lblgenero, lblNulo;
    private JButton btnconfirmar;
    private ImageIcon iconLogo;

    public cadastro() {
        tela = new JFrame("Cadastro de Usuários");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(600, 550); // Aumentei um pouco a altura para caber tudo confortavelmente
        tela.setResizable(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 5, 2, 5); // Diminuí o espaçamento vertical
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Faz os componentes preencherem o espaço

        // Fundo
        URL urlFundo = getClass().getResource("/TELAS/img/background.png");
        Image imagemFundo = new ImageIcon(urlFundo).getImage();
        JPanel painelPrincipal = new BackgroundPanel(imagemFundo);
        // CORREÇÃO: O layout manager deve ser aplicado ao painel, não à tela.
        painelPrincipal.setLayout(new GridBagLayout());

        int linha = 0;

        // Título
        // CORREÇÃO: Carregando a imagem de forma mais segura com getResource()
        URL urlLogo = getClass().getResource("/TELAS/img/logo.png");
        if (urlLogo != null) {
            iconLogo = new ImageIcon(urlLogo);
        }
        lblimagem = new JLabel();
        lblimagem.setIcon(iconLogo);
        lblimagem.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza a logo
        gbc.gridwidth = 2; // Ocupa a largura de 2 colunas
        gbc.gridx = 0;
        gbc.gridy = linha;
        painelPrincipal.add(lblimagem, gbc);
        linha++;

        // já cadastrado
        lblmsn = new JLabel("Já está registrado?");
        lblmsn.setForeground(Color.WHITE);
        lblmsn.setFont(new Font("Montserrat", Font.PLAIN, 12));

        lblmsn2 = new JLabel("Faça o login");
        lblmsn2.setForeground(Color.YELLOW);
        lblmsn2.setFont(new Font("Montserrat", Font.BOLD, 12));
        lblmsn2.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Muda o cursor para indicar que é clicável
        lblmsn2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                tela.dispose(); // Fecha a tela de cadastro
                new Login(); // Abre a tela de login
            }
        });

        // CORREÇÃO: Juntando os dois JLabels em um painel para não sobreporem
        JPanel painelLoginLink = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        painelLoginLink.setOpaque(false);
        painelLoginLink.add(lblmsn);
        painelLoginLink.add(lblmsn2);
        gbc.gridx = 0;
        gbc.gridy = linha;
        painelPrincipal.add(painelLoginLink, gbc);
        linha++;
        gbc.gridwidth = 1; // Reseta para 1 coluna

        // CPF
        lblcpf = new JLabel("CPF");
        lblcpf.setForeground(Color.WHITE);
        // FONTE APLICADA
        lblcpf.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = linha;
        painelPrincipal.add(lblcpf, gbc);
        linha++;
        txtcpf = new RoundedTextField(22);
        gbc.gridx = 0;
        gbc.gridy = linha;
        painelPrincipal.add(txtcpf, gbc);
        linha++;

        // Email
        lblemail = new JLabel("Email");
        lblemail.setForeground(Color.WHITE);
        // FONTE APLICADA
        lblemail.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = linha;
        painelPrincipal.add(lblemail, gbc);
        linha++;
        txtemail = new RoundedTextField(22);
        gbc.gridx = 0;
        gbc.gridy = linha;
        painelPrincipal.add(txtemail, gbc);
        linha++;

        // Nome
        lblnome = new JLabel("Nome");
        lblnome.setForeground(Color.WHITE);
        // FONTE APLICADA
        lblnome.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = linha;
        painelPrincipal.add(lblnome, gbc);
        linha++;
        txtnome = new RoundedTextField(22);
        gbc.gridx = 0;
        gbc.gridy = linha;
        painelPrincipal.add(txtnome, gbc);
        linha++;

        // Senha
        lblsenha = new JLabel("Senha");
        lblsenha.setForeground(Color.WHITE);
        // FONTE APLICADA
        lblsenha.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = linha;
        painelPrincipal.add(lblsenha, gbc);
        linha++;
        jpsenha = new RoundedPasswordField(22);
        gbc.gridx = 0;
        gbc.gridy = linha;
        painelPrincipal.add(jpsenha, gbc);
        linha++;

        // Gênero
        lblgenero = new JLabel("Gênero");
        lblgenero.setForeground(Color.WHITE);
        // FONTE APLICADA
        lblgenero.setFont(new Font("Montserrat", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = linha;
        painelPrincipal.add(lblgenero, gbc);
        linha++;
        genero = new RoundedComboBox<>(new String[] { " -- Selecione o Gênero -- ", "Masculino", "Feminino", "Outro" });
        gbc.gridx = 0;
        gbc.gridy = linha;
        painelPrincipal.add(genero, gbc);
        linha++;

        lblNulo = new JLabel(" "); // Usado como espaçador
        gbc.gridx = 0;
        gbc.gridy = linha;
        painelPrincipal.add(lblNulo, gbc);
        linha++;

        // Botão Confirmar
        btnconfirmar = new RoundedButton("Cadastre-se");
        btnconfirmar.setBackground(Color.WHITE);
        btnconfirmar.setForeground(Color.BLACK);
        // FONTE APLICADA
        btnconfirmar.setFont(new Font("Montserrat", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = linha;
        gbc.anchor = GridBagConstraints.CENTER;
        painelPrincipal.add(btnconfirmar, gbc);

        btnconfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Muda o cursor para indicar que é clicável
        
        btnconfirmar.addActionListener(e -> {
            String cpf = txtcpf.getText().trim();
            String nome = txtnome.getText().trim();
            String email = txtemail.getText().trim();
            String senha = new String(jpsenha.getPassword()).trim();
            String generoSelecionado = (String) genero.getSelectedItem();

            if (cpf.isEmpty() || nome.isEmpty() || email.isEmpty() || senha.isEmpty()
                    || generoSelecionado.startsWith(" --")) {
                JOptionPane.showMessageDialog(tela, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validação simples de CPF (apenas tamanho, depois você pode integrar com regex
            // ou lib própria)
            if (cpf.length() != 11) {
                JOptionPane.showMessageDialog(tela, "CPF inválido! Deve conter 11 dígitos.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validação simples de email
            if (!email.contains("@") || !email.contains(".")) {
                JOptionPane.showMessageDialog(tela, "Email inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Se passou em tudo → aqui entraria o INSERT no banco
            JOptionPane.showMessageDialog(tela, "Cadastro realizado com sucesso!");

            tela.dispose();
            new Login();
        });

        tela.setContentPane(painelPrincipal);
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
    }
}
