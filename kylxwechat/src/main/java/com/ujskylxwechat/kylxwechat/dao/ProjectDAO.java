package com.ujskylxwechat.kylxwechat.dao;

import com.ujskylxwechat.kylxwechat.dataobject.ProjectDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author peiqi
 * @Title:
 * @Package
 * @Description:
 * @date 2021/11/1822:31
 */
@Mapper
public interface ProjectDAO {
    public int create(ProjectDO projectDO);
}
