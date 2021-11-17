package com.ujskylxwechat.kylxwechat.pojo;

import com.ujskylxwechat.kylxwechat.dataobject.UserDO;
import lombok.Data;

/**
 * @author peiqi
 * @Title:
 * @Package
 * @Description:
 * @date 2021/11/1715:53
 */
@Data
public class UserInfo {
    private String nickName;

    private String gender;

    private String language;

    private String city;

    private String province;

    private String country;

    private String avatarUrl;

    private UserDO userDO;

    private String openId;

    private String sessionKey;
}
