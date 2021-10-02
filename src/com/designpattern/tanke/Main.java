package com.designpattern.tanke;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/2 8:29
 * ^
 * @Description:
 **/
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankeFrame tankeFrame = new TankeFrame();

        while (true){
            Thread.sleep(50);
            tankeFrame.repaint();
        }
    }
}
