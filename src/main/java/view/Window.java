package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame implements ActionListener{
    private JPanel pan = new JPanel();
    private JButton bouton = new JButton("S'identifier");
    private JPanel container = new JPanel();
    private int compteur = 0;

    public Window(){
        this.setTitle("Java t'identifier");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());
        container.add(pan, BorderLayout.CENTER);

        bouton.addActionListener(this);

        JPanel topRight = new JPanel();
        topRight.add(bouton);
        container.add(topRight, BorderLayout.EAST);

        this.setContentPane(container);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}