package com.ujskylxwechat.kylxwechat.controller;

import com.ujskylxwechat.kylxwechat.dao.ProjectDAO;
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

    final String rooturl="E:/temp";
    final String applicationpath="/applicationfile";
    final String overpath="/overfile";
    @Autowired
    UserDAO userDAO;

    @Autowired
    ProjectDAO projectDAO;
    @PostMapping("applicationupload")
    @ResponseBody
    public String fileupload(MultipartFile file, Model model,Long projectId){
        String userfolder="/"+projectId;
        String subname="";
        try {
            File folder=new File(rooturl+userfolder);
            if (!folder.exists()){
                folder.mkdir();
                File childfolder=new File(rooturl+userfolder+applicationpath);
                childfolder.mkdir();
                childfolder=new File(rooturl+userfolder+overpath);
                childfolder.mkdir();
            }
//            file.transferTo(new File(url+folderpath+"/"+file.getOriginalFilename()));
            String fileName=file.getOriginalFilename();


            if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0){
                subname= fileName.substring(fileName.lastIndexOf(".")+1);
            }
            File check=new File(rooturl+userfolder+applicationpath+"/"+projectId+"applicationfile."+subname);
                if(check.exists()){
                    check.delete();
                    System.out.println("success");
                }
              file.transferTo(check);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("msg","失败");
        }
        projectDAO.insertapplication(projectId+"applicationfile."+subname,projectId);
        model.addAttribute("msg","成功");
        System.out.println("文件上传");
        return "success";
    }
}
