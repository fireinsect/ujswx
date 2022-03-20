package com.ujskylxwechat.kylxwechat.dao;

import com.ujskylxwechat.kylxwechat.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDAO {
    int insert(UserDO userDO);
    List<UserDO> selectByOpenid(@Param("openid")String openid);

    /**
     * 找到此学号的同学
     * @param studentId
     * @return
     */
    List<UserDO> selectByStudentId(@Param("studentId") String studentId);


    int updateProjectIdForUser(UserDO userDO);

    int userUpdate(UserDO userDO);
}
