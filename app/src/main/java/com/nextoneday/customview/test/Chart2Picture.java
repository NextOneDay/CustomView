package com.nextoneday.customview.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.IOException;


/**
 * Created by nextonedaygg on 2018/12/5.
 */

public class Chart2Picture {


    /**
     * @param path 图片路径
     */
//    public static void createAsciiPic(final String path) {
//        final String base = "@#&$%*o!;.";// 字符串由复杂到简单
//        try {
//            final BufferedImage image = ImageIO.read(new File(path));
//            for (int y = 0; y < image.getHeight(); y += 2) {
//                for (int x = 0; x < image.getWidth(); x++) {
//                    final int pixel = image.getRGB(x, y);
//                    final int r = (pixel & 0xff0000) >> 16, g = (pixel & 0xff00) >> 8, b = pixel & 0xff;
//                    final float gray = 0.299f * r + 0.578f * g + 0.114f * b;
//                    final int index = Math.round(gray * (base.length() + 1) / 255);
//                    System.out.print(index >= base.length() ? " " : String.valueOf(base.charAt(index)));
//                }
//                System.out.println();
//            }
//        } catch (final IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void createChartPicture(String path){

        String base = "!@#$%^&*[]{};:?/|";
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        for (int i = 0; i < bitmap.getHeight(); i++) {
            for (int j = 0; j < bitmap.getWidth(); j++) {

                int pixel = bitmap.getPixel(j, i);
                final int r = (pixel & 0xff0000) >> 16, g = (pixel & 0xff00) >> 8, b = pixel & 0xff;
                final float gray = 0.299f * r + 0.578f * g + 0.114f * b;
                final int index = Math.round(gray * (base.length() + 1) / 255);
                System.out.print(index >= base.length() ? " " : String.valueOf(base.charAt(index)));
            }
            System.out.println();
        }

    }

    /**
     * test
     *
     * @param args
     */
//    public static void main(final String[] args) {
//        Chart2Picture.createChartPicture("F:/图片/图片/徐贤/abc.jpg");
//    }


}
