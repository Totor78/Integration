import org.bytedeco.javacv.CanvasFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends CanvasFrame implements ActionListener {

    private String title;
    private int width;
    private int height;
    private JPanel container = new JPanel();
    private Button button = new Button("Bouton");


    public Window(String title){
        super(title);
        this.setTitle("Animation");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());
        container.add(button, BorderLayout.SOUTH);
        this.setContentPane(container);
        this.setVisible(true);
        button.addActionListener(this);
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        Test gs = new Test(this);
        gs.run();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("action performed");
    }
}