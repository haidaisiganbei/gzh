package com.lyj.gzh.entity;


import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class Menu {

    private List<Menu> sub_button ;

    private String type;

    private String name;

    private String key;

    private String url;

    private String mediaId;

    private String appid;

    private String pagepath;

}
