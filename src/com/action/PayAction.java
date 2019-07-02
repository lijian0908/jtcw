package com.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @ClassName PayAction
 * @Author lijian
 * @Date 2019/6/30
 * @Time 1:04 PM
 * @Version 1.0
 */
public class PayAction extends ActionSupport {



     public String goAliPay(){
        return ActionSupport.SUCCESS;
     }
}
