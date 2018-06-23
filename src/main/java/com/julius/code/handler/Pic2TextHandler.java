package com.julius.code.handler;

import com.julius.code.model.Picture;

import java.io.*;
import java.util.List;

import static com.google.common.base.Preconditions.*;

/**
 * @author zhangyibo
 * @date 2018/6/23 下午7:02
 */
public class Pic2TextHandler {

    public void handle(String picturePath, String outputFilePath, int scanPrecision) {
        checkArgument(new File(outputFilePath).getParentFile().exists(), "can not to create outputFile");
        checkArgument(new File(outputFilePath).getAbsolutePath().endsWith(".txt"), "outputFile path is unValid");

        Picture picture = new Picture(picturePath, scanPrecision);
        List<List<Picture.PicturePixel>> picturePixels = picture.getPicturePixels();

        File file = new File(outputFilePath);
        try (OutputStream os = new FileOutputStream(file)) {
            for (List<Picture.PicturePixel> rowPixels : picturePixels) {
                StringBuffer buffer = new StringBuffer();
                for (Picture.PicturePixel pixel : rowPixels) {
                    char c = TextHandler.pixel2Char(pixel.getPixel());
                    //降低io操作 每写一行重新创建buffer 防止图片过大撑爆buffer
                    buffer.append(c);
                }
                os.write(buffer.append("\n").toString().getBytes());
            }

            os.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
