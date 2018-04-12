package com.zzwdkj.common;

/**
 * 图片压缩
 * Created by xizhuangchui on 2015/8/14.
 */
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ScaleImage {
    /**
     * 图片压缩
     * @param imgsrc 源图片地址
     * @param imgdist 新图片地址
     * @param widthdist  宽
     * @param heightdist 高
     * @param level 图片压缩质量（1：保持源质量）
     */
    public void reduceImg(String imgsrc, String imgdist, int widthdist,
                          int heightdist,float level ) {
        try {
            File srcfile = new File(imgsrc);
            if (!srcfile.exists()) {
                return;
            }
            Image src = javax.imageio.ImageIO.read(srcfile);
            ImageIcon ii = new ImageIcon(imgsrc);
            double realWidth = ii.getIconWidth();
            double realHeight = ii.getIconHeight();
            if(realWidth>widthdist||realHeight>heightdist)
            {

                if (realWidth >= realHeight) {
                    //width = widthdist;
                    heightdist = (int)(realHeight * (widthdist / realWidth));
                } else {
                    //height = heightdist;
                    widthdist = (int)(realWidth * (heightdist / realHeight));
                }
            }

            BufferedImage tag= new BufferedImage((int) widthdist, (int) heightdist,
                    BufferedImage.TYPE_INT_RGB);
            if(src!=null)
            {
                tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist,  Image.SCALE_SMOOTH), 0, 0,  null);
                //tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist,  Image.SCALE_AREA_AVERAGING), 0, 0,  null);

                FileOutputStream out = new FileOutputStream(imgdist);
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	        	JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(tag);
                  /* 压缩质量 */
                param.setQuality( level, true);
	            encoder.encode(tag, param);

                out.close();
            }



        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args){
        //new ScaleImage().reduceImg();
    }

}
