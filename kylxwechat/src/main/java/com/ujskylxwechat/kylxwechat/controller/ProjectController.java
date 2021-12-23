package com.ujskylxwechat.kylxwechat.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ujskylxwechat.kylxwechat.dao.ProjectDAO;
import com.ujskylxwechat.kylxwechat.dao.UserDAO;
import com.ujskylxwechat.kylxwechat.dataobject.InviteeDO;
import com.ujskylxwechat.kylxwechat.dataobject.ProjectDO;
import com.ujskylxwechat.kylxwechat.dataobject.ProjectFileDO;
import com.ujskylxwechat.kylxwechat.dataobject.UserDO;
import com.ujskylxwechat.kylxwechat.pojo.InviteeInfo;
import com.ujskylxwechat.kylxwechat.pojo.ProjectInfo;
import com.ujskylxwechat.kylxwechat.pojo.ProjectMessage;
import com.ujskylxwechat.kylxwechat.util.SqlSessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
        InviteeDO inviteeDO=new InviteeDO();
        inviteeDO.setProjectId(projectDO.getId());
        ProjectFileDO projectFileDO=new ProjectFileDO();
        projectFileDO.setProjectId(projectDO.getId());
        projectDAO.createinvitee(inviteeDO);
        projectDAO.createfileurl(projectFileDO);
        //找到学生id为当前id的学生把项目id插入
        List<UserDO> users = userDAO.selectByStudentId(projectDO.getLeaderId());
        UserDO userDO = users.get(0);
//        System.out.println(userDO.toString());
        userDO.setProjectId(projectDO.getId());
        int insert = userDAO.updateProjectIdForUser(userDO);
//        System.out.println(userDO.toString());
        if(get==1&&insert==1){
            return "success";
        }else{
            return "fail";
        }
    }

    @PostMapping("insertInviteeId")
    @ResponseBody
    public String invite(@RequestParam("projectName") String projectName,@RequestParam("projectId") String projectId,@RequestParam("inviteeId") String inviteeId,@RequestParam("inviteeName") String inviteeName){

       ProjectDO projectDO = projectDAO.searchByProjectName(projectName);
       InviteeDO inviteeDO=projectDAO.searchVisiteebyid(projectDO.getId());
        int res;
//        System.out.println(projectDO.toString());
        if (inviteeDO.getInvitee1Id()==null){
           res  = projectDAO.insertInvitee1Id(inviteeId,inviteeName,projectId);
        }else if (inviteeDO.getInvitee2Id()==null&&inviteeDO.getInvitee1Id()!=null){
            res  = projectDAO.insertInvitee2Id(inviteeId,inviteeName,projectId);
        }else if (inviteeDO.getInvitee3Id()==null&&inviteeDO.getInvitee1Id()!=null&&inviteeDO.getInvitee2Id()!=null){
            res  = projectDAO.insertInvitee3Id(inviteeId,inviteeName,projectId);
        }else{
            return "fail";
        }

        SqlSession session = SqlSessionUtil.getSqlSession();
//        System.out.println(res);
//        System.out.println(projectDO.toString());
        session.commit();
        if(res!=0){
            return "success";
        }else{
            return "fail";
        }
    }

    @PostMapping("getProjectName")
    @ResponseBody
    public JSONArray getProjectName(@RequestParam("leaderId")String leaderId){
        List<ProjectDO> projects = projectDAO.searchByLeaderId(leaderId);
        ArrayList<String> list = new ArrayList<>();
        for (ProjectDO project : projects) {
            list.add(project.getTitle());
        }
        JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(list));
        return jsonArray;
    }

    @PostMapping("getProjectId")
    @ResponseBody
    public JSONArray getProjectId(@RequestParam("leaderId")String leaderId) {
        List<ProjectDO> projects = projectDAO.searchByLeaderId(leaderId);
        ArrayList<Long> list = new ArrayList<>();
        for (ProjectDO project : projects) {
            list.add(project.getId());
        }
        JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(list));
        return jsonArray;
    }

    @PostMapping("searchbyleaderid")
    @ResponseBody
    public int searchbyleaderid(@RequestParam("leaderId")String leaderId){
        System.out.println(1);
        System.out.println("leaderId："+leaderId);
        System.out.println(projectDAO.searchByLeaderId(leaderId).size()+"123");
        if(projectDAO.searchByLeaderId(leaderId).size()!=0){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * 拿到主持人者id,去获取项目名称和参与者id
     * 返回的信息为
     */
    @PostMapping("getProjectMessage")
    @ResponseBody
    public JSONArray getProjectMessage(@RequestParam("leaderId")String leaderId){
        List<ProjectDO> projects = projectDAO.searchByLeaderId(leaderId);
        ArrayList<ProjectMessage> list = new ArrayList<>();
        for (ProjectDO projectDO:projects){
            InviteeDO inviteeDO=projectDAO.searchVisiteebyid(projectDO.getId());
            ProjectMessage projectMessage = new ProjectMessage();
            ArrayList<InviteeInfo> invitees = new ArrayList<>();
            //存储项目名称
            projectMessage.setProjectName(projectDO.getTitle());
            projectMessage.setLeaderId(leaderId);
            if (!StringUtils.isEmpty(inviteeDO.getInvitee1Id())){
                InviteeInfo inviteeInfo=new InviteeInfo();
                inviteeInfo.setInviteeId(inviteeDO.getInvitee1Id());
                inviteeInfo.setInviteeName(inviteeDO.getInvitee1Name());
                invitees.add(inviteeInfo);
            }
            if (!StringUtils.isEmpty(inviteeDO.getInvitee2Id())){
                InviteeInfo inviteeInfo=new InviteeInfo();
                inviteeInfo.setInviteeId(inviteeDO.getInvitee2Id());
                inviteeInfo.setInviteeName(inviteeDO.getInvitee2Name());
                invitees.add(inviteeInfo);
            }
            if (!StringUtils.isEmpty(inviteeDO.getInvitee3Id())){
                InviteeInfo inviteeInfo=new InviteeInfo();
                inviteeInfo.setInviteeId(inviteeDO.getInvitee2Id());
                inviteeInfo.setInviteeName(inviteeDO.getInvitee2Name());
                invitees.add(inviteeInfo);
            }
            //存储项目参与者id
            projectMessage.setInvitees(invitees);
            list.add(projectMessage);
        }
        JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(list));
        return jsonArray;
    }

    @PostMapping("getProject")
    @ResponseBody
    public JSONArray getProject(@RequestParam("leaderId")String leaderId){
        System.out.println(111);
        ArrayList<ProjectInfo> list = new ArrayList<>();
        List<ProjectDO> projects = projectDAO.searchByLeaderId(leaderId);

        for (ProjectDO project : projects) {
            ProjectFileDO projectFileDO=projectDAO.searchfilebyid(project.getId());
            ProjectInfo projectInfo = new ProjectInfo();
            projectInfo.setProjectId(project.getId());
            projectInfo.setProjectName(project.getTitle());
            projectInfo.setSign(project.getSign());
            projectInfo.setTeacherName(project.getTeacherName());
//            projectInfo.setFilePath(project.getFilePath());
            projectInfo.setTeacherDept(project.getTeacherCollege());
            projectInfo.setApplicationPath(projectFileDO.getApplicationUrl());
//            System.out.println(project.getFilePath());
            list.add(projectInfo);
        }
        JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(list));
        return jsonArray;
    }
    @PostMapping("/projectUpdate")
    @ResponseBody
    public String projectUpdate(ProjectDO projectDO){
        System.out.println(projectDO.toString());
        int a=projectDAO.projectupdate(projectDO);
        System.out.println(a);
        if(a==1){
            return "success";
        }else{
            return "fail";
        }

    }

}

