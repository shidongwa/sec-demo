package com.stone.vuln.java.serialize;


import org.apache.commons.io.IOUtils;

import java.io.*;

public class SerializeTest {
    public static final String exportData = "/tmp/employee.dat";

    public static void main(String[] args) {
/*        Employee employee = new Employee(1L, "张三");
        serialize(employee);
        System.out.println("employee:" + employee);*/

        // 反序列化时构造函数不执行
        Employee employee2 = deSerialize();
        assert employee2 != null;
        System.out.println("employee2:" + employee2);
    }

    public static void serialize(Employee employee) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(exportData));
            out.writeObject(employee);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 反序列化时构造函数不执行
     * https://docs.oracle.com/javase/8/docs/platform/serialization/spec/serial-arch.html#a4539
     * "Have access to the no-arg constructor of its first nonserializable superclass"
     */
    public static Employee deSerialize() {
        ObjectInputStream in = null;
        Employee employee = null;
        try {
            in = new ObjectInputStream(new FileInputStream(exportData));
            employee = (Employee) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
        }

        return employee;
    }
}
