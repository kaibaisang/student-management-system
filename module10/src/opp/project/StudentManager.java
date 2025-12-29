package opp.project;

import java.util.ArrayList;
import java.util.Scanner;
public class StudentManager {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //先放几个测试数据
        addTestData();

        while (true){
            showMenu();
            int choice = sc.nextInt();
            sc.nextLine();//消耗换行符

            switch (choice){
                case 1:
                    showAllStudents();
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    updateScore();
                    break;
                case 6:
                    System.out.println("谢谢使用，再见！");
                    return;//退出程序
                default:
                    System.out.println("输入错误，请重新输入！");
            }
            System.out.println("\n按回车继续");
            sc.nextLine();  //消耗换行符
        }
    }

    private static void showMenu(){
        System.out.println("\n======== 学生管理系统 v3.0 (ArrayList版) ========");
        System.out.println("1.显示所有学生");
        System.out.println("2.添加学生");
        System.out.println("3.根据姓名查找学生");
        System.out.println("4.根据姓名删除学生");
        System.out.println("5.修改学生分数");
        System.out.println("6.退出系统");
        System.out.print("请作出你的选择:");
    }
    private static void addTestData(){
        students.add(new Student("John", 20, 80.0));
        students.add(new Student("Mary", 21, 70.0));
        students.add(new Student("Tom", 22, 95.0));
        students.add(new Student("Jane", 23, 85.0));
        students.add(new Student("Lily", 19, 65.0));
    }
    private static void showAllStudents(){
        System.out.println("\n=== 所有学生信息（共 " + students.size() + " 人） ===");
        if (students.isEmpty()){
            System.out.println("暂无学生信息！");
            return;
        }
        for (Student stu : students) {
            stu.showInfo();
        }
    }
    private static void addStudent(){
        System.out.println("\n=== 添加学生 ===");
        System.out.println("请输入姓名：");
        String name = sc.nextLine();
        System.out.println("请输入年龄：");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.println("请输入分数：");
        double score = sc.nextDouble();
        sc.nextLine();

        students.add(new Student(name, age, score));    // 直接add，自动扩容！
        System.out.println("添加成功！当前总数：" + students.size());
    }
    private static void searchStudent(){
        System.out.println("\n=== 查找学生 ===");
        System.out.print("请输入要查找的姓名：");
        String name = sc.nextLine();

        boolean found = false;
        for (Student stu : students){
            if (stu.getName().equals(name)){
                stu.showInfo();
                found = true;
            }
        }
        if (!found){
            System.out.println("未找到该学生！");
        }
    }
    private static void deleteStudent(){
        System.out.println("\n=== 删除学生 ===");
        System.out.print("请输入要删除的姓名：");
        String name = sc.nextLine();
        int index = -1;
        boolean removed = students.removeIf(stu -> stu.getName().equals(name));
        // removeIf 是 Java 8 新特性，超级好用！返回 true 表示删除了至少一个
        if(removed){
            System.out.println("删除成功！当前剩余：" + students.size());
        }else {
            System.out.println("未找到该学生,无法删除！");
        }
    }
    private static void updateScore(){
        System.out.println("\n=== 修改学生分数 ===");
        System.out.print("请输入要修改的姓名：");
        String name = sc.nextLine();

        boolean found = false;
        for (Student stu : students) {
            if (stu.getName().equals(name)) {
                System.out.print("请输入新的分数：");
                double score = sc.nextDouble();
                sc.nextLine();
                stu.setScore(score);
                System.out.println("修改成功！");
                found = true;
                break;  // 找到了就退出循环(避免重名全改）
            }
            if (!found){
                System.out.println("未找到该学生，无法修改！");
            }
        }
    }
}
