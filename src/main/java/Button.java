import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton implements MouseListener {
    private String name;
    public Button(String str){
        super(str);
        this.name = str;
        this.addMouseListener(this);
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setColor(Color.white);
        g2d.drawString(this.name, this.getWidth() / 2 - (this.getWidth()/ 2 /4), (this.getHeight() / 2) + 5);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouse clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mouse pressed");

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouse released");

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("mouse entered");

    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("mouse exited");

    }
}
