package com.util;

import com.aliPay.AlipayConfig;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class Util {
	public static Date newDate(String s) throws ParseException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		Date date = new Date();
		date = sdf.parse(s);
		return date;
	}

	public static Date newDate1(String s) throws ParseException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm");
		Date date = new Date();
		date = sdf.parse(s);
		return date;
	}

	public static Date FormatFullDate(String s) throws ParseException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		date = sdf.parse(s);
		return date;

	}

	public static String splitDate(Date d) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		return sdf.format(d);
	}

	public static String splitDate1(Date d) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy��MM��dd��");
		return sdf.format(d);
	}

	/**
	 * ���ַ����ض̣�ȡǰn���ַ���Ӣ�������ַ���
	 * 
	 * @param orignalString
	 *            ԭ�ַ���
	 * @param length
	 *            ����
	 * @param chopedString
	 *            �������ֵı�ʾ�ַ���
	 * @return ��ȡ���ַ���
	 */
	public static String chop(String orignalString, double length,
			String chopedString) {
		if (orignalString == null || orignalString.length() == 0) {
			return orignalString;
		}
		orignalString = orignalString.replaceAll(" ", " ");
		if (orignalString.length() < length) {
			return orignalString;
		}
		StringBuffer buffer = new StringBuffer((int) length);
		length = length * 2;
		int count = 0;
		int stringLength = orignalString.length();
		int i = 0;
		for (; count < length && i < stringLength; i++) {
			char c = orignalString.charAt(i);
			if (c < '\u00ff') {
				count++;
			} else {
				count += 2;
			}
			buffer.append(c);
		}
		if (i < stringLength) {
			buffer.append(chopedString);
		}
		return buffer.toString();
	}

	public static long getPrimeKey() {
		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.getTimeInMillis();
	}

	public static long stringToLong(String source) {
		return Long.parseLong(source);
	}
	/*
	 * public static void main(String[] args){ for(int i=0;i<10;i++){
	 * System.out.println(Util.getPrimeKey()); } }
	 */

	public static String getSysYear() {
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		return year;
	}

	public static String getOrderIdByTime() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		String newDate=sdf.format(new Date());
		String result="";
		Random random=new Random();
		for(int i=0;i<3;i++){
	 		result+=random.nextInt(10);
		}
		 return newDate+result;
	 }


	 public static String pay(String WIDout_trade_no,String WIDtotal_amount,String WIDsubject,String WIDbody) throws AlipayApiException, UnsupportedEncodingException {

		 //��ó�ʼ����AlipayClient
		 AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

		 //�����������
		 AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		 alipayRequest.setReturnUrl(AlipayConfig.return_url);
		 alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		 //�̻������ţ��̻���վ����ϵͳ��Ψһ�����ţ�����
		 String out_trade_no = new String(WIDout_trade_no.getBytes("ISO-8859-1"),"UTF-8");
		 //���������
		 String total_amount = new String(WIDtotal_amount.getBytes("ISO-8859-1"),"UTF-8");
		 //�������ƣ�����
		 String subject = new String(WIDsubject.getBytes("ISO-8859-1"),"UTF-8");
		 //��Ʒ�������ɿ�
		 String body = new String(WIDbody.getBytes("ISO-8859-1"),"UTF-8");

		 alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
				 + "\"total_amount\":\""+ total_amount +"\","
				 + "\"subject\":\""+ subject +"\","
				 + "\"body\":\""+ body +"\","
				 + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		 //�����BizContent����������ѡ����������������Զ��峬ʱʱ�����timeout_express������˵��
		 //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
		 //		+ "\"total_amount\":\""+ total_amount +"\","
		 //		+ "\"subject\":\""+ subject +"\","
		 //		+ "\"body\":\""+ body +"\","
		 //		+ "\"timeout_express\":\"10m\","
		 //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		 //��������ɲ��ġ�������վ֧����API�ĵ�-alipay.trade.page.pay-����������½�

		 //����
		 String result = alipayClient.pageExecute(alipayRequest).getBody();

		 return result;
	 }
}
