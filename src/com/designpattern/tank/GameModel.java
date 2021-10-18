package com.designpattern.tank;

import com.designpattern.tank.chainofresponsibility.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/15 17:25
 * @Description:门面模式（Facade）
 **/
public class GameModel {
    private static final GameModel INSTANCE = new GameModel();
    BaseTank myBaseTank;
/**
 * 由于子弹坦克爆炸都继承了GameModel 所以当创建list的时候只需要 GameModel中添加即可
 * List<BaseBullet> baseBullets = new ArrayList();
 * List<BaseTank> baseTanks = new ArrayList<>();
 * List<BaseExplode> baseExplodes = new ArrayList<>();
 */
    ArrayList<GameObject> gameObjects =  new ArrayList<>();
/**    利用ColliderChain
 *   Collider bulletTankCollider = new BulletTankCollider();
 *   Collider tankTankCollider = new TankTankCollider();
 */
    ColliderChain chain = new ColliderChain();

    public static GameModel getINSTANCE() {
        return INSTANCE;
    }

    private GameModel()  {
        int initTanleCount = Integer.parseInt((String) Objects.requireNonNull(PropertyMgr.get("initTankCount")));

        for (int i = 0; i < initTanleCount; i++) {
            this.add(new BaseTank(50+i*30,200,Dir.DOWN,Group.BAD,this));
        }

        add(new Wall(150,150,200,50));
        add(new Wall(550,150,200,50));
        add(new Wall(300,300,50,200));
        add(new Wall(550,300,50,200));
    }
    static {
        INSTANCE.init();
    }
    private void init(){
        //        初始化主站坦克
        myBaseTank = new BaseTank(200, 200, Dir.DOWN, Group.GOOD);
        int initTanleCount = Integer.parseInt((String) Objects.requireNonNull(PropertyMgr.get("initTankCount")));

        for (int i = 0; i < initTanleCount; i++) {
            new BaseTank(50+i*30,200,Dir.DOWN,Group.BAD);
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

//      历史中需要对每一个list都画出来但是现在所有物体都在一个list中只需要花一个就可以
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
//          碰撞检测
            for (int j = i+1; j < gameObjects.size(); j++) {
                GameObject o1 = gameObjects.get(i);
                GameObject o2 = gameObjects.get(j);
                chain.collide(o1,o2);
            }
        }
    }

    public BaseTank getMainTank() {
        return myBaseTank;
    }
}
