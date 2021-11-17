package com.ujskylxwechat.kylxwechat.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author peiqi
 * @Title:
 * @Package
 * @Description: 用于发送请求的工具类
 * @date 2021/11/1715:59
 */
@Slf4j
public class WxHttpUtil {
    public Map<String, String> getMessage(String url, Map<String, String> paramsMap)throws Exception{
        //发送http请求
        RestTemplate restTemplate = new RestTemplate();
        //拿到http请求的response的所有内容
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, paramsMap);

        //拿到body的内容
        String jsonResult = responseEntity.getBody();

        //使用jackson来完成类型转换
        ObjectMapper objectMapper = new ObjectMapper();
        //获取map类型是Map<String, String>
        MapType mapType = TypeFactory.defaultInstance().constructMapType(HashMap.class, String.class, String.class);
        //将json格式的字符串转换为Map
        Map<String, String> resultMap = null;

        try {
            resultMap = objectMapper.readValue(jsonResult, mapType);
        } catch (IOException e) {
            log.error("验证openId的http返回的body内容json转Map转换异常 jsonResult={}", jsonResult);
            throw new IOException("内容json转Map转换异常");
        }

        return resultMap;
    }
}
