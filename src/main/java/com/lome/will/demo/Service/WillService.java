package com.lome.will.demo.Service;

import com.lome.will.demo.Entity.Will;
import com.lome.will.demo.Repo.WillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class WillService {

    @Autowired
    private WillRepo willRepo;

    private String UPLOAD_FOLDER = "/tmp/";

    private String genUUID(){
        return UUID.randomUUID().toString();
    }

    private String saveUploadedFile(MultipartFile file){
        String ret = null;
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + genUUID());
            Files.write(path, bytes);
            ret = path.toString();
        }catch (Exception e){
            System.out.print(e);
        }
        return ret;
    }

    public String upload(String name, MultipartFile file, String pwd){
        Will will = new Will();
        if(willRepo.findByUserName(name) != null){
            will = willRepo.findByUserName(name);
        }
        will.setCreatetime(new Timestamp(new Date().getTime()));
        will.setPdf(saveUploadedFile(file));
        will.setUserName(name);
        will.setPwd(pwd);
        System.out.print(will);
        willRepo.save(will);
        return "succeed";
    }

    public String getFilenameByName(String name){
        Will w = willRepo.findByUserName(name);
        if(w == null){
            return "no pdf yet";
        }
        Path p = Paths.get(w.getPdf());
        return p.getFileName().toString();
    }


    public String getPathByName(String name){
        Will w = willRepo.findByUserName(name);
        if(w == null){
            return "no pdf yet";
        }
        return w.getPdf();
    }

    public void deleteByName(String name){
        Will w = willRepo.findByUserName(name);
        if(w == null){
            return;
        }
        willRepo.delete(w);
    }

    public String search(String name, String pwd){
        Will w = willRepo.findByUserNameAndPwd(name, pwd);
        if(w == null){
            return "not found";
        }
        Path path = Paths.get(w.getPdf());
        return path.getFileName().toString();
    }
}
