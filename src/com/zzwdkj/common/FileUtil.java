package com.zzwdkj.common;

import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author yinminjie 2016/7/19.
 */
public class FileUtil {

    /**
     * 保存文件
     *
     * @param request
     * @param file
     * @param relativeFilePath:为相对路径，工程目录下开始
     * @return
     */
    public static boolean saveFile(HttpServletRequest request, MultipartFile file,
                                   String relativeFilePath) {
        // 判断文件是否为空
        if (file == null
                || relativeFilePath == null || relativeFilePath.isEmpty()) {
            return false;
        }
        if (!file.isEmpty()) {
            try {
                String absoluteFilePath = request.getSession().getServletContext()
                        .getRealPath("/") + relativeFilePath;
                File saveDir = new File(absoluteFilePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();
                // 转存文件
                file.transferTo(saveDir);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /**
     * 保存文件
     *
     * @param request
     * @param imgdata
     * @param relativeFilePath:为相对路径，工程目录下开始
     * @return
     */
    public static boolean saveImgdataFile(HttpServletRequest request, String imgdata,
                                          String relativeFilePath) {
        // 判断文件是否为空
        if (imgdata == null || imgdata.isEmpty()
                || relativeFilePath == null || relativeFilePath.isEmpty()) {
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        FileOutputStream outputStream = null;
        try {
            String imgPath = request.getSession().getServletContext()
                    .getRealPath("/") + relativeFilePath;
            File saveDir = new File(imgPath);
            if (!saveDir.getParentFile().exists())
                saveDir.getParentFile().mkdirs();
            // new一个文件对象用来保存图片，默认保存当前工程根目录
            File imageFile = new File(imgPath);
            // 创建输出流
            outputStream = new FileOutputStream(imageFile);
            // 获得一个图片文件流，我这里是从flex中传过来的
            byte[] result = decoder.decodeBuffer(imgdata.split(",")[1]);//解码
            for (int i = 0; i < result.length; ++i) {
                if (result[i] < 0) {// 调整异常数据
                    result[i] += 256;
                }
            }
            outputStream.write(result);
            return true;
        } catch (Exception e1) {
            e1.printStackTrace();
            return false;
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
