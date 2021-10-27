package com.designpattern.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/3 9:26
 * @Description:
 **/
public class ResourceMgr {
    public static BufferedImage bulletL, bulletU, bulletR, bulletD;
    public static BufferedImage[] explodes = new BufferedImage[16];
    public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD;
    public static BufferedImage badTankL, badTankU, badTankR, badTankD;

    static {
        try {
            ClassLoader classLoader = ResourceMgr.class.getClassLoader();

            goodTankU = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/GoodTank1.png")));

            goodTankL = ImageUtil.rotateImage(goodTankU, -90);
            goodTankR = ImageUtil.rotateImage(goodTankU, 90);
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);

            badTankU = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/BadTank1.png")));
            badTankL = ImageUtil.rotateImage(badTankU, -90);
            badTankR = ImageUtil.rotateImage(badTankU, 90);
            badTankD = ImageUtil.rotateImage(badTankU, 180);

            bulletU = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/bulletU.png")));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);
            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/e" + (i + 1) + ".gif")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
