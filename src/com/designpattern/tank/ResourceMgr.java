package com.designpattern.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/3 9:26
 * ^
 * @Description:
 **/
public class ResourceMgr {
    public static BufferedImage tankL,tankU,tankR,tankD;
    public static BufferedImage bulletL,bulletU,bulletR,bulletD;

    static {
        try {
            ClassLoader classLoader = ResourceMgr.class.getClassLoader();
            tankL = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/tankL.gif")));
            tankU = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/tankU.gif")));
            tankR = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/tankR.gif")));
            tankD = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/tankD.gif")));

            bulletL = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/bulletL.gif")));
            bulletU = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/bulletU.gif")));
            bulletR = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/bulletR.gif")));
            bulletD = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("images/bulletD.gif")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
