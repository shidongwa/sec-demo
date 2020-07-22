package com.stone.vuln.java.serialize;


import com.stone.vuln.Employee;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class SerializeTest {
    public static final String exportData = "/tmp/employee.dat";

    public static void main(String[] args) {
        Employee employee = new Employee(1L, "张三");
        System.out.println("employee:" + employee);

        byte[] bytes = serialize(employee);

        // 反序列化时构造函数不执行
        Employee employee2 = deSerialize(bytes);
        assert employee2 != null;
        System.out.println("employee2:" + employee2);
    }

    public static byte[] serialize(Employee employee) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(employee);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bos.toByteArray();
    }

    /**
     * 反序列化时构造函数不执行
     * https://docs.oracle.com/javase/8/docs/platform/serialization/spec/serial-arch.html#a4539
     * "Have access to the no-arg constructor of its first nonserializable superclass"
     */
    public static Employee deSerialize(byte[] serializedObject) {
        ByteArrayInputStream bis = new ByteArrayInputStream(serializedObject);
        Employee employee = null;
        try {
            ObjectInputStream in = new ObjectInputStream(bis);
            employee = (Employee) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return employee;
    }
}
