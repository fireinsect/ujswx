package com.ujskylxwechat.kylxwechat.controller;

import com.ujskylxwechat.kylxwechat.dao.UserDAO;
import com.ujskylxwechat.kylxwechat.dataobject.UserDO;
import com.ujskylxwechat.kylxwechat.pojo.Result;
import com.ujskylxwechat.kylxwechat.pojo.UserUpdateInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    public UserDO selectByOpenid(String openid){
        System.out.println("openid:"+openid);
        List<UserDO> list= userDAO.selectByOpenid(openid);
        int len=list.size();
        if(len==1){
            return list.get(0);
        }else{
            return null;
        }
    }
    @PostMapping("userupdate")
    @ResponseBody
    public Result userupdate(@RequestBody UserUpdateInfo student){
        UserDO userDO=new UserDO();
        userDO.setStudentId(student.getStudentId());
        userDO.setMobileNumber(student.getMobileNumber());
        userDO.setEmail(student.getEmail());
        userDO.setClasss(student.getClasss());
        userDO.setCollege(student.getCollege());
        userDO.setMajor(student.getMajor());
        int re=userDAO.userUpdate(userDO);
        Result result=new Result();
        if(re!=0){
            result.setStatus("200");
            result.setData(1);
            result.setMessage("更新成功");
            result.setSuccess(true);
        }else{
            result.setStatus("500");
            result.setData(0);
            result.setMessage("更新失败");
            result.setSuccess(false);
        }
        return result;
    }
}
