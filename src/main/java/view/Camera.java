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
    private boolean run = true;
    private JFrame window;

    public Camera(JFrame window) {
        this.window = window;
        JButton button = new JButton("S'identifier");
        button.addMouseListener(this);
        JPanel container = new JPanel();
        Panel camContainer = new Panel();
        container.add(button);
        window.add(container, BorderLayout.EAST);
        window.add(camContainer, BorderLayout.CENTER);
        window.revalidate();

        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);

        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        Java2DFrameConverter paintConverter = new Java2DFrameConverter();
        opencv_core.IplImage img;
        try {
            grabber.start();
            while (this.run) {
                Frame frame = grabber.grab();

                img = converter.convert(frame);

                //the grabbed frame will be flipped, re-flip to make it right
                cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise

                //save
                this.image = paintConverter.getBufferedImage(converter.convert(img));
                camContainer.setImg(this.image);
                camContainer.repaint();
                //window.showImage(converter.convert(img));
            }

            grabber.stop();
            camContainer.setImg(null);
            window.remove(camContainer);
            window.remove(container);
            window.repaint();

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
                new interface_emprunt(this.window, agent_id);
            } else {
                        JOptionPane.showMessageDialog(window,
                        "Agent inconnu, réessayez!",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
            this.run = false;
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
