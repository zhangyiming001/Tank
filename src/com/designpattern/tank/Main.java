package com.designpattern.tank;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/2 8:29
 * ^
 * @Description:
 **/
public class Main {
    public static void main(String[] args) throws InterruptedException {

        TankFrame tankFrame = new TankFrame();
        for (int i = 0; i < 5; i++) {
            tankFrame.tanks.add(new Tank(50+i*30,200,Dir.DOWN,Group.BAD,tankFrame));
        }
        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }

    }
}
