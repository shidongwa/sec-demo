package com.stone.vuln;

import java.io.IOException;
import java.io.Serializable;

/**
 *
 */
public class Employee implements Serializable {

/*    static {
        try {
            // 执行命令
            Runtime.getRuntime().exec(new String[]{"/bin/bash","-c", "/Applications/Calculator.app/Contents/MacOS/Calculator"});
        }catch (IOException e){
            // ignore
        }
    }*/

    public Employee(long id, String name) {
        System.out.println("Employee init(id, name)");
        this.id = id;
        this.name = name;
    }

    public Employee() {
        System.out.println("Employee init()");
        try {
            // 执行命令
            Runtime.getRuntime().exec(new String[]{"/bin/bash","-c", "/Applications/Calculator.app/Contents/MacOS/Calculator"});
        }catch (IOException e){
            // ignore
        }
    }

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
