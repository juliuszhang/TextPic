package com.julius.code.model;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import static com.google.common.base.Preconditions.*;

/**
 * @author zhangyibo
 * @date 2018/6/23 下午7:45
 */
public class Picture {

    private final String path;

    /**
     * AllPixel<RowPixel>
     */
    private List<List<PicturePixel>> picturePixels;

    public Picture(String path, int scanPrecision) {
        checkArgument(!Strings.isNullOrEmpty(path), "path is null or empty");
        checkArgument(new File(path).exists(), "picture is not exsit");
        this.path = path;
        this.parseImage(scanPrecision);
    }

    /**
     * 获取整张图片的像素RGB信息
     *
     * @param scanPrecision 图片采集精度
     * @return
     */
    public void parseImage(int scanPrecision) {
        File file = new File(this.path);
        BufferedImage bi;
        try {
            bi = ImageIO.read(file);
        } catch (Exception e) {
            //简单起见 包装成runtime 抛出
            throw new RuntimeException(e);
        }
        int width = bi.getWidth();
        int height = bi.getHeight();
        int minx = bi.getMinX();
        int miny = bi.getMinY();
        List<List<PicturePixel>> picturePixels
                = Lists.newArrayListWithExpectedSize((height - miny));
        for (int i = miny; i < height; i += scanPrecision) {
            List<PicturePixel> rowPixels = Lists.newArrayListWithExpectedSize(width - minx);
            for (int j = minx; j < width; j += scanPrecision) {
                int pixel = bi.getRGB(j, i);
                PicturePixel picturePixel = new PicturePixel(j, i, pixel);
                System.out.println(picturePixel);
                rowPixels.add(picturePixel);
            }
            picturePixels.add(rowPixels);
        }

        this.picturePixels = picturePixels;
    }

    public String getPath() {
        return path;
    }

    public List<List<PicturePixel>> getPicturePixels() {
        return picturePixels;
    }

    /**
     * 图片像素类
     */
    public static class PicturePixel {

        public static final int RGB_MAX_VALUE = 0xffffff;

        private int posX;

        private int posY;

        private int pixel;

        private int r;

        private int g;

        private int b;

        public PicturePixel(int posX, int posY, int pixel) {
            //param check
            checkArgument(posX >= 0, "posX is unValid,posX = " + posX);
            checkArgument(posY >= 0, "posY is unValid,posY = " + posY);
            checkArgument(pixel <= RGB_MAX_VALUE, "pixel value is unValid,pixelVal = " + pixel);

            this.posX = posX;
            this.posY = posY;

            this.pixel = pixel;
            this.r = (pixel & 0xff0000) >> 16;
            this.g = (pixel & 0xff00) >> 8;
            this.b = (pixel & 0xff);
        }

        public int getPosX() {
            return posX;
        }

        public int getPosY() {
            return posY;
        }

        public int getPixel() {
            return pixel;
        }

        public int getR() {
            return r;
        }

        public int getG() {
            return g;
        }

        public int getB() {
            return b;
        }

        @Override
        public String toString() {
            return "PicturePixel{" +
                    "posX=" + posX +
                    ", posY=" + posY +
                    ", pixel=" + pixel +
                    ", r=" + r +
                    ", g=" + g +
                    ", b=" + b +
                    '}';
        }
    }
}
