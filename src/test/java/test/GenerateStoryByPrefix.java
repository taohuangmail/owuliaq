package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.teboz.biz.web.utils.UploadUtils;

public class GenerateStoryByPrefix {
	
	@Test
	public void generateStory(){
		String prefix = "init";
		String newPrefix ="otherInit";
		File file = new File("E:/828项目/SQL/initStory.sql");
		BufferedWriter bw = null;
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileListing fileListing = UploadUtils.BUCKET_MANAGER.listFiles(UploadUtils.BUCKETNAME, prefix, null, 100, null);
			FileInfo[] items = fileListing.items;
			int count = 0;
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = sdf.format(date);
	        for(FileInfo fileInfo:items){
	        	 String newKey =  fileInfo.key.replace(prefix, newPrefix);
	        	 String name = fileInfo.key.substring(fileInfo.key.lastIndexOf("/")+1, fileInfo.key.lastIndexOf("."));
	        	 //UploadUtils.BUCKET_MANAGER.move(UploadUtils.BUCKETNAME, fileInfo.key, UploadUtils.BUCKETNAME, newKey);
	        	 String sql = generateSql(newKey, name, count++, 6, "发明故事", dateStr);
	        	 bw.write(sql+"\n");
	        	 System.out.println(sql);
	        }
	        bw.close();
		} catch (QiniuException e) {
			Response r = e.response;
	        System.out.println(r.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String generateSql(String newKey,String name,int count,int categoryId,String categoryName,String dateStr){
		String sql = "INSERT INTO `bo_story` VALUES (null, '"+categoryId+"', '"+categoryName+"', '1', '1', '0', '"+(count)+"', null, 'http://resource.mytbz.com/"+newKey+"', null, '"+name+"', '0', 'http://resource.mytbz.com/tagRunner/"+name+".jpg', null, '0', '1', '1', '"+dateStr+"', '"+dateStr+"');";
		return sql;
	}
}
