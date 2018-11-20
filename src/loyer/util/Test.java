package loyer.util;

import org.opencv.core.Mat;

public class Test {

  public static void main(String[] args) {

    Mat src = ImgTools.read("F:\\picture\\face.jpg");
    ImgTools.write("F:\\picture\\faceDec1.jpg", ImgTools.faceDetection(src));
  }

}
