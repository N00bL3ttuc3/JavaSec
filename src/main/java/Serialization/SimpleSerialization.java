package Serialization;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author L3ttuc3
 * @version 1.0
 * @date 2023/5/7 20:36
 */
public class SimpleSerialization {
    public static void main(String[] args) throws Exception {
//        testSerialization();
//        testPersonSerialization();
        testDummySerialization();
    }

    public static void testSerialization() throws Exception{
        serialization(new Date());
        unSerialization();
    }

    public static void serialization(Date date) throws Exception{
        FileOutputStream fileOutputStream = new FileOutputStream("res.ser");
        ObjectOutput objectOutput = new ObjectOutputStream(fileOutputStream);
        objectOutput.writeObject(date);
        objectOutput.flush();
    }

    public static void unSerialization() throws Exception{
        FileInputStream fileInputStream = new FileInputStream("res.ser");
        ObjectInput objectInput = new ObjectInputStream(fileInputStream);
        Date o = (Date) objectInput.readObject();
        System.out.println(o);
    }

    public static void testPersonSerialization() throws Exception{
        Person person = new Person("张三");
        personSerialization(person);
        personUnSerialization();
    }

    public static void personSerialization(Person person) throws Exception{
        FileOutputStream fileOutputStream = new FileOutputStream("res.ser");
        ObjectOutput objectOutput = new ObjectOutputStream(fileOutputStream);
        objectOutput.writeObject(person);
        objectOutput.flush();
    }

    public static void personUnSerialization() throws Exception{
        FileInputStream fileInputStream = new FileInputStream("res.ser");
        ObjectInput objectInput = new ObjectInputStream(fileInputStream);
        Person o = (Person) objectInput.readObject();
        System.out.println(o);
    }

    public static void testDummySerialization() throws Exception{
        byte[] bytes = dummySerialization();
        dummyUnSerialization(bytes);
    }

    public static byte[] dummySerialization() throws Exception{
        Dummy dummy = new Dummy("open -a Calculator.app");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(dummy);
        return byteArrayOutputStream.toByteArray();
    }

    public static void dummyUnSerialization(byte[] bytes) throws Exception{
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Dummy dummy = (Dummy) objectInputStream.readObject();
        System.out.println(dummy);
    }

}

class Person implements Serializable{
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    private void readObject(ObjectInputStream s) throws Exception{
        System.out.println("Person readObject method called");
        name = s.readUTF();
    }

    private void writeObject(ObjectOutputStream s) throws Exception{
        System.out.println("Person writeObject method called");
        s.writeUTF(name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Dummy implements Serializable{
    private String cmd;

    public Dummy(String cmd) {
        this.cmd = cmd;
    }

    private void readObject(ObjectInputStream stream) throws Exception{
        cmd = stream.readUTF();
        Runtime.getRuntime().exec(cmd);
    }

    private void writeObject(ObjectOutputStream stream) throws Exception{
        stream.writeUTF(cmd);
    }
}