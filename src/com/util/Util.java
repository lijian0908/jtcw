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
				"yyyy年MM月dd日");
		return sdf.format(d);
	}

	/**
	 * 将字符串截短，取前n个字符，英文算半个字符。
	 * 
	 * @param orignalString
	 *            原字符串
	 * @param length
	 *            长度
	 * @param chopedString
	 *            超过部分的表示字符串
	 * @return 截取的字符串
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

		 //获得初始化的AlipayClient
		 AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

		 //设置请求参数
		 AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		 alipayRequest.setReturnUrl(AlipayConfig.return_url);
		 alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		 //商户订单号，商户网站订单系统中唯一订单号，必填
		 String out_trade_no = new String(WIDout_trade_no.getBytes("ISO-8859-1"),"UTF-8");
		 //付款金额，必填
		 String total_amount = new String(WIDtotal_amount.getBytes("ISO-8859-1"),"UTF-8");
		 //订单名称，必填
		 String subject = new String(WIDsubject.getBytes("ISO-8859-1"),"UTF-8");
		 //商品描述，可空
		 String body = new String(WIDbody.getBytes("ISO-8859-1"),"UTF-8");

		 alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
				 + "\"total_amount\":\""+ total_amount +"\","
				 + "\"subject\":\""+ subject +"\","
				 + "\"body\":\""+ body +"\","
				 + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		 //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		 //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
		 //		+ "\"total_amount\":\""+ total_amount +"\","
		 //		+ "\"subject\":\""+ subject +"\","
		 //		+ "\"body\":\""+ body +"\","
		 //		+ "\"timeout_express\":\"10m\","
		 //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		 //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

		 //请求
		 String result = alipayClient.pageExecute(alipayRequest).getBody();

		 return result;
	 }
}
