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

    public String singIn(String name, String pwd, String type){
        if(userRepo.findAllByName(name).size() == 0){
            return "user not exist";
        }
        else{

            User temp = userRepo.findAllByName(name).get(0);
            if (!temp.getPwd().equals(pwd)){
                return "username or password is incorrect";

            }else if (!temp.getType().equals(type)){
                return "username or type is incorrect";
            }
        }
        return "succeed";
    }

    public String getTypeByName(String name){
        if(userRepo.findAllByName(name).size() == 0){
            return "user not exist";
        }else{
            return userRepo.findAllByName(name).get(0).getType();
        }
    }

    public Long getIdByName(String name){
        return userRepo.findByName(name).getId();
    }

}
