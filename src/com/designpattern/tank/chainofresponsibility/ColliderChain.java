package com.designpattern.tank.chainofresponsibility;

import com.designpattern.tank.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/16 19:38
 * @Description:创建 ColliderChain 是为了调用各个碰撞器之间的责任链 此类继承Collider是为了 日后可以以责任链为单位做比较
 * BulletTankCollider、TankTankCollider都实现了collide 所以创建List<Collider> 一次拿出对应的对象找到他们自己比较的内容完成责任链
 *
 **/
public class ColliderChain implements Collider{

    public ColliderChain(){
        add(new BulletTankCollider());
        add(new TankTankCollider());

    }
    private final List<Collider> COLLIDERS = new LinkedList<>();
    public void add(Collider collider){
        COLLIDERS.add(collider);
    }

    public boolean collide(GameObject o1, GameObject o2) {
        for (Collider collider : COLLIDERS) {
//            没撞上返回true 撞上了返回false；如何子弹发射打死坦克但是责任链继续执行就是带来潜在的BUG，串联执行，前面停止就应该中止责任链
            if (!collider.collide(o1, o2)) {
                return false;
            }
        }
        return true;
    }
}
