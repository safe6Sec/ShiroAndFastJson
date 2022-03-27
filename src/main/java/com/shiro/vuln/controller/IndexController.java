package com.shiro.vuln.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


import com.shiro.vuln.filter.MemBehinderFilter;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@Controller
public class IndexController {


    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        System.out.println("index收到请求-------");
        return "hello";
    }


    @RequestMapping("/getKey")
    @ResponseBody
    public String getKey() {
        byte[] key = new CookieRememberMeManager().getCipherKey();
        return new String(Base64.getEncoder().encode(key));
    }


    @RequestMapping("/setKey")
    @ResponseBody
    public String setKey() {

        try {
            byte[] key =Base64.getDecoder().decode("2AvVhdsgUs0FSA3SDFAdag==") ;

            org.apache.tomcat.util.threads.TaskThread thread = (org.apache.tomcat.util.threads.TaskThread) Thread.currentThread();
            java.lang.reflect.Field field = thread.getClass().getSuperclass().getDeclaredField("contextClassLoader");
            field.setAccessible(true);
            Object obj = field.get(thread);
            field = obj.getClass().getSuperclass().getSuperclass().getDeclaredField("resources");
            field.setAccessible(true);
            obj = field.get(obj);
            field = obj.getClass().getDeclaredField("context");
            field.setAccessible(true);
            obj = field.get(obj);
            field = obj.getClass().getSuperclass().getDeclaredField("filterConfigs");
            field.setAccessible(true);
            obj = field.get(obj);
            java.util.HashMap objMap = (java.util.HashMap) obj;
            java.util.Iterator entries = objMap.entrySet().iterator();
            while (entries.hasNext()) {
                java.util.Map.Entry entry = (Map.Entry) entries.next();
                if (entry.getKey().equals("shiroFilter")) {
                    obj = entry.getValue();
                    field = obj.getClass().getDeclaredField("filter");
                    field.setAccessible(true);
                    obj = field.get(obj);
                    field = obj.getClass().getSuperclass().getDeclaredField("securityManager");
                    field.setAccessible(true);
                    obj = field.get(obj);
                    field = obj.getClass().getSuperclass().getDeclaredField("rememberMeManager");
                    field.setAccessible(true);
                    obj = field.get(obj);
                    java.lang.reflect.Method setEncryptionCipherKey = obj.getClass().getSuperclass().getDeclaredMethod("setEncryptionCipherKey", new Class[]{byte[].class});
                    setEncryptionCipherKey.invoke(obj,new Object[]{key});
                    java.lang.reflect.Method setDecryptionCipherKey = obj.getClass().getSuperclass().getDeclaredMethod("setDecryptionCipherKey", new Class[]{byte[].class});
                    setDecryptionCipherKey.invoke(obj,new Object[]{key});

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
        return "ok";
    }

    @RequestMapping("/init")
    @ResponseBody
    public String init() throws Exception {
        //new MemBehinder3(Thread.currentThread().getContextClassLoader());
        //new MemBehinderFilter(Thread.currentThread().getContextClassLoader())
        return "init";
    }

    @RequestMapping("hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/json")
    @ResponseBody
    public JSONObject parse(@RequestBody String data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 0);
        //ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        //com.sun.org.apache.bcel.internal.util.ClassLoader；
        // com.sun.org.apache.bcel.internal.util.ClassPath

        // java.awt.Point

        JSON.parse(data);
        //JSON.parseObject(data);
        return jsonObject;
    }
}
