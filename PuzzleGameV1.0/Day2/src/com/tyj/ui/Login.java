package com.tyj.ui;

import javax.swing.*;

public class Login extends JFrame {
    String loginPath="C:\\Users\\asus\\IdeaProjects\\Project0\\Day2\\image\\login\\";
    public Login(){
        //初始化登录界面
        buildLoginUI();
        //初始化图片
        addLoginPicture();

        //显示登录界面
        this.setVisible(true);
    }
    //登陆界面设置
    private void buildLoginUI() {
        //设置登录界面宽高
        this.setSize(520,450);
        //设置登录界面标题
        this.setTitle("拼图 登录");
        //登录界面始终在最前面
        this.setAlwaysOnTop(true);
        //界面居中
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
    }
    private void addLoginPicture(){
        //
        ImageIcon userName=new ImageIcon(loginPath+"用户名.png");
        JLabel manageName=new JLabel(userName);
        manageName.setBounds(100,140,47,17);
        this.getContentPane().add(manageName);

        ImageIcon password=new ImageIcon(loginPath+"密码.png");
        JLabel managePassword=new JLabel(password);
        managePassword.setBounds(100,185,32,16);
        this.getContentPane().add(managePassword);

        ImageIcon showPassword=new ImageIcon(loginPath+"显示密码.png");
        JLabel manageShow=new JLabel(showPassword);
        manageShow.setBounds(280,185,18,29);
        this.getContentPane().add(manageShow);

        ImageIcon captcha=new ImageIcon(loginPath+"验证码.png");
        JLabel manageCaptcha=new JLabel(captcha);
        manageCaptcha.setBounds(100,230,56,21);
        this.getContentPane().add(manageCaptcha);
        //添加登录按键
        ImageIcon loginCut=new ImageIcon(loginPath+"登录按钮.png");
        JLabel manageLoginCut=new JLabel(loginCut);
        manageLoginCut.setBounds(85,280,128,47);
        this.getContentPane().add(manageLoginCut);
        //添加注册按键
        ImageIcon registerCut=new ImageIcon(loginPath+"注册按钮.png");
        JLabel manageRegisterCut=new JLabel(registerCut);
        manageRegisterCut.setBounds(285,280,128,47);
        this.getContentPane().add(manageRegisterCut);
        //添加背景
        ImageIcon loginBackground=new ImageIcon(loginPath+"background.png");
        JLabel manageLogin=new JLabel(loginBackground);
        manageLogin.setBounds(10,20,470,390);
        this.getContentPane().add(manageLogin);
    }
}
