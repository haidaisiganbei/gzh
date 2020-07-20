package com.lyj.gzh.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyj.gzh.constant.WeiXin;
import com.lyj.gzh.mapper.AuthMapper;
import com.lyj.gzh.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Configuration
@EnableScheduling
@Slf4j
public class MyTask {

    @Value("${wx.APPID}")
    private String APPID;

    @Value("${wx.APPSECRET}")
    private String APPSECRET;

    @Autowired
    private AuthMapper authMapper;
    private RestTemplate restTemplate = new RestTemplate();

    /**
     * 获取access_token
     *
     * @return
     */
    @Scheduled(fixedRate= 90*60*1000)
    public void getAccessToken() {
        log.info("获取access_token");
        try {
            String str = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
            String url = str.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
            Map<String,String> res = restTemplate.getForObject(url, Map.class);
            String token = res.get("access_token");
            WeiXin.token = token;
            authMapper.setAccessToken(token);

            log.info("获取access_token成功");
            log.info(res.toString());
        } catch (Exception e) {
            log.info("获取access_token失败");
            e.printStackTrace();
        }
    }
}
