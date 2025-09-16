package CLASSES;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RoundedPasswordField extends JPasswordField {
    private int arcWidth = 20;  // raio horizontal
    private int arcHeight = 20; // raio vertical

    // 🔹 NOVO: Variável para controlar o estado de foco
    private boolean hasFocus = false;

    public RoundedPasswordField(int columns) {
        super(columns);
        setOpaque(false);
        
        // Mantemos uma borda vazia para dar espaçamento interno ao texto
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // 🔹 ALTERADO: Listener de foco para atualizar a variável e redesenhar
        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                hasFocus = true;
                repaint(); // Pede para o componente se redesenhar
            }

            @Override
            public void focusLost(FocusEvent e) {
                hasFocus = false;
                repaint(); // Pede para o componente se redesenhar
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Desenha o fundo branco arredondado
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

        // 🔹 ALTERADO: Lógica para desenhar a borda
        if (hasFocus) {
            // Se tiver foco, desenha a borda azul e mais espessa
            g2.setColor(new Color(66, 133, 244)); // Azul Google
            g2.setStroke(new BasicStroke(2)); // Define a espessura da linha para 2 pixels
        } else {
            // Se não tiver foco, desenha a borda cinza padrão
            g2.setColor(Color.GRAY);
            g2.setStroke(new BasicStroke(1)); // Define a espessura da linha para 1 pixel
        }
        
        // Desenha o contorno arredondado
        // Subtraímos 1 da largura e altura para a borda ficar totalmente dentro do componente
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);

        g2.dispose();

        // Chama o método original para desenhar o texto (os asteriscos)
        super.paintComponent(g);
    }
}