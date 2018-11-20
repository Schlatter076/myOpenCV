package loyer.util;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 * 图像处理工具类，封装opencv方法
 * 
 * @author Loyer
 * @coding UTF-8
 */
public class ImgTools {

  private ImgTools() {
  } // 不允许其他类构造本类实例

  static {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // 加载opencv库
  }

  /**
   * 在图像上画框
   * 
   * @param matrix
   *          源图像
   * @param p1
   *          起始坐标
   * @param p2
   *          结束坐标
   * @param color
   *          方框颜色
   * @param thickness
   *          方框厚度
   * @return 处理过的图像
   */
  public static Mat rectangle(Mat matrix, Point p1, Point p2, Scalar color, int thickness) {
    Mat src = matrix;
    Imgproc.rectangle(src, p1, p2, color, thickness);
    return src;
  }

  /**
   * 从硬盘读取图像矩阵
   * 
   * @param filename
   *          文件名(如:F:\img.jpg)
   * @return 读取到的图像矩阵
   */
  public static Mat read(String filename) {
    Mat matrix = Imgcodecs.imread(filename);
    return matrix;
  }

  /**
   * 将图像矩阵转储到本地
   * 
   * @param filename
   *          文件名
   * @param src
   *          待转储的资源
   * @return
   */
  public static boolean write(String filename, Mat src) {
    boolean bool = Imgcodecs.imwrite(filename, src);
    return bool;
  }

  /**
   * 图像灰度化处理
   * 
   * @param src
   *          源
   * @return 处理后的资源
   */
  public static Mat toGray(Mat src) {
    Mat matrix = new Mat();
    Imgproc.cvtColor(src, matrix, Imgproc.COLOR_RGB2GRAY);
    return matrix;
  }

  /**
   * 人像识别，如果检测到脸部，则在脸部画个方框
   * 
   * @param filename
   *          待检测的图像
   * @return 检测后的图像矩阵
   */
  public static Mat faceDetection(String filename) {

    Mat src = Imgcodecs.imread(filename);
    // Instantiating the CascadeClassifier
    String xmlFile = "./xml/lbpcascade_frontalface.xml";
    CascadeClassifier classifier = new CascadeClassifier(xmlFile);
    // Detecting the face in the snap
    MatOfRect faceDetections = new MatOfRect();
    classifier.detectMultiScale(src, faceDetections);
    // Drawing boxes
    for (Rect rect : faceDetections.toArray()) {
      Imgproc.rectangle(src, // where to draw the box
          new Point(rect.x, rect.y), // bottom left
          new Point(rect.x + rect.width, rect.y + rect.height), // top right
          new Scalar(0, 0, 255), 3); // RGB color
    }
    return src;
  }
  /**
   * 重载人脸识别方法
   * @param matrix
   * @return
   */
  public static Mat faceDetection(Mat matrix) {
    Mat src = matrix;
    // Instantiating the CascadeClassifier
    String xmlFile = "src/xml/lbpcascade_frontalface.xml";
    CascadeClassifier classifier = new CascadeClassifier(xmlFile);
    // Detecting the face in the snap
    MatOfRect faceDetections = new MatOfRect();
    classifier.detectMultiScale(src, faceDetections);
    // Drawing boxes
    for (Rect rect : faceDetections.toArray()) {
      Imgproc.rectangle(src, // where to draw the box
          new Point(rect.x, rect.y), // bottom left
          new Point(rect.x + rect.width, rect.y + rect.height), // top right
          new Scalar(0, 0, 255), 3); // RGB color
    }
    return src;
  }

}
