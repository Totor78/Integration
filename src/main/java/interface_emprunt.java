import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class interface_emprunt extends JFrame {
    public interface_emprunt(){
        super("JavaIdentifier");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int hauteurEcran = d.height;
        int largeurEcran = d.width;
        setSize(largeurEcran/2, hauteurEcran/2);
        setLocation(largeurEcran/4, hauteurEcran/4);
        Image img = tk.getImage("C:\\Users\\Remi-\\IdeaProjects\\API\\Images\\logo.jpg");
        setIconImage(img);

        /*JButton b1 = new JButton("S'identifier");
        add(b1,BorderLayout.WEST);*/

        setLayout(new GridLayout(7, 1));
        add(new Checkbox("Mousqueton", null, true));
        add(new Checkbox("Gant d'intervention"));
        add(new Checkbox("Ceinture de sécurité tactique "));
        add(new Checkbox("Détecteur de métaux "));
        add(new Checkbox("Brassard de sécurité"));
        add(new Checkbox("Lampe torche"));
        add(new Checkbox("Gilet par balle"));
        setVisible(true);
      /*  b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {


            }
        });*/
    }


}
