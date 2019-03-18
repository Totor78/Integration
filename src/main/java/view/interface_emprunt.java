package view;

import model.Agent;
import model.Borrow;
import model.Equipment;
import model.dal.AgentDAO;
import model.dal.BorrowDAO;
import model.dal.EquipmentDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class interface_emprunt extends JFrame implements ItemListener {

    private JCheckBox[] checkBoxes;
    public interface_emprunt(JFrame window, int agent_id,  Camera cam) throws SQLException{



        this.setIconImage(new ImageIcon("./Images/logo.jpg").getImage());
        AgentDAO Agent = new AgentDAO();
        List<model.Agent> agents = Agent.getAgents();
        AtomicReference<model.Agent> agent = new AtomicReference<>();
        agents.forEach(ag -> {
            if (ag.getId() == agent_id) {
                agent.set(ag);
            }
        });

        String Portrait = "./Images/" + agent.get().getImage() + ".jpg";

        AgentDAO Equipements = new AgentDAO();
        List<Equipment> equipments = Equipements.getEquipmentsFromAgent(agent_id);
        System.out.println("Agent id = " + agent_id);
        Integer nb = equipments.size();

        this.checkBoxes = new JCheckBox[nb];
        JPanel container = new JPanel();
        JPanel p = new JPanel(new GridLayout(2,1));
        JPanel b = new JPanel();
        JPanel Titre = new JPanel();
        JLabel Utilisateur = new JLabel("Bonjour " + Agent.GetAgentName(agent_id));
        for(int i=0;i<nb;i++)
        {
            String nom = equipments.get(i).getName();
            System.out.println(equipments.get(i).getName() + " is borrowed " + equipments.get(i).isBorrowed());
            boolean selected = equipments.get(i).isBorrowed();
            JCheckBox checkBox = new JCheckBox((String)equipments.get(i).getName(), selected);
            checkBox.setFont(new Font("Roboto",1,12));
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
            checkBox.setText(checkBox.getText() + " - (" + equipments.get(i).getQuantity() + ")");
            if (equipments.get(i).getQuantity() == 0 && !equipments.get(i).isBorrowed()) {
                checkBox.setEnabled(false);
            }
            Integer Id = equipments.get(i).getId();
        }

        Panel Image1 = new Panel();

        ImageIcon imageIcon = new ImageIcon(Portrait);
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(300, 200,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        JLabel picLabel = new JLabel(imageIcon);
        Image1.add(picLabel);
        JButton b1 = new JButton("Valider");
        JButton b2 = new JButton("Se déconnecter");
        b1.setBackground(new Color(55,158,193));
        b1.setFont(new Font("Roboto",1,25));
        b2.setFont(new Font("Roboto",1,25));
        b2.setBackground(new Color(55,158,193));
        container.setLayout(new BoxLayout(container,BoxLayout.PAGE_AXIS));
        b.add(b1);
        b.add(b2);
        Utilisateur.setFont(new Font("Roboto",1,25));
        Utilisateur.setForeground(new Color(55,158,193));
        Titre.add(Utilisateur);
        container.add(Titre);
        container.add(Image1);
        container.add(p);
        container.add(b);
        window.add(container);
        window.revalidate();
        window.repaint();

       b1.addActionListener(e -> {
           Date dt = new Date();
           java.sql.Date CurrentDate = new java.sql.Date(dt.getTime());
           BorrowDAO BorrowDao = new BorrowDAO();
           System.out.println("agent id : " + agent_id);
           for (int j = 0; j < nb; j++) {
               if (this.checkBoxes[j].isSelected() == true) {
                   Borrow Emprunt = null;
                   try {
                       Emprunt = new Borrow(agent_id, new EquipmentDAO().GetEquipmentId(this.checkBoxes[j].getText().split(" - ")[0]), dt.toString(), null);
                   } catch (SQLException e1) {
                       e1.printStackTrace();
                   }

                   int finalJ = j;
                   Borrow finalEmprunt = Emprunt;
                   equipments.forEach(equipment -> {
                       String equipmentName = equipment.getName();
                       String checkboxName = this.checkBoxes[finalJ].getText().split(" - ")[0];
                       int quantity = Integer.parseInt((this.checkBoxes[finalJ].getText().split(" - \\(")[1]).split("\\)")[0]);
                       System.out.println(quantity);
                       System.out.println(checkboxName);
                       if (checkboxName.equals(equipmentName) && !equipment.isBorrowed()) {
                           this.checkBoxes[finalJ].setText(equipmentName + " - (" + (quantity - 1) + ")");
                           equipment.setBorrowed(true);
                           try {
                               System.out.println("create borrow : " + equipmentName);
                               BorrowDao.createBorrow(finalEmprunt);
                           } catch (SQLException e1) {
                               e1.printStackTrace();
                           }
                       }
                   });
               } else {
                   int finalJ = j;
                   equipments.forEach(equipment -> {
                       String equipmentName = equipment.getName();
                       String checkboxName = this.checkBoxes[finalJ].getText().split(" - ")[0];
                       int quantity = Integer.parseInt((this.checkBoxes[finalJ].getText().split(" - \\(")[1]).split("\\)")[0]);
                       System.out.println(quantity);
                       if(checkboxName.equals(equipmentName) && equipment.isBorrowed()) {
                           this.checkBoxes[finalJ].setText(equipmentName + " - (" + (quantity + 1) + ")");
                           equipment.setBorrowed(false);
                           try {
                               BorrowDao.setBorrowReturnDate(new EquipmentDAO().GetEquipmentId(equipment.getName()), agent_id);
                           } catch (SQLException e1) {
                               e1.printStackTrace();
                           }
                       }
                   });
               }
           }

       });

       b2.addActionListener(e -> {
           window.remove(container);
           window.repaint();
           window.revalidate();
           cam.run();
       });


   }


    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
