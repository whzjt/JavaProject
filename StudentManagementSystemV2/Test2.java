package com.tyj.StudentManagementSystemV2;

import java.util.ArrayList;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> studentArrayList = new ArrayList<>();
        ArrayList<UserPhone> userArrayList = new ArrayList<>();
        //用户登录界面
        loop0:while (true){
            System.out.println("--------------欢迎来到学生管理系统登录界面----------------");
            System.out.println("请选择以下操作：1登录 2注册 3忘记密码");
            System.out.println("请输入您的选择：");
            String userChoose=sc.next();
            switch (userChoose){
                case "1":
                    if(login(userArrayList)){
                        break loop0;
                    }else {
                        break;
                    }
                case "2":
                    register(userArrayList);
                    break;
                case "3":
                    forgetPassword(userArrayList);
                    break;
                default:
                    System.out.println("没有这个选项，请重新输入");
                    break;
            }
        }
        //学生系统界面
        loop:
        while (true) {
            //打印用户操作界面
            System.out.println("----------------欢迎来到学生管理系统----------------");
            System.out.println("请选择以下操作：1：添加学生 2：删除学生 3：修改学生 4：查询学生 5：打印所有学生信息 6: 退出");
            System.out.println("请输入您的选择：");
            String choose = sc.next();
            //总体架构
            switch (choose) {
                case "1":
                    addStudent(studentArrayList);
                    break;
                case "2":
                    deleteStudent(studentArrayList);
                    break;
                case "3":
                    modStudent(studentArrayList);
                    break;
                case "4":
                    queryStudent(studentArrayList);
                    break;
                case "5":
                    printStudent(studentArrayList);
                    break;
                case "6":
                    break loop;
                default:
                    System.out.println("输入不合法，请正确输入");
                    break;
            }
        }
    }
    //用户注册
    public static void register(ArrayList<UserPhone> al1){
        Scanner sc1=new Scanner(System.in);
        String buildUserName;
        String buildLogin1;
        String buildIdCard;
        String buildPhoneNumber;
        //验证用户名---必须为英文和数字组合且不能为纯数字
        while (true) {
            System.out.println("请输入您的用户名");
            buildUserName=sc1.next();
            boolean flag=getDifferentName(al1,buildUserName);
            if(flag){
                System.out.println("账号已经存在，请重新输入");
            }else {
                if (buildUserName.length()<3||buildUserName.length()>15){
                    System.out.println("用户名的长度必须在3-15之间，请重新输入");
                }else {
                    if(getEnglishAndNumberName(buildUserName)){
                        break;
                    }else {
                        System.out.println("请输入字母数字的组合名称，且不能为纯数字");
                    }
                }
            }
        }
        //验证用户密码
        while (true){
            System.out.println("请输入您的登录密码");
            buildLogin1=sc1.next();
            System.out.println("请确认您的密码");
            String buildLogin2=sc1.next();
            if(buildLogin1.equals(buildLogin2)){
                break;
            }else {
                System.out.println("两次密码不相同，请确认后重新输入");
            }
        }
        //验证用户身份证号码---号码为18位且前17位为数字，最后一位为数字或者x或X，首位不能为0
        while (true){
            System.out.println("请输入您的身份证号码");
            buildIdCard=sc1.next();
            if(buildIdCard.length()!=18){
                System.out.println("身份证必须为18位");
            }else {
                if (buildIdCard.charAt(0)=='0'){
                    System.out.println("身份证首位不能为0");
                }else {
                    if(!getNumber(buildIdCard.substring(0,17))){
                        System.out.println("身份证前17位必须为数字");
                    }else {
                        if ((buildIdCard.charAt(buildIdCard.length()-1)>='0'&&buildIdCard.charAt(buildIdCard.length()-1)<='9')||(buildIdCard.charAt(buildIdCard.length()-1)=='x')||(buildIdCard.charAt(buildIdCard.length()-1)=='X')){
                            break;
                        }
                        else {
                            System.out.println("身份证最后一位必须是数字或者x或者X");
                        }
                    }
                }
            }
        }
        //验证手机号码---首位不能为0且长度为11的纯数字
        while (true){
            System.out.println("请输入您的手机号码");
            buildPhoneNumber=sc1.next();
            if(buildPhoneNumber.length()!=11){
                System.out.println("手机号码长度必须为11");
            }else {
                if (buildPhoneNumber.charAt(0)=='0'){
                    System.out.println("手机号码不能以0开头");
                }else {
                    if (getNumber(buildPhoneNumber)){
                        break;
                    }else {
                        System.out.println("手机号码必须为纯数字");
                    }
                }
            }
        }
        //保存用户信息
        UserPhone user1=new UserPhone(buildUserName,buildLogin1,buildIdCard,buildPhoneNumber);
        al1.add(user1);
        System.out.println("恭喜你！账号注册成功！");
    }
    //用户登录---监测是否有账号，如果有则有三层输入密码机会，密码成功就进入到学生管理系统
    public static boolean login(ArrayList<UserPhone> al1){
        Scanner sc1=new Scanner(System.in);
        for (int i = 0; i < 3; ) {
            System.out.println("请输入您要登录的账号：");
            String loginName=sc1.next();
            if (getIndex(al1,loginName)>=0){
                System.out.println("请输入登录密码");
                String longinPassword=sc1.next();
                if (al1.get(getIndex(al1,loginName)).getPassword().equals(longinPassword)){
                    System.out.println("恭喜您，登录成功");
                    return true;
                }else {
                    if (i<2){
                        System.out.println("密码错误，请重新输入，您还剩"+(2-i)+"次机会");
                        i++;
                    }else {
                        System.out.println("登录失败");
                    }
                }
            }else {
                System.out.println("用户不存在");
            }
        }
        return false;
    }
    //忘记密码---用户必须输入注册时候输入的身份证和手机号，成功则有三次机会修改密码，否则修改失败
    public static void forgetPassword(ArrayList<UserPhone> al1){
        Scanner sc1=new Scanner(System.in);
        System.out.println("请输入需要重置密码的账号");
        String forgetName=sc1.next();
        if (getIndex(al1,forgetName)>=0){
            System.out.println("请输入您的身份证号码：");
            String idCard=sc1.next();
            System.out.println("请输入您的手机号码：");
            String phoneNumber=sc1.next();
            int tempIndex=getIndex(al1,forgetName);
            if (al1.get(tempIndex).getIdCard().equals(idCard)&&al1.get(tempIndex).getPhoneNumber().equals(phoneNumber)){
                for (int i = 0; i < 3; i++) {
                    System.out.println("请输入您要修改的密码");
                    String newPassword1=sc1.next();
                    System.out.println("请确认您的密码");
                    String newPassword2=sc1.next();
                    if (newPassword1.equals(newPassword2)){
                        al1.get(tempIndex).setPassword(newPassword1);
                        System.out.println("密码修改成功，请返回登录");
                        break;
                    }else {
                        if (i<2){
                            System.out.println("两次密码不一致，您还剩"+(2-i)+"次机会");
                        }else {
                            System.out.println("密码修改失败");
                        }
                    }
                }
            }else {
                System.out.println("身份证或手机号码认证失败");
            }
        }else {
            System.out.println("账号不存在");
        }
    }
    //给出相同账号的集合索引
    public static int getIndex(ArrayList<UserPhone> al1,String name){
        for (int i = 0; i < al1.size(); i++) {
            if(al1.get(i).getUserName().equals(name)){
                return i;
            }
        }
        return -1;
    }
    //判断是否为纯数字
    public static boolean getNumber(String name){
        for (int i = 0; i < name.length(); i++) {
            if(name.charAt(i)<'0'||name.charAt(i)>'9'){
                return false;
            }
        }
        return true;
    }
    //判断字母数量
    public static int getEnglishNumber(String name){
        int count=0;
        for (int i = 0; i < name.length(); i++) {
            char tempEnglish=name.charAt(i);
            if ((tempEnglish>='a'&&tempEnglish<='z')||(tempEnglish>='A'&&tempEnglish<='Z')){
                count++;
            }
        }
        return count;
    }
    //判断是否为数字或者字母组合
    public static boolean getEnglishAndNumberName(String name){
        if(getEnglishNumber(name)<=0){
            return false;
        }
        for (int i = 0; i < name.length(); i++) {
            char tempName=name.charAt(i);
            if((tempName>='a'&&tempName<='z')||(tempName>='A'&&tempName<='Z')||(tempName>='0'&&tempName<='9')){
               return true;
            }
        }
        return false;
    }
    //判断用户名是否重复
    public static boolean getDifferentName(ArrayList<UserPhone> useAl,String name){
        for (int i = 0; i < useAl.size(); i++) {
            if(useAl.get(i).getUserName().equals(name)){
                return true;
            }
        }
        return false;
    }
    //判断Id在集合中是否存在
    public static boolean getDifferentId(ArrayList<Student> al, String id) {
        for (int i = 0; i < al.size(); i++) {
            String tempId = al.get(i).getId();
            if (tempId.equals(id)) {
                return true;
            }
        }
        return false;
    }

    //添加学生
    public static void addStudent(ArrayList<Student> al) {
        Scanner sc = new Scanner(System.in);
        String studentId;
        while (true) {
            System.out.println("请输入学生ID");
            studentId = sc.next();
            if (getDifferentId(al, studentId)) {
                System.out.println("当前学生Id已经存在，请重新输入");
            } else {
                break;
            }
        }

        System.out.println("请输入学生姓名");
        String studentName = sc.next();
        System.out.println("请输入年龄");
        int studentAge = sc.nextInt();
        System.out.println("请输入家庭住址");
        String studentAddress = sc.next();
        Student student = new Student(studentId, studentName, studentAge, studentAddress);
        al.add(student);
        System.out.println("学生信息添加成功");
    }

    //删除学生
    public static void deleteStudent(ArrayList<Student> al) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入准备删除学生Id");
        String studentId = sc.next();
        if (getDifferentId(al, studentId)) {
            for (int i = 0; i < al.size(); i++) {
                if (al.get(i).getId().equals(studentId)) {
                    al.remove(i);
                    System.out.println("学生信息删除成功");
                    break;
                }
            }
        } else {
            System.out.println("用户不存在，请重新输入");
        }
    }

    //修改学生信息
    public static void modStudent(ArrayList<Student> al) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改的学生Id");
        String studentId = sc.next();
        if (getDifferentId(al, studentId)) {
            for (int i = 0; i < al.size(); i++) {
                if (al.get(i).getId().equals(studentId)) {
                    System.out.println("请输入学生修改后的ID");
                    String newStudentId = sc.next();
                    System.out.println("请输入学生修改后的姓名");
                    String newStudentName = sc.next();
                    System.out.println("请输入学生修改后的年龄");
                    int newStudentAge = sc.nextInt();
                    System.out.println("请输入学生修改后的家庭住址");
                    String newStudentAddress = sc.next();
                    Student newStudent = new Student(newStudentId, newStudentName, newStudentAge, newStudentAddress);
                    al.set(i, newStudent);
                    System.out.println("学生信息修改成功");
                    break;
                }
            }
        } else {
            System.out.println("用户不存在，请重新输入");
        }
    }

    //查询学生
    public static void queryStudent(ArrayList<Student> al) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要查询学生Id");
        String studentId = sc.next();
        if (getDifferentId(al, studentId)) {
            for (int i = 0; i < al.size(); i++) {
                if (al.get(i).getId().equals(studentId)) {
                    System.out.println(al.get(i).getId() + " " + al.get(i).getName() + " " + al.get(i).getAge() + " " + al.get(i).getAddress());
                    break;
                }
            }
        } else {
            System.out.println("用户不存在，请重新查询");
        }
    }

    //打印学生信息
    public static void printStudent(ArrayList<Student> al) {
        if (al.size() == 0) {
            System.out.println("当前没有用户，请添加后查询");
        } else {
            System.out.println("Id\t\t姓名\t年龄\t家庭住址");
            for (int i = 0; i < al.size(); i++) {
                System.out.println(al.get(i).getId() + " " + al.get(i).getName() + " " + al.get(i).getAge() + " " + al.get(i).getAddress());
            }
        }
    }
}
