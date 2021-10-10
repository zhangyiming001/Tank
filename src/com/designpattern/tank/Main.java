package com.designpattern.tank;

import java.util.Objects;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/2 8:29
 * ^
 * @Description:
 **/
public class Main {
    public static void main(String[] args) throws Exception {
        TankFrame tankFrame = new TankFrame();
        int initTanKCount = Integer.parseInt((String) Objects.requireNonNull(PropertyMgr.get("initTankCount")));

        for (int i = 0; i < initTanKCount; i++) {
            tankFrame.baseTanks.add(tankFrame.gameFactory.crateTank(50 + i * 30, 200, Dir.DOWN, Group.BAD, tankFrame));
//             tankFrame.baseTanks.add(new BaseTank(50+i*30,200,Dir.DOWN,Group.BAD,tankFrame));
        }
        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }

    }
}