package com.shiro.vuln.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {


    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        System.out.println("index收到请求-------");
        return "hello";
    }


    @PostMapping("/json")
    @ResponseBody
    public JSONObject parse(@RequestBody String data){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",0);
        //ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        //com.sun.org.apache.bcel.internal.util.ClassLoader；
       // com.sun.org.apache.bcel.internal.util.ClassPath

        // java.awt.Point

        JSON.parse(data);
        JSON.parseObject(data);
        return jsonObject;
    }
}
