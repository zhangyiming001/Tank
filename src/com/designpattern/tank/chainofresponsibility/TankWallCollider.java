package com.designpattern.tank.chainofresponsibility;

import com.designpattern.tank.BaseTank;
import com.designpattern.tank.GameObject;
import com.designpattern.tank.Wall;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/16 14:42
 * @Description: 实现具体的游戏物体直接的碰撞逻辑
 **/
public class TankWallCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof BaseTank baseTank && o2 instanceof Wall wall){

            if (baseTank.rectangle.intersects(wall.rectangle)) {
                baseTank.back();
            }

        }else if (o1 instanceof Wall && o2 instanceof BaseTank){
            return collide(o2,o1);
        }
        return true;
    }
}
