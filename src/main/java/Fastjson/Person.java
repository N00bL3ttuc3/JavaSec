package Fastjson;

import java.io.IOException;

/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/4/24 16:43
 */
public class Person {
    private String name;
    private int age;

    public Person() {
        System.out.println("无参构造");
    }

    public Person(String name, int age) {
        System.out.println("有参构造");
        this.name = name;
        this.age = age;
    }

    public String getName() {
        System.out.println("getName Run");
        return name;
    }

    public void setName(String name) {
        System.out.println("setName Run");
//        try {
//            Runtime.getRuntime().exec("open -a Calculator.app");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        this.name = name;
    }

    public int getAge() {
        System.out.println("getAge Run");
        return age;
    }

    public void setAge(int age) {
        System.out.println("setAge Run");
        this.age = age;
    }

    @Override
    public String toString() {
        System.out.println("toString Run");
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
