import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.*;

import static org.bytedeco.javacpp.opencv_core.cvFlip;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvSaveImage;

public class Test {

    final int INTERVAL = 100;///you may use interval
    CanvasFrame canvas;

    public Test(CanvasFrame _canvas) {
        canvas = _canvas;
    }

    public void run() {

        FrameGrabber grabber = new VideoInputFrameGrabber(0); // 1 for next camera
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        opencv_core.IplImage img;
        int i = 0;
        try {
            grabber.start();
            while (true) {
                Frame frame = grabber.grab();

                img = converter.convert(frame);

                //the grabbed frame will be flipped, re-flip to make it right
                cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise

                //save
                cvSaveImage((i++) + "-aa.jpg", img);

                canvas.showImage(converter.convert(img));

                Thread.sleep(INTERVAL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
