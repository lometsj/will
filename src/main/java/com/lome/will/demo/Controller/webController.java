package com.lome.will.demo.Controller;

import com.lome.will.demo.Entity.User;
import com.lome.will.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class webController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Object userName = session.getAttribute("user");
        if(userName == null){
            return "/index.html";
        }
        User user = userService.getUserByName((String) userName);
        String url = null;
        if(user.getType().equals("testator")){
            url = "/willCreate.html";
        }
        else if(user.getType().equals("eneficiary") || user.getType().equals("notary")){
            url =  "/willView.html";
        }
        return url;
    }
}
