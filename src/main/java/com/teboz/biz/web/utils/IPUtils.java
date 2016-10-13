package com.teboz.biz.web.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONObject;

/**
 * 根据IP地址获取详细的地域信息
 * 
 */
public class IPUtils {
	
	public static final String TAOBAO_API_URL = "http://ip.taobao.com/service/getIpInfo.php";
	
	public static final String QQ_API_URL = "http://ip.qq.com/cgi-bin/searchip?searchip1=";
	
	public static final String SINA_API_URL  = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip=";

	/**
	 * 
	 * @param content
	 *            请求的参数 格式为：name=xxx&pwd=xxx
	 * @param encoding
	 *            服务器端请求编码。如GBK,UTF-8等
	 * @return
	 * @throws IOException 
	 * @throws UnsupportedEncodingException
	 */
	
	public static String getAddresses(String content, String encodingString,String ip) throws IOException{
		String address = "";
		address = getIpInfoBySina(ip);
		if(StringUtils.isBlank(address)){
			String returnStr = TebozHttpUtils.getResult(TAOBAO_API_URL, content, encodingString);
			if (StringUtils.isNotBlank(returnStr)) {
				// 处理返回的省市区信息
				JSONObject jsonObject = JSONObject.fromObject(returnStr);
				Integer code = (Integer) jsonObject.get("code");
				if(code == 0){
					JSONObject addrData = (JSONObject) jsonObject.get("data");
					address += addrData.get("country");
					address += addrData.get("region")+"-";
					address += addrData.get("city")+"-";
				}
			}
		}
		if(StringUtils.isBlank(address)){
			address = getByQQ(ip);
		}
		if(address.length()>50){
			address = "";
		}
		return address;
	}


	
	
	public static String getByQQ(String ip) throws IOException{//qq开放平台获取地址
		    //String strIP = "116.25.163.95";
		    URL url = new URL( QQ_API_URL + ip); 
		    URLConnection conn = url.openConnection(); 
		    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK")); 
		    String line = null; 
		    StringBuffer result = new StringBuffer(); 
		    while((line = reader.readLine()) != null){
		      result.append(line); 
		    } 
		    reader.close(); 
		    //System.out.println(result);
		    if(result.toString().contains("该IP所在地为：")){
	    	    String strIP = result.substring(result.indexOf( "该IP所在地为：" ));
			    strIP = strIP.substring(strIP.indexOf( "：") + 1);
			    String province = strIP.substring(6, strIP.indexOf("省"));
			    String city = strIP.substring(strIP.indexOf("省") + 1, strIP.indexOf("市"));
			    
			    return province+"-"+city+"-";
		    }
		    return "";
		   
	}
	
	public static String getIpInfoBySina(String ip) throws IOException{//新浪根据ip获取地址
	        String  ipAddress="";
	        if(StringUtils.isBlank(ip)){
	            return null;
	        }
	        URL url = new URL(SINA_API_URL+ip);//这个是从属性文件中获取url，即新浪接口地址
		    URLConnection conn = url.openConnection(); 
		    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); 
		    String line = null; 
		    StringBuffer result = new StringBuffer(); 
		    while((line = reader.readLine()) != null){
		      result.append(line); 
		    }
	        if(StringUtils.isNotBlank(result) &&result.indexOf("{")>0 &&result.lastIndexOf("}")>0){
	        	String jsonStr = result.substring(result.indexOf("{"), result.lastIndexOf("}"))+"}";
				JSONObject jsonObject = JSONObject.fromObject(jsonStr);
				ipAddress += jsonObject.get("country");
				ipAddress += jsonObject.get("province")+"省"+"-";
				ipAddress += jsonObject.get("city")+"市"+"-";
	        }
	        return ipAddress;
	    }
}