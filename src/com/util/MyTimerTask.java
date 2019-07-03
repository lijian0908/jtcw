package com.util;

import com.aliPay.AlipayConfig;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.dao.FinancialEntityDAO;
import com.dao.OrdersEntityDAO;
import com.dao.TTouziDAO;
import com.model.FinancialEntity;
import com.model.OrdersEntity;
import com.model.TTouzi;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName MyTimerTask
 * @Author lijian
 * @Date 2019/7/3
 * @Time 3:59 PM
 * @Version 1.0
 */
public class MyTimerTask {
    Timer timer = new Timer();
    private OrdersEntityDAO ordersEntityDAO;

    private TTouziDAO touziDAO;

    private FinancialEntityDAO financialEntityDAO;

    public void setFinancialEntityDAO(FinancialEntityDAO financialEntityDAO) {
        this.financialEntityDAO = financialEntityDAO;
    }

    public void setTouziDAO(TTouziDAO touziDAO) {
        this.touziDAO = touziDAO;
    }

    public void setOrdersEntityDAO(OrdersEntityDAO ordersEntityDAO) {
        this.ordersEntityDAO = ordersEntityDAO;
    }

    public void init(){
        long start = System.currentTimeMillis();
        System.out.println("timer run:"+start);
        run();
        System.out.println("timer done:"+(System.currentTimeMillis() - start));
    }

    public void run(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                List<OrdersEntity> list = ordersEntityDAO.findIfPay();
                for (OrdersEntity ordersEntity:list){
                    try {
                        ifPay(ordersEntity);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (AlipayApiException e) {
                        e.printStackTrace();
                    }
                }

            }
        },0,1000*60);
    }


    public String ifPay(OrdersEntity ordersEntity) throws UnsupportedEncodingException, AlipayApiException {
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
            ordersEntity.setOrderStatus("20");
            ordersEntity.setPaidTime(new Date(System.currentTimeMillis()));
            ordersEntity.setPaidAmount(jsonObject.getJSONObject("alipay_trade_query_response").getString("total_amount"));
            ordersEntityDAO.saveOrUpdate(ordersEntity);
            addTouZi(ordersEntity);
        }else {
            System.out.println("erro");
        }

        return ActionSupport.SUCCESS;
    }

    public void addTouZi(OrdersEntity ordersEntity){
        FinancialEntity financialEntity = financialEntityDAO.findById(Long.valueOf(ordersEntity.getProductId()));
        TTouzi tTouzi = new TTouzi();
        tTouzi.setBenjin(Double.valueOf(ordersEntity.getPaidAmount()));
        tTouzi.setXangmu(financialEntity.getFinancialName());
        tTouzi.setKaishi(Util.getDate());
        tTouzi.setShouyi(0D);
        tTouzi.setUserId(Integer.valueOf(ordersEntity.getUserId()));
        touziDAO.save(tTouzi);
    }
}
