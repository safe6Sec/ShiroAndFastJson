package com.shiro.vuln.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;

@Controller
public class SerController {

    @RequestMapping("/ser")
    @ResponseBody
    public String ser(String str) {

        if (str!=null){
            try {
                ObjectInputStream obs = new ObjectInputStream(new ByteArrayInputStream(Base64.getDecoder().decode(str)));
                Object o = obs.readObject();
                o.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return "error";
            }

        }

        return "ok";
    }
}
