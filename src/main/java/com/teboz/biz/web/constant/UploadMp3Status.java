package com.teboz.biz.web.constant;

public final class UploadMp3Status {
	
	//上传成功对应状态 1 
	public static final String SUCCESS = "保存成功";
	
	//上传失败没有专辑封面情况
	public static final String NO_TAG = "MP3格式文件没有专辑封面或者文件解析错误";
	
	//其他上传失败情况
	public static final String  UPLOAD_FAILED = "上传MP3到七牛云失败";

}
