package com.designpattern.tank;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/2 8:29
 * ^
 * @Description:
 **/
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame TankFrame = new TankFrame();
        System.out.println();
        while (true){
            Thread.sleep(50);
            TankFrame.repaint();
        }
    }
}
