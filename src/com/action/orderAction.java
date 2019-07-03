package com.action;

import com.aliPay.AlipayConfig;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.dao.FinancialEntityDAO;
import com.dao.OrdersEntityDAO;
import com.model.FinancialEntity;
import com.model.OrdersEntity;
import com.model.TUser;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName orderAction
 * @Author lijian
 * @Date 2019/6/30
 * @Time 1:29 PM
 * @Version 1.0
 */
public class orderAction {
    private OrdersEntity ordersEntity;

    private OrdersEntityDAO ordersEntityDAO;
    private FinancialEntityDAO financialEntityDAO;

    public FinancialEntityDAO getFinancialEntityDAO() {
        return financialEntityDAO;
    }

    public void setFinancialEntityDAO(FinancialEntityDAO financialEntityDAO) {
        this.financialEntityDAO = financialEntityDAO;
    }

    public OrdersEntityDAO getOrdersEntityDAO() {
        return ordersEntityDAO;
    }

    public void setOrdersEntityDAO(OrdersEntityDAO ordersEntityDAO) {
        this.ordersEntityDAO = ordersEntityDAO;
    }

    public OrdersEntity getOrdersEntity() {
        return ordersEntity;
    }

    public void setOrdersEntity(OrdersEntity ordersEntity) {
        this.ordersEntity = ordersEntity;
    }

    public String saveOrUpdate() throws UnsupportedEncodingException, AlipayApiException {
        HttpServletRequest req=ServletActionContext.getRequest();
        HttpSession sess=req.getSession();
        TUser user=(TUser)sess.getAttribute("user");
        FinancialEntity financialEntity1 = financialEntityDAO.findById(ordersEntity.getProduct().getId());
        ordersEntity.setProductId(String.valueOf(ordersEntity.getProduct().getId()));
        ordersEntity.setOrderStatus("10");
        long tempTime = new java.util.Date().getTime();
        ordersEntity.setCreateTime(new Date(tempTime));
        ordersEntity.setUserId(user.getUserId().toString());
        ordersEntityDAO.saveOrUpdate(ordersEntity);
        Map map = (Map) ServletActionContext.getContext().get("request");
        map.put("WIDout_trade_no",ordersEntity.getOrderNum());
        map.put("WIDtotal_amount",new BigDecimal(ordersEntity.getOrderAmount()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).toString());
        map.put("WIDsubject",financialEntity1.getFinancialName());
        map.put("WIDbody",financialEntity1.getFinancialDescribe());

        return ActionSupport.SUCCESS;
    }

    public String delete(){
        ordersEntityDAO.delete(ordersEntity);
        return ActionSupport.SUCCESS;
    }

    public String payNotify() throws UnsupportedEncodingException, AlipayApiException {

        Map<String,String> params = new HashMap<String,String>();
        HttpServletRequest request=ServletActionContext.getRequest();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——

	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/
        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }
            System.out.println("trade_no:"+trade_no+" out_trade_no:"+out_trade_no+" trade_status:"+trade_status);
        }else {//验证失败
            return ActionSupport.ERROR;
        }

        return ActionSupport.SUCCESS;
    }


    public String payReturn() throws UnsupportedEncodingException, AlipayApiException {
        Map<String,String> params = new HashMap<String,String>();
        HttpServletRequest request=ServletActionContext.getRequest();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            System.out.println("trade_no:"+trade_no+" out_trade_no:"+out_trade_no+" total_amount:"+total_amount);
        }else {
            return ActionSupport.ERROR;
        }

        return ActionSupport.SUCCESS;
    }

    public String ifPay() throws UnsupportedEncodingException, AlipayApiException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();

        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = new String(ordersEntity.getOrderNum().getBytes("ISO-8859-1"),"UTF-8");

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"}");

        //请求
        String result = alipayClient.execute(alipayRequest).getBody();
        JSONObject jsonObject = JSONObject.fromObject(result);
        if("10000".equals(jsonObject.getJSONObject("alipay_trade_query_response").getString("code"))){
            System.out.println("success");
        }else {
            System.out.println("erro");
        }

        return ActionSupport.SUCCESS;
    }
}
