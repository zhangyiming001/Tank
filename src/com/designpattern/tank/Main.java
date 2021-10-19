package com.designpattern.tank;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/2 8:29
 * @Description:
 **/
public class Main {
    public static void main(String[] args) throws Exception {
        TankFrame tankFrame = new TankFrame();

        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }

    }
}