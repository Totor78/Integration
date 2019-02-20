package view;
import controller.FacesComparisonController;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import static org.bytedeco.javacpp.opencv_core.cvFlip;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;

public class Camera implements MouseListener {

    private BufferedImage image;
    private boolean run;
    private JFrame window;
    private Panel camContainer;
    private JPanel container;
    private OpenCVFrameGrabber grabber;

    OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
    Java2DFrameConverter paintConverter = new Java2DFrameConverter();
    opencv_core.IplImage img;

    public Camera(JFrame window) {
        this.window = window;
        this.run = true;
        JButton button = new JButton("S'identifier");
        button.setFont(new Font("Roboto",1,25));
        button.setForeground(new Color(55,158,193));
        button.addMouseListener(this);
        this.container = new JPanel();
        this.camContainer = new Panel();
        this.grabber = new OpenCVFrameGrabber(0);
        container.add(button);
        window.add(container, BorderLayout.EAST);
        window.add(camContainer, BorderLayout.CENTER);
        window.revalidate();
    }

    public void run() {
        if(this.run == false) {
            this.run = true;
            window.add(container, BorderLayout.EAST);
            window.add(camContainer, BorderLayout.CENTER);

            window.revalidate();
        }

        try {
            grabber.start();
            while (true) {
                Frame frame = grabber.grab();

                img = converter.convert(frame);

                //the grabbed frame will be flipped, re-flip to make it right
                cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise

                //save
                this.image = paintConverter.getBufferedImage(converter.convert(img));
                camContainer.setImg(this.image);
                camContainer.repaint();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouse clicked");
        OpenCVFrameConverter.ToIplImage iplConverter = new OpenCVFrameConverter.ToIplImage();
        Java2DFrameConverter java2dConverter = new Java2DFrameConverter();
        opencv_core.IplImage iplImage = iplConverter.convert(java2dConverter.convert(this.image));
        cvSaveImage("./Images/capture.jpg", iplImage);
        try {
            int agent_id = new FacesComparisonController().run();
            if (agent_id != 0) {
                this.run = false;
                window.remove(camContainer);
                window.remove(container);
                window.repaint();
                new interface_emprunt(this.window, agent_id, this);
            } else {
                        JOptionPane.showMessageDialog(window,
                        "Agent inconnu, réessayez!",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
