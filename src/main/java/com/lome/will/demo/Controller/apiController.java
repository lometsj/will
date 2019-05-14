package com.lome.will.demo.Controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.lome.will.demo.Entity.Will;
import com.lome.will.demo.Service.UserService;
import com.lome.will.demo.Service.WillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class apiController {

    @Autowired
    UserService userService;

    @Autowired
    WillService willService;

    @PostMapping("/api/signUp")
    public JSONObject regist(@RequestBody JSONObject reg){
        JSONObject ret = new JSONObject();
        ret.put("ret",userService.signUp(reg.getString("name"), reg.getString("pwd"), reg.getString("pwd2"), reg.getString("email"), reg.getString("type")));
        return ret;
    }

    @PostMapping("/api/signIn")
    public JSONObject signIn(@RequestBody JSONObject data, HttpServletRequest httpServletRequest){
        JSONObject ret = new JSONObject();
        ret.put("ret",userService.singIn(data.getString("name"), data.getString("pwd"), data.getString("type")));
        if(ret.getString(("ret")).equals("succeed")){
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("user", data.getString("name"));
        }
        return ret;
    }

    @PostMapping("/api/upload")
    public JSONObject upload(@RequestParam("file") MultipartFile uploadFile, @RequestParam("pwd") String pwd, HttpServletRequest httpServletRequest){
        JSONObject ret = new JSONObject();
        HttpSession session = httpServletRequest.getSession();
        String name = (String )session.getAttribute("user");
        if(name == null) {
            ret.put("ret", "login first");
            return ret;
        }
        ret.put("ret",willService.upload(name, uploadFile, pwd));
        return ret;
    }

    @PostMapping("/api/getFilenameByName")
    public JSONObject fileName(HttpServletRequest httpServletRequest){
        String name = (String )httpServletRequest.getSession().getAttribute("user");
        JSONObject ret = new JSONObject();
        if(name == null) {
            ret.put("ret", "login first");
            return ret;
        }
        ret.put("ret",willService.getFilenameByName(name));
        return ret;
    }

    @PostMapping("/api/getFilenameByNameAndPwd")
    public JSONObject search(@RequestBody JSONObject data){
        JSONObject ret = new JSONObject();
        String name = data.getString("name");
        String pwd = data.getString("pwd");
        ret.put("ret",  willService.search(name, pwd));
        return ret;
    }

    @PostMapping("/api/getName")
    public JSONObject name(HttpServletRequest request){
        JSONObject ret = new JSONObject();
        ret.put("ret",request.getSession().getAttribute("user"));
        return ret;
    }
}


