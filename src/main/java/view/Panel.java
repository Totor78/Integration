package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Panel extends JPanel {

    private BufferedImage img;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 50, 50, this.getWidth(), this.getHeight() - 100, this);
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }
}
