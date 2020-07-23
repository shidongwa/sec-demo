package com.stone.vuln.json.serialize;

import com.alibaba.fastjson.JSON;
import com.stone.vuln.Employee;

/**
 * @Author: shidonghua
 * @Description:
 * @Date: 7/23/20 08:00
 * @Version: 1.0
 */
public class FastJsonTest {

    public static void main(String[] args) {
        test1();
    }

    /**
     * fastjson反序列化时先采用默认构造函数初始化再调用setter方法重置字段。所以pojo必须提供setter方法，否则不能成功序列化/反序列化
     */
    public static void test1() {
        Employee employee = new Employee(1,"牛1");
        System.out.println("employ:" + employee);

        //这里将javabean转化成json字符串
        String jsonString = JSON.toJSONString(employee);
        System.out.println("json str:" + jsonString);

        //这里将json字符串转化成javabean对象,
        employee = JSON.parseObject(jsonString, Employee.class);
        System.out.println("employ:" + employee);
    }
}
