package view;

import com.amazonaws.event.DeliveryMode;
import com.google.api.client.json.Json;
import model.Borrow;
import model.Equipment;
import model.dal.AgentDAO;
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
    public interface_emprunt(int agent_id) throws SQLException{

        f = new JFrame("JavaIdentifier");
        setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int hauteurEcran = d.height;
        int largeurEcran = d.width;
        Image img = tk.getImage("C:\\Users\\Remi-\\IdeaProjects\\API\\Images\\logo.jpg");
        setIconImage(img);

        AgentDAO Equipements = new AgentDAO();
        List<Equipment> equipments = Equipements.getEquipmentsFromAgent(agent_id);
        Integer nb = equipments.size();

        this.checkBoxes = new JCheckBox[nb];
        JPanel container = new JPanel();
        JPanel p = new JPanel();
        JPanel b = new JPanel();
        for(int i=0;i<nb;i++)
        {
            String nom = equipments.get(i).getName();
            boolean selected = equipments.get(i).isBorrowed();
            JCheckBox checkBox = new JCheckBox((String)equipments.get(i).getName(), selected);
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
            Integer Id = equipments.get(i).getId();
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
                       Emprunt = new Borrow(1, new EquipmentDAO().GetEquipmentId(this.checkBoxes[j].getText()), dt.toString(), null);
                   } catch (SQLException e1) {
                       e1.printStackTrace();
                   }

                   int finalJ = j;
                   Borrow finalEmprunt = Emprunt;
                   equipments.forEach(equipment -> {
                       if (this.checkBoxes[finalJ].getText() == equipment.getName() && !equipment.isBorrowed()) {
                           try {
                               BorrowDao.createBorrow(finalEmprunt);
                           } catch (SQLException e1) {
                               e1.printStackTrace();
                           }
                       }
                   });
               } else {
                   int finalJ = j;
                   equipments.forEach(equipment -> {
                       if(this.checkBoxes[finalJ].getText() == equipment.getName() && equipment.isBorrowed()) {
                           try {
                               BorrowDao.setBorrowReturnDate(new EquipmentDAO().GetEquipmentId(this.checkBoxes[finalJ].getText()), agent_id);
                           } catch (SQLException e1) {
                               e1.printStackTrace();
                           }
                       }
                   });
               }
           }
       });


   }


    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
