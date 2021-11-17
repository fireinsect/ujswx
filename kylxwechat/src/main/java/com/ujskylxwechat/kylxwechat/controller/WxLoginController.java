package com.ujskylxwechat.kylxwechat.controller;

import com.ujskylxwechat.kylxwechat.pojo.UserInfo;
import com.ujskylxwechat.kylxwechat.util.WxHttpUtil;
import com.ujskylxwechat.kylxwechat.util.WxLoginUtil;
import org.springframework.stereotype.Controller;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author peiqi
 * @Title:
 * @Package
 * @Description:
 * @date 2021/11/1715:56
 */
@Controller
@RequestMapping("/wx")
public class WxLoginController {
    @PostMapping("/login")
    @ResponseBody
    public String Login(@RequestParam("code") String code,
                        @RequestBody UserInfo userInfo)throws Exception{
        if (StringUtils.isBlank(code)){
            return "参数不能为空";
        }

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("appid", WxLoginUtil.APPID);
        paramsMap.put("secret", WxLoginUtil.APP_SECRET);
        paramsMap.put("js_code", code);
        paramsMap.put("grant_type", "authorization_code");

        //获取用户的信息
        Map<String, String> resultMap = new WxHttpUtil().getMessage(WxLoginUtil.AUTH_URL, paramsMap);
        String openId = resultMap.get("openid");
        String session_key = resultMap.get("session_key");

        //将获取openID和sessionKey放入userInfo类中
        userInfo.setOpenId(openId);
        userInfo.setSessionKey(session_key);
        System.out.println(resultMap);

        System.out.println(userInfo.toString());
        return "Success";
    }
}
