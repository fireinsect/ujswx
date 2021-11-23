package com.ujskylxwechat.kylxwechat.dao;

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
     List<ProjectDO> searchByLeaderId(@Param("leaderId") String leaderId);
}
