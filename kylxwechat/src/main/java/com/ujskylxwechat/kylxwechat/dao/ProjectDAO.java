package com.ujskylxwechat.kylxwechat.dao;

import com.ujskylxwechat.kylxwechat.dataobject.InviteeDO;
import com.ujskylxwechat.kylxwechat.dataobject.ProjectDO;
import com.ujskylxwechat.kylxwechat.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author peiqi
 * @Title:
 * @Package
 * @Description:
 * @date 2021/11/1822:31
 */
@Mapper
public interface ProjectDAO {
     int create(ProjectDO projectDO);

     int createinvitee(InviteeDO inviteeDO);

     List<ProjectDO> searchByLeaderId(@Param("leaderId") String leaderId);

     int insertInvitee1Id(String inviteeId,String inviteeName,String projectId);
     int insertInvitee2Id(String inviteeId,String inviteeName,String projectId);
     int insertInvitee3Id(String inviteeId,String inviteeName,String projectId);

     ProjectDO searchByProjectName(@Param("projectName") String projectName);

     InviteeDO searchVisiteebyid(@Param("projectId") Long projectId);

}
