package com.sharing.cn.utils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class OcrUtils {

    public static String identify(String url) {

        // 创建实例
        ITesseract instance = new Tesseract();
        // 设置识别语言
        instance.setLanguage("chi_sim");
        // 设置识别引擎
        instance.setOcrEngineMode(1);
        // 读取文件
        BufferedImage image = null;
        // 识别
        String result = null;
        try {
            InputStream resourceAsStream = OcrUtils.class.getResourceAsStream(url);
            if (null != resourceAsStream) {
                image = ImageIO.read(resourceAsStream);
                result = instance.doOCR(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
