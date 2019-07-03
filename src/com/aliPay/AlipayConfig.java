package com.aliPay;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101100658035";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCuzZo9TFARfVC+kUFrcsxxl/vQ0pro6cn/KE0DyvGCaohUjXHDQ+94E2YGvRrKARlhrYPRuHd7mkT5WH2bCOKRTEzh3h9c6cSrDUFLSSPsV7Z1tNCIx4Y/ciyBv/7g9++KHOCa4MGi6XIv2l/VQtaJT2xD7ArMJI4+xTKomDZqhgXC98Jv+EqCxK37doO5myIc5TjcaMDrUxHDRHWX8H3+QV7HJonpCUvDwLQjKDYetJ00ZZrS2VbN5GYyOoA+uj+bsm2dZEJzOESJwy0vG/+AfnbbTu5uHF7dpPnX0+3C91dVS4QSkvi4hNqzP5jaDqjUO16qN4382/LKMQ469VC/AgMBAAECggEBAIUkCu/XeUDo/yA3SOnLRZbb2SxDzUPMFlPnYUmbQPpBpFcqaNT+oJ4HwHUvggEMd0WjwLxzUWZuqUx2Ly1vxgo/YGvFIIRHdcmHTg1GxzrT0CC1OQSFXGZk0yt8jmjRz7yVpCmzOSmZv6RdIxMvVnK9cMwHNG4rBcEfuCcb0I6vBOXIIX28jlyKFwekineAUnewCGRZhmLwYUMG10Ir+v8ZD6M/eBYJcjB86u0aVe9h5bgCYhk0cNrWtSYOaD7UQ0AluxEGVXyOWmbWx9WMw10vQNagWosXnWvGrpHO/1ulRSXmTUxaBo2eBx8u+d9Wesp1SKfRSKM0oZuopf0IPdkCgYEA5MF65Ij3iH9zDNsuedMHrdQrWlDM89WMukEM47YFyTMrgyrH2X+miATOXdDXAW3UCN2ClhQFwLBjN1YkZ8zPe7vuS93vq2sWpfFLYlFDgcUE6ixiiLl3GgN5Sv7PNSUXqlYl70x4u5DX1LCFyRg9RtwRGYBh/hKD848mOi75t40CgYEAw58p7AWW0/JnGCz3ZwoWPzLkMxzRWBnuGNddgQZmW+PfBm/8t6fZLkvU2ztkM57Z9UDem4Kr4ZHToQwaCvs+6yLJIDsogs+Da6SPXrG8xZ/EdNyp5R27rtyZ3LmSlyJsDKMvZBc0AUlbYk6y+Za58ImJnnCneEp8kcLS0QXKoHsCgYAxnR0QT/yP6ESFt0v/NBvoQaodboTlJZ3eD5nCEYpztttrGW466AqwWYqAonjfYHiqa5CVCEavIhKCv/YpZSxjA8GNRtdicFl7KDhXXJStopOMTpAYY1FHnRVDhLxgJGlbkk+r9QQCekm1YYvCr7UqlzRGOagHOPDRdGywFdwnSQKBgQCSRbUnEpbRLHQCE716goRYBUm1Hls6sUjjskXscEihdSYSvjINcahngbRLqYEnSCRZyoOpbG/xXwULau0eQPevAne5bgLArUHrf0Tk3an1a52OdGFlPHJWMgWqkegWslpwqKSLBv6P57Y5L/eLwTSPs4f/vOS8gZXbt/3C5b25eQKBgEpPYND7s8Ey2XFA/4ZY37Mm/+osXNEU6KzC52i1jFOpLlaQTf4x/TJyr3NJlU5ImZaC5KuBHZ3tZUXG8gMPCCJgKiLJQlhy/lO/0cp/yYgLoZfY4E50PS+/9sn4P5HQhrrPVgEE0zbIxwCl1opyHq+wheQQn0JyfEIzxMrpVxCf";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgMsCyRKT2TRBx4ICTIg1/57m10uK0sVSv4FrKtq1obRAKN6Qfp58gOvzI3yz8UukuFf6YUu3vjZim1iZj+MLZMNm7fVBCm7/a5rvIL2zdiEDG/jPaaHrY3ZBrbYnCS5oWS/1ueJaYbyAr5BJiBmUwvq2YjfXDca6PN89rS/XcEswlvJfwsW+V7RtAqwGa9LmBjNQvLFlX1GT+6l5fXUVSfIddHtuZJXUJXdJyrR91gC/5AHkL/0axtgekAxHU2enBxYZBuJxgQPKpniGwVVzHqxYBArelteGJvF31UtJnZkYs2IRFe8XhW2Mf4jzZmEF0w1mTILHLnp+G9lEw0HTQwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://172.30.22.214:8081/notify_url.action";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://172.30.22.214:8081/return_url.action";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

