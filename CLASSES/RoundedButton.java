package CLASSES;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedButton extends JButton {

    private boolean hover = false; // flag para hover

    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(Color.WHITE);
        setFont(getFont().deriveFont(Font.BOLD, 14f));
        setPreferredSize(new Dimension(160, 42));

        // Listener para hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // cores base
        Color start = Color.CYAN;
        Color end = Color.PINK;

        // clarear no hover
        if (hover) {
            start = lighten(start, 0.2f);
            end = lighten(end, 0.2f);
        }

        GradientPaint gp = new GradientPaint(0, 0, start, getWidth(), getHeight(), end);
        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        // borda suave
        g2.setColor(new Color(255, 255, 255, 80));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

        // texto centralizado
        g2.setColor(getForeground());
        FontMetrics fm = g2.getFontMetrics();
        int textX = (getWidth() - fm.stringWidth(getText())) / 2;
        int textY = (getHeight() + fm.getAscent()) / 2 - 2;
        g2.drawString(getText(), textX, textY);

        g2.dispose();
    }

    /**
     * Clareia uma cor pela fração dada.
     */
    private Color lighten(Color color, float fraction) {
        int r = (int) Math.min(255, color.getRed() + 255 * fraction);
        int g = (int) Math.min(255, color.getGreen() + 255 * fraction);
        int b = (int) Math.min(255, color.getBlue() + 255 * fraction);
        return new Color(r, g, b, color.getAlpha());
    }
}
