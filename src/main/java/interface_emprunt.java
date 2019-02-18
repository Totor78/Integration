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


    public interface_emprunt() throws SQLException{

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
        JButton b1 = new JButton("Valider");
        add(b1,BorderLayout.WEST);
        EquipmentDAO Equipements = new EquipmentDAO();
        Integer nb= Equipements.getEquipments().size();
        setLayout(new GridLayout(nb, 1));

        JCheckBox[] tableau = new JCheckBox[nb];


        for(int i=0;i<nb;i++)
        {

            String nom = Equipements.getEquipments().get(i).getName();
            tableau[i]= new JCheckBox((String)Equipements.getEquipments().get(i).getName(), false);
            add(new Checkbox(nom));
            tableau[i].setMnemonic(KeyEvent.VK_C);
            tableau[i].addItemListener(this);
            Integer Id = Equipements.getEquipments().get(i).getId();
        }
            setVisible(true);


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
