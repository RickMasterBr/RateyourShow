package CLASSES;

import javax.swing.*;
import java.awt.*;

public class RoundedTextField extends JTextField {
    private int arcWidth = 20;  // raio horizontal
    private int arcHeight = 20; // raio vertical

    public RoundedTextField(int columns) {
        super(columns);
        setOpaque(false); // importante: não desenhar fundo padrão
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // padding interno
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Suavização
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fundo
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

        // Borda
        g2.setColor(Color.GRAY);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);

        g2.dispose();

        // Chama a pintura padrão do texto
        super.paintComponent(g);
    }
}

