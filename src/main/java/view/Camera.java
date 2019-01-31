package view;


import org.bytedeco.javacv.FFmpegFrameRecorder;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

public class Camera {

    public static final String FILENAME = "output.mp4";

    public void run() throws Exception {
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        grabber.start();
        Frame grabbedImage = grabber.grab();
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();

        CanvasFrame canvasFrame = new CanvasFrame("Cam");
        canvasFrame.setCanvasSize(converter.convert(grabbedImage).width(), converter.convert(grabbedImage).height());

        System.out.println("framerate = " + grabber.getFrameRate());
        grabber.setFrameRate(grabber.getFrameRate());
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(FILENAME,  grabber.getImageWidth(),grabber.getImageHeight());

        recorder.setVideoCodec(13);
        recorder.setFormat("mp4");
        recorder.setFrameRate(30);
        recorder.setVideoBitrate(10 * 1024 * 1024);

        recorder.start();
        while (canvasFrame.isVisible() && (grabbedImage = grabber.grab()) != null) {
            canvasFrame.showImage(grabbedImage);
            recorder.record(grabbedImage);
        }
        recorder.stop();
        grabber.stop();
        canvasFrame.dispose();
    }
}
