package org.stone.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.stone.vuln.Employee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: donghua
 * @date: 2020-07-24
 */
@RestController
public class DemoController {

    @RequestMapping("/")
    String home() {
        return "Hello Demo!";
    }

    @RequestMapping("/vuln/fastjson")
    public String fastjsonDeserial() {
        execJsonDeser();
        return "fastjson deserialization demo ok";
    }

    private void execJsonDeser() {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        Employee employee = new Employee(1,"牛1");
        System.out.println("employ:" + employee);

        //这里将javabean转化成json字符串, 带type
        String jsonString = JSON.toJSONString(employee, SerializerFeature.WriteClassName);
        System.out.println("jsonString:" + jsonString);

        // 反序列化为JSONObject时执行默认构造函数setter/getter方法
        JSON.parseObject(jsonString);
    }
}
