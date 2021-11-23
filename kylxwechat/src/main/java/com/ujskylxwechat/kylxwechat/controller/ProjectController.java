package com.ujskylxwechat.kylxwechat.controller;

import com.ujskylxwechat.kylxwechat.dao.ProjectDAO;
import com.ujskylxwechat.kylxwechat.dao.UserDAO;
import com.ujskylxwechat.kylxwechat.dataobject.ProjectDO;
import com.ujskylxwechat.kylxwechat.dataobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    private ProjectDAO projectDAO;
    @Autowired
    private UserDAO userDAO;

    @PostMapping("projectcreate")
    @ResponseBody
    public String projectcreate(@RequestBody ProjectDO projectDO){//还未完成项目号传入学生表内
        int get=projectDAO.create(projectDO);
        //找到学生id为当前id的学生把项目id插入
        List<UserDO> users = userDAO.selectByStudentId(projectDO.getLeaderId());
        UserDO userDO = users.get(0);
        userDO.setProjectId(projectDO.getSign());
        int insert = userDAO.updateProjectIdForUser(userDO);
        System.out.println(userDO.toString());
        if(get==1&&insert==1){
            return "success";
        }else{
            return "fail";
        }
    }

    @PostMapping("insertInviteeId")
    @ResponseBody
    public String invite(@RequestParam String leaderId, @RequestParam String inviteeId){
        List<ProjectDO> projects = projectDAO.searchByLeaderId(leaderId);
        ProjectDO projectDO = projects.get(0);

        return "fail";
    }
}
