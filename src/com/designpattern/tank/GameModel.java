package com.designpattern.tank;

import com.designpattern.tank.chainofresponsibility.BulletTankCollider;
import com.designpattern.tank.chainofresponsibility.Collider;
import com.designpattern.tank.chainofresponsibility.ColliderChain;
import com.designpattern.tank.chainofresponsibility.TankTankCollider;

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
//    由于子弹坦克爆炸都继承了GameModel 所以当创建list的时候只需要 GameModel中添加即可
//    List<BaseBullet> baseBullets = new ArrayList();
//    List<BaseTank> baseTanks = new ArrayList<>();
//    List<BaseExplode> baseExplodes = new ArrayList<>();
    List<GameObject> gameObjects =  new ArrayList();
//    利用ColliderChain
//    Collider bulletTankCollider = new BulletTankCollider();
//    Collider tankTankCollider = new TankTankCollider();
    ColliderChain chain = new ColliderChain();

    public GameModel() throws Exception {
        int initTanleCount = Integer.parseInt((String) Objects.requireNonNull(PropertyMgr.get("initTankCount")));

        for (int i = 0; i < initTanleCount; i++) {
            this.add(new BaseTank(50+i*30,200,Dir.DOWN,Group.BAD,this));
        }

        add(new Wall(150,150,200,50));
        add(new Wall(550,150,200,50));
        add(new Wall(300,300,50,200));
        add(new Wall(550,300,50,200));
    }
    public void add(GameObject gameObject){
        this.gameObjects.add(gameObject);
    }
    public void remove(GameObject gameObject){
        this.gameObjects.remove(gameObject);
    }
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.white);
//        g.drawString("子弹数量："+ baseBullets.size(),10,60);
//        g.drawString("敌人数量："+ baseTanks.size(),10,80);
//        g.drawString("爆炸数量："+ baseExplodes.size(),10,100);
        g.setColor(c);
        myBaseTank.paint(g);
//        for (int i = 0; i < baseBullets.size(); i++) {
//            baseBullets.get(i).paint(g);
//        }
//        for (int i = 0; i < baseTanks.size(); i++) {
//            baseTanks.get(i).paint(g);
//        }
//        for (int i = 0; i < baseExplodes.size(); i++) {
//            baseExplodes.get(i).paint(g);
//        }
        /**
         * 历史中需要对每一个list都画出来但是现在所有物体都在一个list中只需要花一个就可以
         *
         * */
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
//          碰撞检测
            for (int j = i+1; j < gameObjects.size(); j++) {
                GameObject o1 = gameObjects.get(i);
                GameObject o2 = gameObjects.get(j);
//                bulletTankCollider.collide(o1,o2);
//                tankTankCollider.collide(o1,o2);
                chain.collide(o1,o2);
            }
        }
//        for (int i = 0; i < gameObjects.size(); i++) {
//
//        }
        //碰撞检测
//        for (int i = 0; i < baseBullets.size(); i++) {
//            for (int j = 0; j < baseTanks.size(); j++) {
//                baseBullets.get(i).collidwith(baseTanks.get(j));
//            }
//        }
    }

    public BaseTank getMainTank() {
        return myBaseTank;
    }
}
