package com.designpattern.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/8/28 21:11
 * ^
 * @Description: 读取配置文件
 **/
public class PropertyMgr {
    static  Properties properties = new Properties();
    static {
        try {
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //获取配置文件的值
    public  static  Object get(String key){
        if (properties == null)
            return null;
        return properties.get(key);
    }
}
