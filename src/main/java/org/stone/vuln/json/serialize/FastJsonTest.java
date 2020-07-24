package com.stone.vuln.json.serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.stone.vuln.Employee;
import org.junit.Test;

/**
 * @Author: shidonghua
 * @Description:
 * @Date: 7/23/20 08:00
 * @Version: 1.0
 */
public class FastJsonTest {

/*    static {
        ParserConfig.getGlobalInstance().addAccept("com.stone.vuln.Employee");
    }*/

    /**
     * fastjson反序列化时先采用默认构造函数初始化再调用setter方法重置字段。所以pojo必须提供setter方法，否则不能成功序列化/反序列化
     */
    @Test
    public void test1() {
        Employee employee = new Employee(1,"牛1");
        System.out.println("employ:" + employee);

        //这里将javabean转化成json字符串
        String jsonString = JSON.toJSONString(employee);
        System.out.println("json str:" + jsonString);

        //这里将json字符串转化成javabean对象,
        employee = JSON.parseObject(jsonString, Employee.class);
        System.out.println("employ:" + employee);
    }

    /**
     * com.stone.vuln.Employee 加到白名单或者fastjson版本切换到1.2.24及以下
     */
    @Test
    public void test2() {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        Employee employee = new Employee(1,"牛1");
        System.out.println("employ:" + employee);

        //这里将javabean转化成json字符串, 带type
        String jsonString = JSON.toJSONString(employee, SerializerFeature.WriteClassName);
        System.out.println("jsonString:" + jsonString);

        employee = (Employee)JSON.parse(jsonString);
//        employee = JSON.parseObject(jsonString, Employee.class);
        System.out.println("employ:" + employee);
    }
}
