package utils;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;

/**
 * CvUtil
 *
 * @author Grigory Dyakonov
 */

public class CvUtil {

    public static void show(Mat img, String name) {
        JFrame window = new JFrame("OpenCV Computer Vision "+ name);
        JLabel screen = new JLabel();
        window.setContentPane(screen);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MatOfByte buffer = new MatOfByte();
        Imgcodecs.imencode(".jpg", img, buffer);
        screen.setIcon(new ImageIcon(buffer.toArray()));
        screen.repaint();
        window.pack();
    }
}
