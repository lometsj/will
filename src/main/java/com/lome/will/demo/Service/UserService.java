package com.lome.will.demo.Service;

import com.lome.will.demo.Entity.User;
import com.lome.will.demo.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public String signUp(String name, String pwd, String pwd2, String email, String type){
        if(name == null || pwd == null || pwd2 == null || email == null || type == null){
            return "cannot be blank";
        }

        if(userRepo.findAllByName(name).size() != 0){
            return "user exist";
        }

        if(!pwd.equals(pwd2)){
            return "passwords aren't be same";
        }

        if(!type.equals("testator") && !type.equals(("eneficiary")) && !type.equals("notary")){
            return "type error";
        }

        User temp  = new User(name, pwd, email, type);
        userRepo.save(temp);
        return "succeed";
    }

    public User getUserByName(String name){
        return userRepo.findAllByName(name).get(0);
    }


}
