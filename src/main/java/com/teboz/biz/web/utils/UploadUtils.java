package com.teboz.biz.web.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import net.sf.json.JSONObject;

public class UploadUtils {
	// 设置好账号的ACCESS_KEY和SECRET_KEY
	public static final String ACCESS_KEY = "L5zQVyb0aXQWly7MUFVCPtEKewMC1O4qeAM9XC2a";
	public static final String SECRET_KEY = "9VKsuWeD_BYe8S1O4LAUfvV3Z_quZih2vcCcTY2X";
	// 要上传的空间
	public static final String BUCKETNAME = "bogo-resource";
	
	public static final String BASE_URL="http://resource.mytbz.com/";
	
	public static final String REFRESH_URL="http://fusion.qiniuapi.com/v2/tune/refresh";

	// 密钥配置
	public static final Auth AUTH = Auth.create(ACCESS_KEY, SECRET_KEY);
	// 创建上传对象
	public static final UploadManager UPLOAD_MANAGER = new UploadManager();
	
	//文件管理对象
	public static final BucketManager BUCKET_MANAGER = new BucketManager(AUTH);


	// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public static String getUpToken(String bucketname) {
		return AUTH.uploadToken(bucketname);
	}
	
	// 覆盖上传，使用默认策略，只需要设置上传的空间名就可以了
	public static String getUpToken(String bucketname,String key) {
		return AUTH.uploadToken(bucketname,key);
	}
	//使用文件名
	public static String upload(String key,String FilePath,String bucketname,String baseUrl) throws IOException {
		// 调用put方法上传
		Response res = UPLOAD_MANAGER.put(FilePath, key, getUpToken(bucketname,key));
		String uploadKey = "";
		if(JSONObject.fromObject(res).getBoolean("OK")){
			uploadKey = baseUrl+JSONObject.fromObject(res.bodyString()).getString("key");
		}
		return uploadKey;
	}
	//使用byte数组
	public static String upload(String key,byte [] data,String bucketname,String baseUrl) throws IOException {
		// 调用put方法上传
		Response res = UPLOAD_MANAGER.put(data, key, getUpToken(bucketname));
		String uploadKey = "";
		if(JSONObject.fromObject(res).getBoolean("OK")){
			uploadKey = baseUrl+JSONObject.fromObject(res.bodyString()).getString("key");
		}
		return uploadKey;
	}
	//使用文件上传
	public static String upload(String key,File file,String bucketname,String baseUrl) throws IOException {
		// 调用put方法上传
		Response res = UPLOAD_MANAGER.put(file, key, getUpToken(bucketname,key));
		String uploadKey = "";
		if(JSONObject.fromObject(res).getBoolean("OK")){
			uploadKey = baseUrl+JSONObject.fromObject(res.bodyString()).getString("key");
		}
		return uploadKey;
	}

	
	//通过上传的文件流上传到七牛云
	public static String uploadfile(MultipartFile multipartFile ,String contenPath,String key) throws Exception{
		CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile)multipartFile;
		key += commonsMultipartFile.getFileItem().getName().substring(commonsMultipartFile.getFileItem().getName().lastIndexOf("."));
		String qiuniuFilename = upload(key, multipartFile.getBytes(),BUCKETNAME,BASE_URL);//图片上传到云后返回链接
		return qiuniuFilename;
	}
	
	
	/*//上传MP3文件专用方法
	public static String uploadMp3(MultipartFile multipartFile ,MultipartFile multipartFileImg,String contenPath,String key,Story story) throws Exception{
		CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile)multipartFile;
		String name = commonsMultipartFile.getFileItem().getName();
		key += name;
		story.setName(name.substring(0, (name.lastIndexOf(".")) > 0 ? name.lastIndexOf(".") : name.length()));
		String tagRunner = upload("tagRunner/"+UUID.randomUUID()+".jpg", multipartFileImg.getBytes(),BUCKETNAME,BASE_URL);
		String fileName = commonsMultipartFile.getFileItem().getName();
		File outfile    = new File(contenPath+fileName);                 
		commonsMultipartFile.getFileItem().write(outfile);
        Integer length = Mp3Util.setMP3Image(outfile, multipartFileImg.getBytes());//设置MP3文件封面
		story.setLength(length);
		String qiuniuFilename = upload(key, outfile,BUCKETNAME,BASE_URL);
		if(StringUtils.isNotBlank(story.getUrl()) && story.getUrl().contains(UploadUtils.BASE_URL) && !qiuniuFilename.equals(story.getUrl())){//删除原来在七牛云中的图片
			UploadUtils.deletefile(UploadUtils.getKey(story.getUrl()));
		}
		if(StringUtils.isNotBlank(story.getTagRunner()) && story.getTagRunner().contains(UploadUtils.BASE_URL) && !tagRunner.equals(story.getTagRunner())){
			UploadUtils.deletefile(UploadUtils.getKey(story.getTagRunner()));
		}
		story.setTagRunner(tagRunner);
		story.setUrl(qiuniuFilename);
		if(outfile.exists()){
			outfile.delete();
		}
		return qiuniuFilename;
	}*/
	//通过上传的文件流上传到七牛云
	public static String uploadfile(MultipartFile multipartFile ,String contenPath,String key,String bucketname,String baseUrl) throws Exception{
		if(StringUtils.isBlank(bucketname)){
			bucketname = BUCKETNAME;
		}
		if(StringUtils.isBlank(baseUrl)){
			baseUrl = BASE_URL;
		}
		CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile)multipartFile;
		key += commonsMultipartFile.getFileItem().getName().substring(commonsMultipartFile.getFileItem().getName().lastIndexOf("."));
		String qiuniuFilename = upload(key,multipartFile.getBytes(),bucketname,baseUrl);//图片上传到云后返回链接
		return qiuniuFilename;
	}
	
	//通过指定key删除图片
		public static void deletefile(String key) throws QiniuException{
			BUCKET_MANAGER.delete(BUCKETNAME, key);
		}
	
	//通过指定空间和key删除图片
	public static void deletefile(String bucketname ,String key) throws QiniuException{
		BUCKET_MANAGER.delete(bucketname, key);
	}
	
	public static String getKey(String url ,String baseUrl){
		if(StringUtils.isBlank(baseUrl)){
			baseUrl = BASE_URL;
		}
		return url.substring(baseUrl.length());
	}
	
	public static String getKey(String url ){
		return url.substring(BASE_URL.length());
	}
	
	
	
	/*public static boolean postRefresh(String url){
		String content =
		
		TebozHttpUtils.postRefresh(REFRESH_URL, content, "UTF-8", sign);
		return false;
	}
	*/
	/*public static void main(String args[]) throws IOException {
		System.out.println(getKey("http://resource.mytbz.com/category/4e8d987e-50d9-412f-bd46-e5f8819cc059.jpg",null));
		//deletefile(getKey("http://resource.mytbz.com/tagRunner/f8f87187-2ad8-45e6-856f-661eb038410e.jpg",null));
		postRefresh("http://resource.mytbz.com/tagRunner/f8f87187-2ad8-45e6-856f-661eb038410e.jpg");
		//System.out.println(UploadUtils.upload("img/"+UUID.randomUUID().toString()+".jpg","E:/"+"1{VVICFO`V224~]U~8LW`TD.png"));
	}*/
	
	
  /*public static void main(String[] args) throws Exception {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(REFRESH_URL);
        request.addHeader("Host","fusion.qiniuapi.com");
        String param =   "{\"urls\":[http://resource.mytbz.com/tagRunner/f8f87187-2ad8-45e6-856f-661eb038410e.jpg],\"dirs\":[],}";
        String sign = AUTH.signRequest(REFRESH_URL, param.getBytes(), "application/json");
        request.addHeader("Authorization", "QBox "+sign);
        request.addHeader("Content-Type" , "application/json");
        StringEntity se = new StringEntity(param); 
        request.setEntity(se);
        HttpResponse httpResponse = client.execute(request);
        String retSrc = EntityUtils.toString(httpResponse.getEntity());
        System.out.println(retSrc);
    }*/

} 