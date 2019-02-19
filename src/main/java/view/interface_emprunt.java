package view;

import com.amazonaws.event.DeliveryMode;
import com.google.api.client.json.Json;
import model.Borrow;
import model.Equipment;
import model.dal.BorrowDAO;
import model.dal.EquipmentDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Console;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class interface_emprunt extends JFrame implements ItemListener {

    static JFrame f;
    private JCheckBox[] checkBoxes;
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
        List<Equipment> equipments = Equipements.getEquipments();
        Integer nb = equipments.size();

        this.checkBoxes = new JCheckBox[nb];
        JPanel container = new JPanel();
        JPanel p = new JPanel();
        JPanel b = new JPanel();
        for(int i=0;i<nb;i++)
        {
            String nom = Equipements.getEquipments().get(i).getName();
            JCheckBox checkBox = new JCheckBox((String)Equipements.getEquipments().get(i).getName(), false);
            p.add(checkBox);
            checkBox.setMnemonic(KeyEvent.VK_C);
            checkBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    System.out.println(checkBox.getText());
                    if(e.getStateChange() == ItemEvent.SELECTED) {
                        checkBox.setSelected(true);
                        System.out.println(checkBox.getText() + " set select " + checkBox.isSelected());
                    }
                }
            });
            this.checkBoxes[i] = checkBox;
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

       b1.addActionListener(e -> {
           Date dt = new Date();
           java.sql.Date CurrentDate = new java.sql.Date(dt.getTime());
           BorrowDAO BorrowDao = new BorrowDAO();
           for (int j = 0; j < nb; j++) {
               System.out.println(checkBoxes[j].getText() + " is selected ? " + checkBoxes[j].isSelected());

               if (this.checkBoxes[j].isSelected() == true) {
                   Borrow Emprunt = null;
                   try {
                       Emprunt = new Borrow(1, Equipements.GetEquipmentId(this.checkBoxes[j].getText()), dt.toString(), null);
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
       });


   }


    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
