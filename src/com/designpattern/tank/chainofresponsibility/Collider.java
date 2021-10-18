package com.designpattern.tank.chainofresponsibility;

import com.designpattern.tank.GameObject;

public interface Collider {
    boolean collide(GameObject o1,GameObject o2);
}
