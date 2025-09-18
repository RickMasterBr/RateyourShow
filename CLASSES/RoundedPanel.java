package CLASSES;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private Color backgroundColor;
    private int cornerRadius = 15;

    public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
        super(layout);
        this.cornerRadius = radius;
        this.backgroundColor = bgColor;
        setOpaque(false); // Essencial para permitir o desenho customizado
    }

    // --- MÉTODO ADICIONADO PARA A CORREÇÃO ---
    /**
     * Define uma nova cor de fundo para o painel e o redesenha.
     * Este método é essencial para que o ThemeManager consiga alterar o tema dinamicamente.
     * @param bgColor A nova cor de fundo.
     */
    public void setBackgroundColor(Color bgColor) {
        this.backgroundColor = bgColor;
        repaint(); // Pede para o componente se redesenhar com a nova cor
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Desenha o painel arredondado com a cor de fundo
        if (backgroundColor != null) {
            graphics.setColor(backgroundColor);
            graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
        }
    }
}