package com.ujskylxwechat.kylxwechat.dao;

import com.ujskylxwechat.kylxwechat.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
    int insert(UserDO userDO);
}
