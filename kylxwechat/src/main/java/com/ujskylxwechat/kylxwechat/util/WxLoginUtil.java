package com.ujskylxwechat.kylxwechat.util;

/**
 * @author peiqi
 * @Title:
 * @Package
 * @Description: 用于登录的工具类
 * @date 2021/11/1715:56
 */
public class WxLoginUtil {
    /**
     * 申请小程序的AppId
     */
    public static final String APPID = "wx82b2a932815899c3";
    /**
     * 生成的AppSecret
     */
    public static final String APP_SECRET = "af12c9a68c36f3c3578119c344b44efd";

    /**
     * 请求微信后端的地址
     */
    public static final String AUTH_URL = "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type={grant_type}";
}