package com.lyj.gzh.controller;


import com.lyj.gzh.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/menu")
@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("create")
    public void createMenu(){
        menuService.createMenu();

    }


}
