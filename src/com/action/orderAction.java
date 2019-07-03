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
            //����������δ����ڳ�������ʱʹ��
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //����SDK��֤ǩ��

        //�������������д���ĳ������´�������ο�������

	/* ʵ����֤���̽����̻�����������У�飺
	1����Ҫ��֤��֪ͨ�����е�out_trade_no�Ƿ�Ϊ�̻�ϵͳ�д����Ķ����ţ�
	2���ж�total_amount�Ƿ�ȷʵΪ�ö�����ʵ�ʽ����̻���������ʱ�Ľ���
	3��У��֪ͨ�е�seller_id������seller_email) �Ƿ�Ϊout_trade_no��ʵ��ݵĶ�Ӧ�Ĳ��������е�ʱ��һ���̻������ж��seller_id/seller_email��
	4����֤app_id�Ƿ�Ϊ���̻�����
	*/
        if(signVerified) {//��֤�ɹ�
            //�̻�������
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //֧�������׺�
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //����״̬
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            if(trade_status.equals("TRADE_FINISHED")){
                //�жϸñʶ����Ƿ����̻���վ���Ѿ���������
                //���û�������������ݶ����ţ�out_trade_no�����̻���վ�Ķ���ϵͳ�в鵽�ñʶ�������ϸ����ִ���̻���ҵ�����
                //���������������ִ���̻���ҵ�����

                //ע�⣺
                //�˿����ڳ������˿����޺��������¿��˿��֧����ϵͳ���͸ý���״̬֪ͨ
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //�жϸñʶ����Ƿ����̻���վ���Ѿ���������
                //���û�������������ݶ����ţ�out_trade_no�����̻���վ�Ķ���ϵͳ�в鵽�ñʶ�������ϸ����ִ���̻���ҵ�����
                //���������������ִ���̻���ҵ�����

                //ע�⣺
                //������ɺ�֧����ϵͳ���͸ý���״̬֪ͨ
            }
            System.out.println("trade_no:"+trade_no+" out_trade_no:"+out_trade_no+" trade_status:"+trade_status);
        }else {//��֤ʧ��
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
            //����������δ����ڳ�������ʱʹ��
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //����SDK��֤ǩ��

        //�������������д���ĳ������´�������ο�������
        if(signVerified) {
            //�̻�������
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //֧�������׺�
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //������
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            System.out.println("trade_no:"+trade_no+" out_trade_no:"+out_trade_no+" total_amount:"+total_amount);
        }else {
            return ActionSupport.ERROR;
        }

        return ActionSupport.SUCCESS;
    }

    public String ifPay() throws UnsupportedEncodingException, AlipayApiException {
        //��ó�ʼ����AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //�����������
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();

        //�̻������ţ��̻���վ����ϵͳ��Ψһ������
        String out_trade_no = new String(ordersEntity.getOrderNum().getBytes("ISO-8859-1"),"UTF-8");

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"}");

        //����
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
