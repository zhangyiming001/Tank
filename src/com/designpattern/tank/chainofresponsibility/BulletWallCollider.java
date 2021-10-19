package com.designpattern.tank.chainofresponsibility;

import com.designpattern.tank.*;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/16 14:42
 * @Description: 实现具体的游戏物体直接的碰撞逻辑
 **/
public class BulletWallCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof BaseBullet baseBullet && o2 instanceof Wall wall){

            if (baseBullet.rectangle.intersects(wall.rectangle)) {
                baseBullet.die();
            }

        }else if (o1 instanceof Wall && o2 instanceof BaseBullet){
            return collide(o2,o1);
        }
        return true;
    }
}
