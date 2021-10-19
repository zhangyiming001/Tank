package com.designpattern.tank.chainofresponsibility;

import com.designpattern.tank.BaseTank;
import com.designpattern.tank.GameObject;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/16 14:42
 * @Description: 实现具体的游戏物体直接的碰撞逻辑
 **/
public class TankTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof BaseTank baseTank1 && o2 instanceof BaseTank baseTank2) {
            if (baseTank1.getRectangle().intersects(baseTank2.getRectangle())) {
                baseTank1.back();
                baseTank2.back();
            }
        }
        return true;
    }
}
