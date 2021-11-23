package com.ujskylxwechat.kylxwechat.controller;

import com.ujskylxwechat.kylxwechat.dao.UserDAO;
import com.ujskylxwechat.kylxwechat.dataobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;


    @PostMapping("/adduser")
    @ResponseBody
    public String adduser(@RequestBody UserDO userDO){
        int re=userDAO.insert(userDO);
        if(re==1){
            return "success";
        }else{
            return "fail";
        }
    }

    @PostMapping("selectbyopenid")
    @ResponseBody
    public String selectByOpenid(String openid){
        System.out.println("openid:"+openid);
        List<UserDO> list= userDAO.selectByOpenid(openid);
        int len=list.size();
        if(len==1){
            return list.get(0).getStudentId();
        }else{
            return "fail";
        }
    }
}
