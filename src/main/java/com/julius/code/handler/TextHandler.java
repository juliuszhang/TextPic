package com.julius.code.handler;

import com.julius.code.model.Picture;

/**
 * @author zhangyibo
 * @date 2018/6/23 下午7:37
 */
public class TextHandler {

    private static final char[] chars = new char[]{' ', '-', '(', '1', ')', '0', '8', '*', '#', '@'};

    public static char pixel2Char(int pixel) {
        int divVal = Picture.PicturePixel.RGB_MAX_VALUE / chars.length + 1;
        return chars[Math.abs(pixel) / divVal];
    }

}
