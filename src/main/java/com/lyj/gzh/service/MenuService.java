package com.lyj.gzh.service;

import com.alibaba.fastjson.JSONObject;
import com.lyj.gzh.constant.WeiXin;
import com.lyj.gzh.entity.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Slf4j
public class MenuService {

    /**
     * 创建菜单
     *
     * @return
     */
    public Map<String, Object> createMenu() {
        String str = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
        String url = str.replace("ACCESS_TOKEN", WeiXin.token);
        RestTemplate client = new RestTemplate();
        JSONObject postData = new JSONObject();
//       菜单一
        Menu menu = new Menu();
        menu.setType("click");
        menu.setName("菜单一");
        menu.setKey("11111");
//        菜单一子菜单
        Menu menu11 = new Menu();
        menu11.setType("click");
        menu11.setName("子菜单一");
        menu11.setKey("11111011");
//        菜单一子菜单
        Menu menu12 = new Menu();
        menu12.setType("click");
        menu12.setName("子菜单二");
        menu12.setKey("11111022");
        List<Menu> subButton = new LinkedList<>();
        subButton.add(menu11);
        subButton.add(menu12);
        menu.setSub_button(subButton);
//        菜单二
        Menu menu1 = new Menu();
        menu1.setType("click");
        menu1.setName("菜单二");
        menu1.setKey("22222");
        ArrayList<Object> list = new ArrayList<>();
        list.add(menu);
        list.add(menu1);
        postData.put("button", list);
        System.out.println(postData);
        JSONObject json = client.postForEntity(url, postData, JSONObject.class).getBody();
        System.out.println(json);
        return json;
    }
}
