package com.zzwdkj.common;

import java.util.Random;

/**
 * @author guoxianwei 2016/6/20.
 */
public class RandomUtil {

    public static String getRandom(int length) {
        StringBuilder sb = new StringBuilder("");
        // 种子你可以随意生成，但不能重复
        String[] seed = {"1", "4", "3", "2", "5", "7", "9", "8", "6", "0"};
        String[] ranArr = new String[34];
        Random ran = new Random();
        // 数量你可以自己定义。
        for (int i = 0; i < seed.length; i++) {
            // 得到一个位置
            int j = ran.nextInt(seed.length - i);
            // 得到那个位置的数值
            ranArr[i] = seed[j];
            // 将最后一个未用的数字放到这里
            seed[j] = seed[seed.length - 1 - i];
        }
        for (int i = 0; i < length; i++) {
            sb.append(ranArr[i]);
        }
        return sb.toString();
    }

    public static String checkCode(String args) {
        byte[] bys = args.getBytes();
        int checkBit = 0;
        for (int i = 0; i < bys.length; i++) {
            int c = (int) bys[i];
            if (c >= 48) {
                checkBit += c - 48;

            } else if (c < 48) {
                checkBit += (256 - (48 - c));
            }
            if (checkBit > 255) {
                checkBit -= 256;
            }
        }
        checkBit = checkBit % 10;
        return String.valueOf(checkBit);
    }
}
