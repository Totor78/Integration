package view;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.VideoInputFrameGrabber;

import static org.bytedeco.javacpp.opencv_core.cvFlip;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;

public class Camera implements Runnable {
    //final int INTERVAL=1000;///you may use interval
    opencv_core.IplImage image;
    CanvasFrame canvas = new CanvasFrame("Web Cam");
    OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
    public Camera() {
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void run() {
        FrameGrabber grabber = new VideoInputFrameGrabber(0); // 1 for next camera
        int i=0;
        try {
            grabber.start();
            opencv_core.IplImage img;
            while (true) {
                img = converter.convert(grabber.grab());
                if (img != null) {
                    cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise
                    cvSaveImage((i++)+"-aa.jpg", img);
                    // show image on window
                    canvas.showImage(grabber.grab());
                }
                //Thread.sleep(INTERVAL);
            }
        } catch (Exception e) {
        }
    }



    public static void main(String[] args) {
        Camera gs = new Camera();
        Thread th = new Thread(gs);
        th.start();
    }
}
