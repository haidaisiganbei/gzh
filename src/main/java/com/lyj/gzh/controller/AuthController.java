package com.lyj.gzh.controller;


import com.lyj.gzh.entity.AuthEntity;
import com.lyj.gzh.entity.ReceiveXmlModel;
import com.lyj.gzh.service.AuthSerivce;
import com.lyj.gzh.utils.MyEncryption;
import com.lyj.gzh.utils.ReceiveXmlProcess;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Value("${wx.token}")
    private String token;
    @Autowired
    private AuthSerivce authSerivce;

    /**
     * 验证消息是否来自微信服务器
     * @param authEntity
     * @param request
     * @return
     */
    @GetMapping("ws")
    public String verifyService(AuthEntity authEntity, HttpServletRequest request){
        String[] arr = new String[]{token,authEntity.getTimestamp(),authEntity.getNonce()};
        Arrays.sort(arr);
        String str = StringUtils.join(arr, "");
        String sha1 = MyEncryption.getSha1(str);
        if(authEntity.getSignature().equals(sha1)){
            return authEntity.getEchostr();
        }
        return null;
    }

    /**
     * 接受用户的消息和事件推送
     * @param request
     */
    @PostMapping("ws")
    public void getMessage( HttpServletRequest request){
        String read = ReadAsChars(request);
        ReceiveXmlModel model = ReceiveXmlProcess.getMsgEntity(read);
        System.out.println(model);
    }

    @GetMapping("getip")
    public void getWeixnIP(){
        ArrayList ipList = authSerivce.getWeixinIp();

        for (Object ip:ipList ) {
            log.info(ip.toString());
        }
    }

    /**
     * 获取request中body的内容
     * @param request
     * @return
     */
    public static String ReadAsChars(HttpServletRequest request)
    {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try
        {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null)
            {
                sb.append(str);
            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != br)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
