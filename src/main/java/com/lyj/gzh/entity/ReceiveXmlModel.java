package com.lyj.gzh.entity;

import lombok.Data;

@Data
public class ReceiveXmlModel {
    private String ToUserName="";
    private String FromUserName="";
    private String CreateTime="";
    private String MsgType="";
    private String MsgId="";
    private String Event="";
    private String EventKey="";
    private String Ticket="";
    private String Latitude="";
    private String Longitude="";
    private String Precision="";
    private String PicUrl="";
    private String MediaId="";
    private String Title="";
    private String Description="";
    private String Url="";
    private String Location_X="";
    private String Location_Y="";
    private String Scale="";
    private String Label="";
    private String Content="";
    private String Format="";
    private String Recognition="";
}
