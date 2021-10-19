package com.designpattern.tank.chainofresponsibility;

import com.designpattern.tank.GameObject;
/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/16 14:42
 * @Description: 创建各个游戏物体之间的碰撞接口，让各个游戏物体继承，主要还是一个多态的运用
 **/
public interface Collider {
    boolean collide(GameObject o1,GameObject o2);
}
