package CLASSES;

import javax.swing.*;
import java.awt.*;

public class ThemeManager {

    public static boolean isDarkMode = false;

    // --- PALETAS DE CORES ---
    public static final Color LIGHT_BACKGROUND_PANEL = new Color(240, 240, 240);
    public static final Color LIGHT_BACKGROUND_COMPONENT = Color.WHITE;
    public static final Color LIGHT_FOREGROUND = new Color(40, 40, 40); // Texto escuro

    public static final Color DARK_BACKGROUND_PANEL = new Color(45, 45, 45);
    public static final Color DARK_BACKGROUND_COMPONENT = new Color(60, 60, 60);
    public static final Color DARK_FOREGROUND = new Color(220, 220, 220); // Texto claro

    /**
     * Alterna o tema e reaplica em todas as janelas abertas.
     */
    public static void toggleDarkMode() {
        isDarkMode = !isDarkMode;
        for (Window window : Window.getWindows()) {
            applyTheme(window);
        }
    }

    /**
     * Método público inicial para aplicar o tema.
     */
    public static void applyTheme(Container container) {
        updateComponentTree(container);
    }

    /**
     * Método recursivo que percorre todos os componentes e aplica as cores do tema.
     */
    private static void updateComponentTree(Container container) {
        Color background = isDarkMode ? DARK_BACKGROUND_PANEL : LIGHT_BACKGROUND_PANEL;
        Color foreground = isDarkMode ? DARK_FOREGROUND : LIGHT_FOREGROUND;
        Color componentBg = isDarkMode ? DARK_BACKGROUND_COMPONENT : LIGHT_BACKGROUND_COMPONENT;

        container.setBackground(background);
        container.setForeground(foreground);

        for (Component component : container.getComponents()) {

            if (component instanceof RoundedPanel) {
                // Painéis customizados seguem o tema
                ((RoundedPanel) component).setBackgroundColor(isDarkMode ? new Color(60, 60, 60, 220) : new Color(255, 255, 255));
            
            } else if (component instanceof JTextField || component instanceof JPasswordField) {
                component.setBackground(componentBg);
                component.setForeground(foreground);
                if (component instanceof JTextField) {
                    ((JTextField) component).setCaretColor(foreground);
                }
            
            } else if (component instanceof RoundedComboBox) {
                // CORREÇÃO: Aplica cores ao ComboBox
                component.setBackground(componentBg);
                component.setForeground(foreground);
            
            } else if (component instanceof RoundedButton) {
                // CORREÇÃO: Força a cor do texto do botão para ser sempre preta
                component.setForeground(Color.BLACK);
            
            } else if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                
                // CORREÇÃO: Força a cor branca para textos no painel da esquerda (sobre a imagem)
                // Usamos o 'name' do painel pai como identificador.
                if (component.getParent() != null && "conteudoEsquerda".equals(component.getParent().getName())) {
                    label.setForeground(Color.WHITE);
                } else {
                    label.setForeground(foreground);
                }
                
                // Mantém o feedback de erro sempre vermelho
                String text = label.getText();
                if (text != null && (text.toLowerCase().contains("inválido") || text.toLowerCase().contains("erro"))) {
                     label.setForeground(Color.RED);
                }

            } else { // Para outros componentes como JPanels normais
                component.setBackground(background);
                component.setForeground(foreground);
            }
            
            if (component instanceof Container) {
                updateComponentTree((Container) component);
            }
        }
        container.revalidate();
        container.repaint();
    }
}