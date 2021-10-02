package com.designpattern.tanke;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/2 9:04
 * ^
 * @Description:
 **/
public class TankeFrame extends Frame {
    public TankeFrame(){
        setSize(800,800);
        setResizable(false);
        setTitle("坦克大战");
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.fillRect(200,200,50,50);
    }
}
