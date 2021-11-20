package com.ujskylxwechat.kylxwechat.dao;

import com.ujskylxwechat.kylxwechat.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDAO {
    int insert(UserDO userDO);

    List<UserDO> selectbyopenid(@Param("openid")String openid);

    int updateprojectidforuser(@Param("openid")String openid,@Param("studentId")String studentId,@Param("projectId")String projectId);
}
