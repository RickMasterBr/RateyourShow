package CLASSES;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RoundedTextFieldPlaceholder extends JTextField {
    private int arcWidth = 20;
    private int arcHeight = 20;
    private String placeholder;
    private boolean focused = false;

    public RoundedTextFieldPlaceholder(int columns, String placeholder) {
        super(columns);
        this.placeholder = placeholder;

        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        // foco controlado por flag para desenhar borda arredondada corretamente
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                focused = true;
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                focused = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // fundo arredondado
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

        // desenha texto e cursor (como JTextField faria). Como opaque=false, super não pinta fundo.
        super.paintComponent(g);

        // placeholder (apenas quando vazio e sem foco)
        if (getText().isEmpty() && !focused) {
            g2.setColor(new Color(150, 150, 150));
            g2.setFont(getFont().deriveFont(Font.ITALIC));
            FontMetrics fm = g2.getFontMetrics();
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
            int x = getInsets().left;
            g2.drawString(placeholder, x, y);
        }

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (focused) {
            g2.setColor(new Color(66, 133, 244)); // azul ao focar
            g2.setStroke(new BasicStroke(2f));
        } else {
            g2.setColor(Color.GRAY);
            g2.setStroke(new BasicStroke(1f));
        }
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
        g2.dispose();
    }

    // getters / setters opcionais
    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }
}
