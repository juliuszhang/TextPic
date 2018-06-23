package com.julius.code;

import com.google.common.base.Strings;
import com.julius.code.handler.Pic2TextHandler;

import java.util.Scanner;

/**
 * @author zhangyibo
 * @date 2018/6/23 下午7:02
 */
public class Starter {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("please input your picture path...");
            String picturePath = null;
            String textPath = null;
            int scanPrecision = 2;
            if (scanner.hasNext()) {
                picturePath = scanner.next();
                System.out.println("picture path is " + picturePath);
            }

            System.out.println("please input your output text path");
            if (scanner.hasNext()) {
                textPath = scanner.next();
                System.out.println("text path is " + textPath);
            }

            System.out.println("please input scan precision");
            if (scanner.hasNext()) {
                String inputPrecision = scanner.next();
                if (Strings.isNullOrEmpty(inputPrecision)) {
                    System.out.println("use the default precision 2");
                } else {
                    //未对是否是数字做校验
                    scanPrecision = Integer.parseInt(inputPrecision);
                    System.out.println("your precision is " + scanPrecision);
                }
            }

            new Pic2TextHandler().handle(picturePath, textPath, scanPrecision);
        }
    }

}
