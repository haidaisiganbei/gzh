package com.lyj.gzh.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyj.gzh.constant.WeiXin;
import com.lyj.gzh.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


@Slf4j
@Service
public class AuthSerivce {

    private RestTemplate restTemplate = new RestTemplate();
    /**
     * 获得微信服务器IP列表
     * @return
     */
    public ArrayList getWeixinIp(){
        String str = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";
        String url = str.replace("ACCESS_TOKEN", WeiXin.token);
        Map<String, ArrayList> res = restTemplate.getForObject(url, Map.class);
        System.out.println(res);
        ArrayList ip_list = res.get("ip_list");
        return ip_list;
    }
}
