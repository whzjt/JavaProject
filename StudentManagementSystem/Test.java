package com.tyj.StudentManagementSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        ArrayList<Student> studentArrayList = new ArrayList<>();
        loop:
        while (true) {
            //打印用户操作界面
            System.out.println("----------------欢迎来到归阳一中学生管理系统----------------");
            System.out.println("1：添加学生");
            System.out.println("2：删除学生");
            System.out.println("3：修改学生");
            System.out.println("4：查询学生");
            System.out.println("5：打印所有学生信息");
            System.out.println("6: 退出");
            System.out.println("请输入您的选择：");
            Scanner sc = new Scanner(System.in);
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
