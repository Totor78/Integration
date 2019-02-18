import com.google.api.client.json.Json;
import model.Borrow;
import model.dal.BorrowDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Console;
import java.sql.SQLException;
import java.util.Date;


public class interface_emprunt extends JFrame implements ItemListener {
     JCheckBox Oreillettes  ;
     JCheckBox Teasers  ;
     JCheckBox Talkies ;
     JCheckBox CoupeVents;
     JCheckBox Blousons ;
     JCheckBox Chemises  ;
     JCheckBox Bandeau ;
     JCheckBox Mousqueton ;
     JCheckBox Gant ;
     JCheckBox Ceinture;
     JCheckBox Detecteur ;
     JCheckBox Brassard ;
     JCheckBox Lampe ;
     JCheckBox Gilet ;

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

        JButton b1 = new JButton("Valider");
        add(b1,BorderLayout.WEST);

        setLayout(new GridLayout(7, 1));
        add(new Checkbox("Mousqueton", null, true));
        add(new Checkbox("Gant d'intervention"));
        add(new Checkbox("Ceinture de sécurité tactique "));
        add(new Checkbox("Détecteur de métaux "));
        add(new Checkbox("Brassard de sécurité"));
        add(new Checkbox("Lampe torche"));
        add(new Checkbox("Bandeaux Agents cynophiles " ));
        add(new Checkbox("Gilet par balle"));
        add(new Checkbox("Chemises manches courtes"));
        add(new Checkbox("Blousons"));
        add(new Checkbox("Coupe-vents"));
        add(new Checkbox("Talkies walkies"));
        add(new Checkbox("Oreillettes"));
        add(new Checkbox("Tasers"));

        Mousqueton = new JCheckBox("Mousqueton");
        Mousqueton.setMnemonic(KeyEvent.VK_C);
        Mousqueton.setSelected(true);

        Gant = new JCheckBox("Gant");
        Gant.setMnemonic(KeyEvent.VK_G);
        Gant.setSelected(true);

        Ceinture = new JCheckBox("Ceinture");
        Ceinture.setMnemonic(KeyEvent.VK_H);
        Ceinture.setSelected(true);

        Detecteur = new JCheckBox("Détecteur");
        Detecteur.setMnemonic(KeyEvent.VK_T);
        Detecteur.setSelected(true);

        Brassard = new JCheckBox("Gant");
        Brassard.setMnemonic(KeyEvent.VK_G);
        Brassard.setSelected(true);

        Lampe = new JCheckBox("Lampe");
        Lampe.setMnemonic(KeyEvent.VK_G);
        Lampe.setSelected(true);

        Bandeau = new JCheckBox("Bandeau Agents Cynophiles");
        Bandeau.setMnemonic(KeyEvent.VK_G);
        Bandeau.setSelected(true);

        Gilet = new JCheckBox("Gilet");
        Gilet.setMnemonic(KeyEvent.VK_G);
        Gilet.setSelected(true);

        Chemises = new JCheckBox("Chemises manches courtes");
        Chemises.setMnemonic(KeyEvent.VK_G);
        Chemises.setSelected(true);

        Blousons = new JCheckBox("Blousons");
        Blousons.setMnemonic(KeyEvent.VK_G);
        Blousons.setSelected(true);

        CoupeVents = new JCheckBox("Coupe-vents");
        CoupeVents.setMnemonic(KeyEvent.VK_G);
        CoupeVents.setSelected(true);

        Talkies = new JCheckBox("Talkies walkies ");
        Talkies.setMnemonic(KeyEvent.VK_G);
        Talkies.setSelected(true);

        Oreillettes = new JCheckBox("kits oreillettes");
        Oreillettes.setMnemonic(KeyEvent.VK_G);
        Oreillettes.setSelected(true);

        Teasers = new JCheckBox("Teasers");
        Teasers.setMnemonic(KeyEvent.VK_G);
        Teasers.setSelected(true);

        Mousqueton.addItemListener(this);
        Gant.addItemListener(this);
        Ceinture.addItemListener(this);
        Detecteur.addItemListener(this);
        Brassard.addItemListener(this);
        Lampe.addItemListener(this);
        Bandeau.addItemListener(this);
        Gilet.addItemListener( this);
        Chemises.addItemListener(this);
        Blousons.addItemListener(this);
        CoupeVents.addItemListener(this);
        Talkies.addItemListener(this);
        Oreillettes.addItemListener(this);
        Teasers.addItemListener(this);
        setVisible(true);
        
        
        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Date dt = new Date();
                java.sql.Date CurrentDate = new java.sql.Date(dt.getTime());

                if(Mousqueton.isSelected()==true)
                {
                    Borrow Emprunt = new Borrow(18,1,1,dt.toString(),dt.toString());
                    BorrowDAO BorrowDao = new BorrowDAO();
                    try {
                        BorrowDao.createBorrow(Emprunt);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                if (Gant.isSelected()== true)
                {
                    Borrow Emprunt = new Borrow(18,1,2,dt.toString(),dt.toString());
                    BorrowDAO BorrowDao = new BorrowDAO();
                    try {
                        BorrowDao.createBorrow(Emprunt);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                if (Ceinture.isSelected()== true)
                {
                    Borrow Emprunt = new Borrow(18,1,3,dt.toString(),dt.toString());
                    BorrowDAO BorrowDao = new BorrowDAO();
                    try {
                        BorrowDao.createBorrow(Emprunt);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                if (Detecteur.isSelected()== true)
                {
                    Borrow Emprunt = new Borrow(18,1,4,dt.toString(),dt.toString());
                    BorrowDAO BorrowDao = new BorrowDAO();
                    try {
                        BorrowDao.createBorrow(Emprunt);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                if (Brassard.isSelected()== true)
                {
                    Borrow Emprunt = new Borrow(18,1,5,dt.toString(),dt.toString());
                    BorrowDAO BorrowDao = new BorrowDAO();
                    try {
                        BorrowDao.createBorrow(Emprunt);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                if (Lampe.isSelected()== true)
                {
                    Borrow Emprunt = new Borrow(18,1,6,dt.toString(),dt.toString());
                    BorrowDAO BorrowDao = new BorrowDAO();
                    try {
                        BorrowDao.createBorrow(Emprunt);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                if (Bandeau.isSelected()== true)
                {
                    Borrow Emprunt = new Borrow(18,1,7,dt.toString(),dt.toString());
                    BorrowDAO BorrowDao = new BorrowDAO();
                    try {
                        BorrowDao.createBorrow(Emprunt);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                 if (Gilet.isSelected()== true)
                {
                    Borrow Emprunt = new Borrow(18,1,8,dt.toString(),dt.toString());
                    BorrowDAO BorrowDao = new BorrowDAO();
                    try {
                        BorrowDao.createBorrow(Emprunt);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                 if (Chemises.isSelected()== true)
                {
                    Borrow Emprunt = new Borrow(18,1,9,dt.toString(),dt.toString());
                    BorrowDAO BorrowDao = new BorrowDAO();
                    try {
                        BorrowDao.createBorrow(Emprunt);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                if (Chemises.isSelected()== true)
                {
                    Borrow Emprunt = new Borrow(18,1,10,dt.toString(),dt.toString());
                    BorrowDAO BorrowDao = new BorrowDAO();
                    try {
                        BorrowDao.createBorrow(Emprunt);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                 if (Blousons.isSelected()== true)
                {
                    Borrow Emprunt = new Borrow(18,1,11,dt.toString(),dt.toString());
                    BorrowDAO BorrowDao = new BorrowDAO();
                    try {
                        BorrowDao.createBorrow(Emprunt);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                 if (CoupeVents.isSelected()== true)
                {
                    Borrow Emprunt = new Borrow(18,1,12,dt.toString(),dt.toString());
                    BorrowDAO BorrowDao = new BorrowDAO();
                    try {
                        BorrowDao.createBorrow(Emprunt);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                 if (Talkies.isSelected()== true)
                {
                    Borrow Emprunt = new Borrow(18,1,13,dt.toString(),dt.toString());
                    BorrowDAO BorrowDao = new BorrowDAO();
                    try {
                        BorrowDao.createBorrow(Emprunt);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                 if (Oreillettes.isSelected()== true)
                {
                    Borrow Emprunt = new Borrow(18,1,14,dt.toString(),dt.toString());
                    BorrowDAO BorrowDao = new BorrowDAO();
                    try {
                        BorrowDao.createBorrow(Emprunt);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                if (Teasers.isSelected()== true)
                {
                    Borrow Emprunt = new Borrow(18,1,15,dt.toString(),dt.toString());
                    BorrowDAO BorrowDao = new BorrowDAO();
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
