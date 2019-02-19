package view;

import com.amazonaws.event.DeliveryMode;
import com.google.api.client.json.Json;
import model.Borrow;
import model.dal.BorrowDAO;
import model.dal.EquipmentDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Console;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class interface_emprunt extends JFrame implements ItemListener {

    static JFrame f;
    public interface_emprunt() throws SQLException{

        f = new JFrame("JavaIdentifier");
        setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int hauteurEcran = d.height;
        int largeurEcran = d.width;
        Image img = tk.getImage("C:\\Users\\Remi-\\IdeaProjects\\API\\Images\\logo.jpg");
        setIconImage(img);

        EquipmentDAO Equipements = new EquipmentDAO();
        Integer nb= Equipements.getEquipments().size();

        JCheckBox[] tableau = new JCheckBox[nb];
        JPanel container = new JPanel();
        JPanel p = new JPanel();
        JPanel b = new JPanel();
        for(int i=0;i<nb;i++)
        {
            String nom = Equipements.getEquipments().get(i).getName();
            tableau[i]= new JCheckBox((String)Equipements.getEquipments().get(i).getName(), false);
            p.add(new Checkbox(nom));
            tableau[i].setMnemonic(KeyEvent.VK_C);
            tableau[i].addItemListener(this);
            Integer Id = Equipements.getEquipments().get(i).getId();
        }


        JButton b1 = new JButton("Valider");

        f.setSize(hauteurEcran,largeurEcran );
        container.setLayout(new BoxLayout(container,BoxLayout.PAGE_AXIS));
        b.add(b1);
        container.add(p);
        container.add(b);
        f.add(container);
        f.show();

       b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                Date dt = new Date();
                java.sql.Date CurrentDate = new java.sql.Date(dt.getTime());
                BorrowDAO BorrowDao = new BorrowDAO();
                System.out.println(tableau[1].getName());
                for (int j = 0; j < nb; j++) {


                    if (tableau[j].isSelected() == true) {
                        Borrow Emprunt = null;
                        try {
                            Emprunt = new Borrow(j, 1, Equipements.GetEquipmentId(tableau[j].toString()), dt.toString(), dt.toString());
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }

                        try {
                            BorrowDao.createBorrow(Emprunt);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });


   }


    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
