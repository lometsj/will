package com.lome.will.demo.Controller;


import com.alibaba.fastjson.JSONObject;
import com.lome.will.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class apiController {

    @Autowired
    UserService userService;

    @PostMapping("/api/signUp")
    public JSONObject regist(@RequestBody JSONObject reg){
        JSONObject ret = new JSONObject();
        ret.put("ret",userService.signUp(reg.getString("name"), reg.getString("pwd"), reg.getString("pwd2"), reg.getString("email"), reg.getString("type")));
        return ret;
    }


}
