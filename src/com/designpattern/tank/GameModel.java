package com.designpattern.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/15 17:25
 * ^
 * @Description:门面模式（Facade）
 **/
public class GameModel {
    BaseTank myBaseTank = new BaseTank(200, 200, Dir.DOWN, Group.GOOD, this);
    java.util.List<BaseBullet> baseBullets = new ArrayList();
    java.util.List<BaseTank> baseTanks = new ArrayList<>();
    List<BaseExplode> baseExplodes = new ArrayList<>();

    public GameModel() throws Exception {
        int initTanleCount = Integer.parseInt((String) Objects.requireNonNull(PropertyMgr.get("initTankCount")));

        for (int i = 0; i < initTanleCount; i++) {
            this.baseTanks.add(new BaseTank(50+i*30,200,Dir.DOWN,Group.BAD,this));
        }
    }
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹数量："+ baseBullets.size(),10,60);
        g.drawString("敌人数量："+ baseTanks.size(),10,80);
        g.drawString("爆炸数量："+ baseExplodes.size(),10,100);
        g.setColor(c);
        myBaseTank.paint(g);
        for (int i = 0; i < baseBullets.size(); i++) {
            baseBullets.get(i).paint(g);
        }
        for (int i = 0; i < baseTanks.size(); i++) {
            baseTanks.get(i).paint(g);
        }
        for (int i = 0; i < baseExplodes.size(); i++) {
            baseExplodes.get(i).paint(g);
        }
        //碰撞检测
        for (int i = 0; i < baseBullets.size(); i++) {
            for (int j = 0; j < baseTanks.size(); j++) {
                baseBullets.get(i).collidwith(baseTanks.get(j));
            }
        }
    }

    public BaseTank getMainTank() {
        return myBaseTank;
    }
}
