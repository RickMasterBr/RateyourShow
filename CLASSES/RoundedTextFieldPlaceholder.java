package CLASSES;

import javax.swing.*;
import java.awt.*;

/**
 * Documentação: RoundedTextFieldPlaceholder
 * * Este componente combina as funcionalidades de um JTextField com cantos
 * arredondados e um placeholder "fantasma".
 * É um componente completo e reutilizável para formulários modernos.
 */
public class RoundedTextFieldPlaceholder extends JTextField {

    // Variáveis do canto arredondado
    private int arcWidth = 20;
    private int arcHeight = 20;

    // Variável do placeholder
    private String placeholder;

    /**
     * Construtor que recebe o número de colunas e o texto do placeholder.
     * @param columns     O número de colunas para calcular a largura preferida.
     * @param placeholder O texto a ser exibido como dica.
     */
    public RoundedTextFieldPlaceholder(int columns, String placeholder) {
        super(columns);
        this.placeholder = placeholder;

        setOpaque(false); // Essencial para que nosso desenho customizado apareça
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Padding interno
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // --- LÓGICA DO ROUNDEDTEXTFIELD ---
        // Desenha o fundo arredondado
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        
        // --- PINTURA PADRÃO DO TEXTO E CURSOR ---
        // Chama o método original para pintar o texto, cursor, etc.
        // Isso deve vir DEPOIS de pintar o fundo, mas ANTES de pintar o placeholder.
        super.paintComponent(g);

        // --- LÓGICA DO PLACEHOLDER ---
        // Se o campo estiver vazio e sem foco, desenha o placeholder
        if (getText().isEmpty() && !isFocusOwner()) {
            // Define a cor e a fonte para o placeholder
            g2.setColor(Color.GRAY);
            g2.setFont(getFont().deriveFont(Font.ITALIC));

            // Calcula a posição e desenha o texto do placeholder
            FontMetrics fontMetrics = g2.getFontMetrics();
            int y = (getHeight() - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
            g2.drawString(placeholder, getInsets().left, y);
        }

        g2.dispose();
    }
    
    @Override
    protected void paintBorder(Graphics g) {
        // Este método desenha a borda. Vamos sobrescrevê-lo para desenhar nossa borda arredondada.
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.GRAY); // Cor da borda
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
        g2.dispose();
    }

    // Getters e Setters para flexibilidade
    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }
}