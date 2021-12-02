package com.ujskylxwechat.kylxwechat.controller;

import com.ujskylxwechat.kylxwechat.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class FileuploadController {

//    final String url="https://threepigs.top";
//    final String folderpath="/uploadfile";

    @Autowired
    UserDAO userDAO;

    @PostMapping("fileupload")
    @ResponseBody
    public String fileupload(MultipartFile file, Model model,String openid){
        System.out.println(openid);
        try {
//            file.transferTo(new File(url+folderpath+"/"+file.getOriginalFilename()));
            System.out.println(file.getContentType());
            String fileName=file.getOriginalFilename();
            String subname="";
            if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0){
                subname= fileName.substring(fileName.lastIndexOf(".")+1);
            }
            File check=new File("E:/temp/"+"az"+"."+subname);
                if(check.exists()){
                    check.delete();
                    System.out.println("success");
                }
              file.transferTo(check);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("msg","失败");
        }
        model.addAttribute("msg","成功");
        return "success";
    }
}
