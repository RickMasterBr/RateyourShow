package CLASSES;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;

public class RoundedComboBox<E> extends JComboBox<E> {
    private int arcWidth = 20;
    private int arcHeight = 20;

    public RoundedComboBox(E[] items) {
        super(items);
        setOpaque(false);
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        setUI(new RoundedComboBoxUI());

        setPreferredSize(new Dimension(200, 35));

        // Renderer para o valor exibido
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus) {
                JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                lbl.setOpaque(false);
                lbl.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
                return lbl;
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

        // borda
        g2.setColor(Color.GRAY);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);

        g2.dispose();
        super.paintComponent(g);
    }

    private class RoundedComboBoxUI extends BasicComboBoxUI {
        @Override
        protected ComboPopup createPopup() {
            return new BasicComboPopup(comboBox) {
                @Override
                protected JScrollPane createScroller() {
                    JScrollPane scroll = new JScrollPane(list,
                            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    scroll.setBorder(BorderFactory.createEmptyBorder());
                    scroll.setOpaque(false);
                    scroll.getViewport().setOpaque(false);
                    return scroll;
                }

                @Override
                protected void configurePopup() {
                    super.configurePopup();
                    setOpaque(false);
                    setBackground(new Color(0, 0, 0, 0));
                    setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                    list.setOpaque(false);
                    list.setCellRenderer(new DefaultListCellRenderer() {
                        @Override
                        public Component getListCellRendererComponent(JList<?> list, Object value,
                                                                      int index, boolean isSelected, boolean cellHasFocus) {
                            JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                            lbl.setOpaque(false);
                            lbl.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
                            if (isSelected) {
                                lbl.setBackground(new Color(200, 200, 255, 120));
                                lbl.setOpaque(true);
                            }
                            return lbl;
                        }
                    });
                }

                @Override
                public void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    g2.setColor(comboBox.getBackground());
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

                    g2.setColor(Color.GRAY);
                    g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);

                    g2.dispose();
                    super.paintComponent(g);
                }
            };
        }
    }

    public void setArc(int w, int h) {
        this.arcWidth = w;
        this.arcHeight = h;
        repaint();
    }
}