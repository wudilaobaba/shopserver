package com.whj.shop.shopserver.sample.DataBase;

import com.whj.shop.shopserver.sample.ConnectTmpl;

public class MySql implements ConnectTmpl {
    private String ip;
    private Integer port;
    public MySql(String ip,Integer port){
        this.ip = ip;
        this.port = port;
    }
    public void connect(){
        System.out.println(this.ip+":"+this.port);
    }
}
