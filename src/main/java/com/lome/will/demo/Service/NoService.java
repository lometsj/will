package com.lome.will.demo.Service;

import com.lome.will.demo.Entity.NoInfo;
import com.lome.will.demo.Entity.Office;
import com.lome.will.demo.Repo.NoInfoRepo;
import com.lome.will.demo.Repo.OfficeRepo;
import com.lome.will.demo.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoService {
    @Autowired
    private NoInfoRepo noInfoRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OfficeRepo officeRepo;

    public boolean hasInfo(String name){
        Long id = userRepo.findByName(name).getId();
        if(noInfoRepo.findByUserId(id) == null) return false;
        System.out.println("return true");
        return true;
    }

    public List<String> getAllOffice(){
        List<String> ret = new ArrayList<>();
        List<Office> t = officeRepo.findAll();
        for(Office i:t){
            ret.add(i.getName());
        }
        return ret;
    }

    public void saveInfo(String name, String office, String num){
        NoInfo t = new NoInfo();
        t.setWorkNum(num);
        t.setUserId(userRepo.findByName(name).getId());
        t.setOfficeId(officeRepo.findByName(office).getId());
        noInfoRepo.save(t);
    }


}
