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
        System.out.println(projectDO);
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
    public String invite(@RequestParam("leaderId") String leaderId,@RequestParam("inviteeId") String inviteeId){
        List<ProjectDO> projects = projectDAO.searchByLeaderId(leaderId);
        //拿到第一个项目
        ProjectDO projectDO = projects.get(0);
        System.out.println(projectDO.toString());
        if (projectDO.getInvitee1Id()==null){
            projectDO.setInvitee1Id(inviteeId);
        }else if (projectDO.getInvitee2Id()==null&&projectDO.getInvitee1Id()!=null){
            projectDO.setInvitee2Id(inviteeId);
        }else if (projectDO.getInvitee3Id()==null&&projectDO.getInvitee1Id()!=null&&projectDO.getInvitee2Id()!=null){
            projectDO.setInvitee3Id(inviteeId);
        }else{
            return "fail";
        }

        int res = projectDAO.insertInviteeId(projectDO);
        System.out.println(projectDO.toString());
        System.out.println(res);
        if(res==1){
            return "success";
        }else{
            return "fail";
        }

//        System.out.println(leaderId);
//        System.out.println(inviteeId);
//        return "fail";

    }
}
