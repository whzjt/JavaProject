package com.tyj.ui;

import javax.swing.*;

public class Register extends JFrame {
    public Register(){
        //初始化界面
        buildRegisterUI();
        //显示注册界面窗口
        this.setVisible(true);
    }
    //注册界面设置
    private void buildRegisterUI(){
        //设置注册界面宽高
        this.setSize(520,500);
        //设置注册界面标题
        this.setTitle("拼图 注册");
        //注册界面始终在最前面
        this.setAlwaysOnTop(true);
        //界面居中
        this.setLocationRelativeTo(null);
    }
}
