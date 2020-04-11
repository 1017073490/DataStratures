package com.zhangxing.hashtable;

import java.util.Scanner;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-9 19:51
 * 课后作业：id随机插入也要有序
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        Employee employee1 = new Employee(4,"zx");
        Employee employee2 = new Employee(235,"qw");
        Employee employee3 = new Employee(25444,"df");
        Employee employee4 = new Employee(2231,"df");
        Employee employee5 = new Employee(123421,"df");
        Employee employee6 = new Employee(20333,"df");
        Employee employee7 = new Employee(2056,"df");
        Employee employee8 = new Employee(200111,"df");
        Employee employee9 = new Employee(20,"df");
        hashTable.add(employee1);
        hashTable.add(employee2);
        hashTable.add(employee3);
        hashTable.add(employee4);
        hashTable.add(employee5);
        hashTable.add(employee6);
        hashTable.add(employee7);
        hashTable.add(employee8);
        hashTable.add(employee9);
        hashTable.showList();
    }

    public static void showMenu(){
        HashTable hashTable = new HashTable(7);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show) 显示");
            System.out.println("a(add) 添加");
            System.out.println("f(find) 退出");
            System.out.println("e(exit) 退出");
            System.out.println("请选择操作：");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    hashTable.showList();
                    break;
                case 'a':
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入姓名");
                    String name = scanner.next();
                    Employee employee = new Employee(id, name);
                    hashTable.add(employee);
                    break;
                case 'f':
                    System.out.println("输入查找的id");
                    int findId = scanner.nextInt();
                    hashTable.findById(findId);
                    break;
                case 'e':
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出。。。");
    }
}


/**
 * 创建HashTable管理多条链表
 */
class HashTable {
    private EmpLinkedList[] empLinkedList;
    private int size;

    public HashTable(int size) {
        this.size = size;
        //初始化empLinkedLists
        empLinkedList = new EmpLinkedList[size];
        //不要忘了分别初始化每一条链表
        for (int i = 0; i < size; i++) {
            empLinkedList[i] = new EmpLinkedList();
        }
    }

    public void add(Employee employee) {
        //根据员工的id得到该员工应该加入哪条链表
        int empLinkedListNo = hashFun(employee.id);
        //将employee加入到对应的链表
        empLinkedList[empLinkedListNo].addByOrder(employee);
    }

    public void showList() {
        //遍历我们的hash表
        for (int i = 0; i < size; i++) {
            empLinkedList[i].showList(i);
        }
    }

    public Employee findById(int id){
        int empLinkedListNo = hashFun(id);
        Employee employee = empLinkedList[empLinkedListNo].findById(id);
        if (employee !=null){
            System.out.println("在"+(empLinkedListNo+1)+"号链表中找到"+id+"号员工。");
        }else {
            System.out.println("无此人、、、");
        }
        return employee;
    }


    private int hashFun(int id) {
        //编写散列函数
        return id % size;
    }
}


class EmpLinkedList {
    /**
     * 头指针指向第一个Emp，因此我们这个链表的head是直接指向第一个Emp
     */
    private Employee head;

    /**
     * 假定添加雇员时，id是自增长的。因此直接添加到链表最后即可
     */
    public void add(Employee employee) {
        //如果是第一个员工
        if (head == null) {
            head = employee;
            return;
        }
        Employee curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = employee;

    }

    public void addByOrder(Employee employee) {
        if (head == null) {
            head = employee;
            return;
        }
        Employee curEmp = head;

        while (true) {
            if (curEmp.id > employee.id) {
                curEmp.next = employee;
                employee.next = curEmp;
                break;
            }
            if (curEmp.next == null){
                curEmp.next = employee;
                break;
            }
//            if (curEmp.next.id > employee.id){
//                //就应该放在次curEmp的下一个
//                employee.next = curEmp.next;
//                curEmp.next = employee;
//                break;
//            }
            curEmp = curEmp.next;
        }
    }

    public void showList(int id) {
        if (head == null) {
            System.out.println((id + 1) + "号链表为空。");
            return;
        }
        System.out.print("当前" + (id + 1) + "号链表的信息为");
        Employee curEmp = head;
        while (true) {
            System.out.print(" ===> id:" + curEmp.id + " name:" + curEmp.name);
            if (curEmp.next == null) {
                //最后一个节点
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    public Employee findById(int id) {
        if (head == null) {
            return null;
        }
        Employee curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break;
            }
            if (curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}


class Employee {
    public int id;
    public String name;
    public Employee next;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
