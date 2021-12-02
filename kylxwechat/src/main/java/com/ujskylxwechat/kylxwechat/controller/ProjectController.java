package com.ujskylxwechat.kylxwechat.controller;

import com.ujskylxwechat.kylxwechat.dao.ProjectDAO;
import com.ujskylxwechat.kylxwechat.dao.UserDAO;
import com.ujskylxwechat.kylxwechat.dataobject.ProjectDO;
import com.ujskylxwechat.kylxwechat.dataobject.UserDO;
import com.ujskylxwechat.kylxwechat.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
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
    public String projectcreate(@RequestBody ProjectDO projectDO){
        System.out.println(projectDO);
        int get=projectDAO.create(projectDO);
        //找到学生id为当前id的学生把项目id插入
        List<UserDO> users = userDAO.selectByStudentId(projectDO.getLeaderId());
        UserDO userDO = users.get(0);
        System.out.println(userDO.toString());
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
        int res;
        System.out.println(projectDO.toString());
        if (projectDO.getInvitee1Id()==null){
           res  = projectDAO.insertInvitee1Id(inviteeId,leaderId);
        }else if (projectDO.getInvitee2Id()==null&&projectDO.getInvitee1Id()!=null){
            res  = projectDAO.insertInvitee2Id(inviteeId,leaderId);
        }else if (projectDO.getInvitee3Id()==null&&projectDO.getInvitee1Id()!=null&&projectDO.getInvitee2Id()!=null){
            res  = projectDAO.insertInvitee3Id(inviteeId,leaderId);
        }else{
            return "fail";
        }

        SqlSession session = SqlSessionUtil.getSqlSession();
        System.out.println(res);
        System.out.println(projectDO.toString());
        session.commit();
        if(res!=0){
            return "success";
        }else{
            return "fail";
        }


    }

    @PostMapping("searchbyleaderid")
    @ResponseBody
    public int searchbyleaderid(@RequestParam("leaderId")String leaderId){
        System.out.println(1);
        System.out.println(leaderId);
        System.out.println(projectDAO.searchByLeaderId(leaderId).size());
        if(projectDAO.searchByLeaderId(leaderId).size()!=0){
            return 1;
        }else{
            return 0;
        }
    }

}

