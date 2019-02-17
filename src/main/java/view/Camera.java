package view;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import static org.bytedeco.javacpp.opencv_core.IplImage;
import static org.bytedeco.javacpp.opencv_core.cvFlip;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;

public class Camera implements MouseListener {

    private BufferedImage image;

    public Camera() {
        JFrame window = new JFrame("Java t'identifier");
        JButton button = new JButton("S'identifier");
        button.addMouseListener(this);
        Panel container = new Panel();

        window.setSize(500, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());
        container.add(button, BorderLayout.SOUTH);
        window.setContentPane(container);
        window.setVisible(true);

        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);

        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        Java2DFrameConverter paintConverter = new Java2DFrameConverter();
        opencv_core.IplImage img;
        try {
            grabber.start();
            while (true) {
                Frame frame = grabber.grab();

                img = converter.convert(frame);

                //the grabbed frame will be flipped, re-flip to make it right
                cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise

                //save
                this.image = paintConverter.getBufferedImage(converter.convert(img));
                container.setImg(this.image);
                container.updateUI();
                //window.showImage(converter.convert(img));
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
        cvSaveImage("capture.jpg",  iplImage);
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
