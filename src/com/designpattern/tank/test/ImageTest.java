package com.designpattern.tank.test;


import com.designpattern.tank.ResourceMgr;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

class ImageTest {

    @Test
    public void test() throws IOException {
        BufferedImage read = ImageIO.read(Objects.requireNonNull(ImageTest.class.getClassLoader().getResourceAsStream("images/tankL.gif")));
        System.out.println(read);
        Assert.assertNull(read);
    }
}