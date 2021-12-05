package com.ujskylxwechat.kylxwechat.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ujskylxwechat.kylxwechat.dao.ProjectDAO;
import com.ujskylxwechat.kylxwechat.dao.UserDAO;
import com.ujskylxwechat.kylxwechat.dataobject.ProjectDO;
import com.ujskylxwechat.kylxwechat.dataobject.UserDO;
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
        //找到学生id为当前id的学生把项目id插入
        List<UserDO> users = userDAO.selectByStudentId(projectDO.getLeaderId());
        UserDO userDO = users.get(0);
//        System.out.println(userDO.toString());
        userDO.setProjectId(projectDO.getSign());
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
    public String invite(@RequestParam("projectName") String projectName,@RequestParam("inviteeId") String inviteeId){

       ProjectDO projectDO = projectDAO.searchByProjectName(projectName);
        int res;
//        System.out.println(projectDO.toString());
        if (projectDO.getInvitee1Id()==null){
           res  = projectDAO.insertInvitee1Id(inviteeId,projectName);
        }else if (projectDO.getInvitee2Id()==null&&projectDO.getInvitee1Id()!=null){
            res  = projectDAO.insertInvitee2Id(inviteeId,projectName);
        }else if (projectDO.getInvitee3Id()==null&&projectDO.getInvitee1Id()!=null&&projectDO.getInvitee2Id()!=null){
            res  = projectDAO.insertInvitee3Id(inviteeId,projectName);
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

    /**
     * 拿到主持人者id,去获取项目名称和参与者id
     * 返回的信息为
     */
    @PostMapping("getProjectMessage")
    @ResponseBody
    public JSONArray getProjectMessage(@RequestParam("leaderId")String leaderId){
        List<ProjectDO> projects = projectDAO.searchByLeaderId(leaderId);
        ArrayList<ProjectMessage> list = new ArrayList<>();
        for (ProjectDO projectDO : projects) {
            ProjectMessage projectMessage = new ProjectMessage();
            ArrayList<String> inviteeIds = new ArrayList<>();
            //存储项目名称
            projectMessage.setProjectName(projectDO.getTitle());
            projectMessage.setLeaderId(leaderId);

            if (!StringUtils.isEmpty(projectDO.getInvitee1Id())){
                inviteeIds.add(projectDO.getInvitee1Id());
            }
            if (!StringUtils.isEmpty(projectDO.getInvitee2Id())){
                inviteeIds.add(projectDO.getInvitee2Id());
            }
            if (!StringUtils.isEmpty(projectDO.getInvitee3Id())){
                inviteeIds.add(projectDO.getInvitee3Id());
            }
            //存储项目参与者id
            projectMessage.setInviteeIds(inviteeIds);

            list.add(projectMessage);
        }

//        JSONObject json = (JSONObject)JSON.toJSON(projectMessage);
        JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(list));
        return jsonArray;
    }

    @PostMapping("getProject")
    @ResponseBody
    public JSONArray getProject(@RequestParam("leaderId")String leaderId){
        ArrayList<ProjectInfo> list = new ArrayList<>();
        List<ProjectDO> projects = projectDAO.searchByLeaderId(leaderId);

        for (ProjectDO project : projects) {
            ProjectInfo projectInfo = new ProjectInfo();
            projectInfo.setProjectType(project.getType());
            projectInfo.setProjectClass(project.getClasss());
            projectInfo.setProjectName(project.getTitle());
            projectInfo.setTeacherDepart(project.getTeacherCollege());
            projectInfo.setTeacherName(project.getTeacherName());

            list.add(projectInfo);
        }
        JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(list));
        return jsonArray;
    }


}

