package com.lome.will.demo.Controller;

import com.lome.will.demo.Entity.User;
import com.lome.will.demo.Entity.Will;
import com.lome.will.demo.Service.UserService;
import com.lome.will.demo.Service.WillService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;


@Controller
public class webController {

    @Autowired
    UserService userService;

    @Autowired
    WillService willService;

    private String UPLOAD_FOLDER = "/tmp/";


    @GetMapping("/")
    public String index(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception{
        HttpSession session = httpServletRequest.getSession();
        String userName = (String )session.getAttribute("user");

        if(userName == null){
            return "/sign.html";
        }
        System.out.println(userName);
        User user = userService.getUserByName(userName);
        if(user.getType().equals("testator")){
            response.sendRedirect("/create");
        }
        else if(user.getType().equals("eneficiary") || user.getType().equals("notary")){
            response.sendRedirect("/view");
        }
        return "/sign.html";
    }

    @GetMapping("/view")
    public String view(HttpServletRequest request, HttpServletResponse response) throws Exception{
        if(request.getSession().getAttribute("user") == null)
            response.sendRedirect("/");
        return "/willView.html";
    }

    @GetMapping("/create")
    public String create(HttpServletRequest request, HttpServletResponse response) throws Exception{
        if(request.getSession().getAttribute("user") == null)
            response.sendRedirect("/");
        return "/willCreate.html";
    }

    @GetMapping("/checkFile/{filename}")
    public void check(HttpServletResponse response, HttpServletRequest request, @PathVariable("filename")String filename) throws Exception{
        readPdf(response, UPLOAD_FOLDER + filename);
    }

    @GetMapping("/deleteFile")
    public void delete(HttpServletResponse response, HttpServletRequest request) throws Exception{
        String name = (String )request.getSession().getAttribute("user");
        if(name == null) {
            response.sendRedirect("/");
        }
        willService.deleteByName(name);
        response.sendRedirect("/");
    }

    private void readPdf(HttpServletResponse response,String path){
        response.reset();
        response.setContentType("application/pdf");
        File file = new File(path);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] data = new byte[fileInputStream.available()];
            fileInputStream.read(data);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(data);
            response.setHeader("Content-Disposition",
                    "inline; filename= file");
            outputStream.flush();
        }catch (Exception e){
            System.out.print(e);
        }
    }

    @GetMapping("/logOut")
    public void logOut(HttpServletResponse response,HttpServletRequest request) throws Exception{
        request.getSession().removeAttribute("user");
        response.sendRedirect("/");
    }
}
