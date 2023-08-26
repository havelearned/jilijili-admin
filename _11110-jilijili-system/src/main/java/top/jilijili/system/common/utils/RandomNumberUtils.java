package top.jilijili.system.common.utils;

import java.util.Random;

public class RandomNumberUtils {

    public static int generateRandomNumber(int minDigits, int maxDigits) {
        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, maxDigits - 1));

        // 如果生成的数字位数小于minDigits，补充0直到达到minDigits位数
        while (String.valueOf(randomNumber).length() < minDigits) {
            randomNumber *= 10;
        }

        return randomNumber;
    }


}
