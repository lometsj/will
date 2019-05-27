package com.lome.will.demo.Service;

import com.lome.will.demo.Entity.Message;
import com.lome.will.demo.Repo.MessRepo;
import com.lome.will.demo.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessService {

    @Autowired
    private MessRepo messRepo;

    @Autowired
    private UserRepo userRepo;

    public List<String> getMessByName(String name){
        Long id = userRepo.findByName(name).getId();
        List<Message> t =  messRepo.findAllByUserId(id);
        List<String> ret = new ArrayList<>();
        for(Message i: t){
            ret.add(i.getContent());
        }
        return ret;
    }

    public void saveMess(Long userId, String message){
        Message t = new Message();
        t.setUserId(userId);
        t.setContent(message);
        messRepo.save(t);
    }
}
