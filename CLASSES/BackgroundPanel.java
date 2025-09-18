package CLASSES;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private Image imagem;

    public BackgroundPanel(Image imagem) {
        this.imagem = imagem;
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagem != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            int imgWidth = imagem.getWidth(this);
            int imgHeight = imagem.getHeight(this);

            double panelWidth = getWidth();
            double panelHeight = getHeight();

            // proporção da imagem
            double imgAspect = (double) imgWidth / imgHeight;
            double panelAspect = panelWidth / panelHeight;

            int drawWidth, drawHeight;
            int drawX, drawY;

            if (imgAspect > panelAspect) {
                // imagem mais "larga" → ajusta pela altura
                drawHeight = (int) panelHeight;
                drawWidth = (int) (panelHeight * imgAspect);
            } else {
                // imagem mais "alta" → ajusta pela largura
                drawWidth = (int) panelWidth;
                drawHeight = (int) (panelWidth / imgAspect);
            }

            // centraliza
            drawX = (int) ((panelWidth - drawWidth) / 2);
            drawY = (int) ((panelHeight - drawHeight) / 2);

            g2.drawImage(imagem, drawX, drawY, drawWidth, drawHeight, this);
            g2.dispose();
        }
    }
}
