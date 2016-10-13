package com.teboz.biz.web.constant;
/**
 * 短信常量
 * @author Administrator
 *
 */

public final class SmsConstant {
	
	//聚合网生成的短信API key
	public static final String  APPKEY ="56378b2a97265b44f04b9965abe1416d";  
	
	//短信模板id
	public static final Integer CHECK_CODE_TPL_ID = 20008;
	
	//数据类型
	public static final String DATA_TYPE = "json";
	
	//发送短信API 链接
    public static final String SEND_SMS_API_URL = "http://v.juhe.cn/sms/send";
    
    //检测屏蔽字 链接
    public static final String CHECK_BLOCK_WORD_URL = "http://v.juhe.cn/sms/black";//请求接口地址


}
