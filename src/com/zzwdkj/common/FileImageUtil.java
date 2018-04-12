package com.zzwdkj.common;

/**
 * @author yinminjie 2016/6/29.
 */

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.imageio.stream.MemoryCacheImageInputStream;

/**
 * @author yinminjie  2016/6/29.
 */
public class FileImageUtil {

    /**
     * 把MultipartFile文件转化为BufferedImage对象
     *
     * @param multipartFile
     * @return
     */
    public static BufferedImage multipartFileToImage(MultipartFile multipartFile) {
        if (multipartFile == null) {
            return null;
        }
        BufferedImage bufferedImage = null;
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(multipartFile.getBytes());
            MemoryCacheImageInputStream mciis = new MemoryCacheImageInputStream(bais);
            bufferedImage = ImageIO.read(mciis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }

    /**
     * 剪裁图片
     *
     * @param bufferedImage 源图像
     * @param x             目标切片起点x坐标
     * @param y             目标切片起点y坐标
     * @param destWidth     目标切片宽度
     * @param destHeight    目标切片高度
     */
    public static BufferedImage abscut(BufferedImage bufferedImage, int x, int y, int destWidth, int destHeight) {
        if (bufferedImage == null) {
            return null;
        }
        try {
            // 读取源图像
            BufferedImage bi = bufferedImage;
            int srcWidth = bi.getWidth(); // 源图宽度
            int srcHeight = bi.getHeight(); // 源图高度
            if (srcWidth >= destWidth && srcHeight >= destHeight
                    && (
                    srcWidth >= (x + destWidth)
                            && srcHeight >= (y + destWidth)
            )) {
                int x1 = x;//* srcWidth / 420;
                int y1 = y;//* srcWidth / 420;
                int w = destWidth;//* srcWidth / 420;
                int h = destHeight;// * srcWidth / 420;
                BufferedImage tag = bi.getSubimage(x1, y1, w, h);
                return tag;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}