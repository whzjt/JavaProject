package com.tyj.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game extends JFrame implements KeyListener, ActionListener {
    int randomNumber[][]=new int[4][4];
    int win[][]={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
    int x=0;
    int y=0;
    int count=0;
    String path="Day2\\image\\girl\\girl1\\";
    //创建二级菜单
    JMenuItem rePlay=new JMenuItem("重新游戏");
    JMenuItem reLogin=new JMenuItem("重新登录");
    JMenuItem closeGame=new JMenuItem("关闭游戏");

    JMenuItem introduce=new JMenuItem("玩法介绍");
    JMenuItem Shortcuts=new JMenuItem("快捷键");

    JMenuItem gameExplain=new JMenuItem("游戏说明");

    JMenuItem animals=new JMenuItem("动物");
    JMenuItem girl=new JMenuItem("美女");
    JMenuItem sport=new JMenuItem("运动");
    public Game(){
        //初始化界面
        buildGameUI();
        //初始化菜单
        buildMenu();
        //初始化数据
        getRandomArr();
        //初始化图片
        addImage();
        //显示游戏主界面
        this.setVisible(true);
    }
    //打乱图片顺序
    private void getRandomArr(){
        Random r=new Random();
        int tempRandomNumber[]={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

        int count=0;
        for (int i = 0; i < tempRandomNumber.length; i++) {
            int j=r.nextInt(tempRandomNumber.length);
            int temp=tempRandomNumber[i];
            tempRandomNumber[i]=tempRandomNumber[j];
            tempRandomNumber[j]=temp;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                randomNumber[i][j]=tempRandomNumber[count];
                if(randomNumber[i][j]==0){
                    x=j;
                    y=i;
                }
                count++;
            }
        }
    }
    //在主界面中添加图片
    private void addImage(){
        //清空原本出现的图片
        this.getContentPane().removeAll();
        //判断游戏是否胜利
        if(victory()){
            ImageIcon winPicture=new ImageIcon("Day2\\image\\win.png");
            JLabel tempFlag=new JLabel(winPicture);
            tempFlag.setBounds(200,280,197,73);
            this.getContentPane().add(tempFlag);
        }
        //统计步数
        JLabel step=new JLabel("步数："+count);
        step.setBounds(20,10,100,20);
        this.getContentPane().add(step);
        //开始添加图片
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //创建图片文件位置对象
                ImageIcon addPicture=new ImageIcon(path+randomNumber[i][j]+".jpg");
                //创建图片管理容器
                JLabel managePicture=new JLabel(addPicture);
                //指定图片位置
                managePicture.setBounds(105*j+84,105*i+115,105,105);
                //添加边框
                managePicture.setBorder(new BevelBorder(1));
                //将图片添加到容器里面
                this.getContentPane().add(managePicture);
            }
        }
        //添加游戏背景
        ImageIcon background=new ImageIcon("Day2\\image\\background.png");
        JLabel manageBackground=new JLabel(background);
        manageBackground.setBounds(40,20,508,560);
        this.getContentPane().add(manageBackground);
        //刷新界面
        this.getContentPane().repaint();
    }
    //主界面设置
    private void buildGameUI() {
        //设置游戏主界面宽高
        this.setSize(600,650);
        //设置游戏标题
        this.setTitle("拼图小游戏V1.0");
        //游戏主界面始终在最前面
        this.setAlwaysOnTop(true);
        //界面居中
        this.setLocationRelativeTo(null);
        //取消图片默认居中
        this.setLayout(null);
        //游戏关闭
        this.setDefaultCloseOperation(3);
        //设置监听事件
        this.addKeyListener(this);
    }
    //主界面菜单设置
    private void buildMenu(){
        //创建主菜单界面
        JMenuBar mainUI=new JMenuBar();
        //创建主菜单
        JMenu functionMenu=new JMenu("功能");
        JMenu shortcut=new JMenu("游戏介绍");
        JMenu explain=new JMenu("关于我们");

        JMenu changePicture=new JMenu("更换图片");
        //将二级菜单添加到主菜单
        functionMenu.add(changePicture);
        functionMenu.add(rePlay);
        functionMenu.add(reLogin);
        functionMenu.add(closeGame);

        changePicture.add(animals);
        changePicture.add(girl);
        changePicture.add(sport);

        shortcut.add(introduce);
        shortcut.add(Shortcuts);

        explain.add(gameExplain);
        //将主菜单添加到菜单界面中
        mainUI.add(functionMenu);
        mainUI.add(shortcut);
        mainUI.add(explain);
        //将主菜单添加到游戏主界面当中
        this.setJMenuBar(mainUI);
        //监听二级菜单
        rePlay.addActionListener(this);
        reLogin.addActionListener(this);
        closeGame.addActionListener(this);
        introduce.addActionListener(this);
        Shortcuts.addActionListener(this);
        gameExplain.addActionListener(this);
        animals.addActionListener(this);
        sport.addActionListener(this);
        girl.addActionListener(this);
    }
    //

    @Override
    public void keyTyped(KeyEvent e) {

    }
    //按住不放
    @Override
    public void keyPressed(KeyEvent e) {
        if (victory()){
            return;
        }
        int code=e.getKeyCode();
        if(code==65){
            this.getContentPane().removeAll();

            ImageIcon resultPicture=new ImageIcon(path+"all.jpg");
            JLabel tempResult=new JLabel(resultPicture);
            tempResult.setBounds(84,114,420,420);
            this.getContentPane().add(tempResult);

            ImageIcon background=new ImageIcon("Day2\\image\\background.png");
            JLabel manageBackground=new JLabel(background);
            manageBackground.setBounds(40,20,508,560);
            this.getContentPane().add(manageBackground);

            this.getContentPane().repaint();
        }
    }
    //松开
    @Override
    public void keyReleased(KeyEvent e) {
        if (victory()){
            return;
        }
        int code=e.getKeyCode();
        //左移
        if(code==37){
            if(x==0){
                return;
            }
            randomNumber[y][x]=randomNumber[y][x-1];
            randomNumber[y][x-1]=0;
            x--;
            count++;
            addImage();
        }
        //上移
        else if(code==38){
            if(y==0){
                return;
            }
            randomNumber[y][x]=randomNumber[y-1][x];
            randomNumber[y-1][x]=0;
            y--;
            count++;
            addImage();
        }
        //右移
        else if(code==39){
            if (x==3){
                return;
            }
            randomNumber[y][x]=randomNumber[y][x+1];
            randomNumber[y][x+1]=0;
            x++;
            count++;
            addImage();
        }
        //下移
        else if(code==40){
            if(y==3){
                return;
            }
            randomNumber[y][x]=randomNumber[y+1][x];
            randomNumber[y+1][x]=0;
            y++;
            count++;
            addImage();
        }
        else if (code==65){
            addImage();
        }
        //一键通关
        else if (code==87){
            randomNumber=new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
            addImage();
        }
    }
    //判断胜利
    public boolean victory(){
        for (int i = 0; i < randomNumber.length; i++) {
            for (int j = 0; j < randomNumber.length; j++) {
                if(randomNumber[i][j]!=win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    //实现二级菜单功能
    @Override
    public void actionPerformed(ActionEvent e) {
        Random r=new Random();
        Object temp=e.getSource();
        if(temp==rePlay){
            count=0;
            getRandomArr();
            addImage();
        }else if (temp==reLogin){
            this.setVisible(false);
            new Login();
        }else if (temp==closeGame) {
            System.exit(0);
        }else if (temp==gameExplain) {
            JDialog dialog=new JDialog();
            //设置弹框大小
            dialog.setSize(430,100);
            dialog.setTitle("关于我们");
            //设置弹框置顶
            dialog.setAlwaysOnTop(true);
            //设置弹框居中
            dialog.setLocationRelativeTo(null);
            //设置无法操作弹框下的界面
            dialog.setModal(true);
            JLabel tempSpeak=new JLabel("作者：往后这几天" +"    "+"源码：https://github.com/whzjt/JavaProject/tree/main");
            dialog.getContentPane().add(tempSpeak);
            //显示弹框
            dialog.setVisible(true);
        }else if (temp==introduce) {
            //介绍玩法
            JDialog play=new JDialog();
            play.setSize(320,100);
            play.setTitle("玩法介绍");
            play.setAlwaysOnTop(true);
            play.setLocationRelativeTo(null);
            play.setModal(true);
            JLabel tempShort=new JLabel("左移：←    右移：→    下移：↓    上移：↑");
            play.getContentPane().add(tempShort);
            play.setVisible(true);
        }else if (temp==Shortcuts) {
            //介绍快捷键
            JDialog cut=new JDialog();
            cut.setSize(200,100);
            cut.setTitle("快捷键");
            cut.setAlwaysOnTop(true);
            cut.setLocationRelativeTo(null);
            cut.setModal(true);
            JLabel tempShort=new JLabel("查看最终效果：A"+"    "+"快速通关：W");
            cut.getContentPane().add(tempShort);
            cut.setVisible(true);
        }else if (temp==animals) {
            if (victory()){
                return;
            }
            path="Day2\\image\\animal\\animal"+r.nextInt(1,9)+"\\";
            count=0;
            addImage();
        }else if (temp==girl) {
            if (victory()){
                return;
            }
            path="Day2\\image\\girl\\girl"+r.nextInt(1,12)+"\\";
            count=0;
            addImage();
        }else if (temp==sport) {
            if (victory()){
                return;
            }
            path="Day2\\image\\sport\\sport"+r.nextInt(1,11)+"\\";
            count=0;
            addImage();
        }
    }
}
