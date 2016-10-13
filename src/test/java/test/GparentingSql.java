package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
//替换原来文章样式的功能
public class GparentingSql {
	
	static String filename="E:/工作文档/R1java项目/articles.json";
	static String filename2="E:/工作文档/R1java项目/articles2.json";

	static String sqlFilename="E:/工作文档/R1java项目/articlesupdate.sql";
	public static void gsql() throws IOException{
		File file = new File(filename);
		File file2 = new File(filename2);

		File sqlFile =new File(sqlFilename);
		
		FileInputStream is = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		StringBuffer json = new StringBuffer();
		String str;
		while ((str=br.readLine())!=null) {
			json.append(str);
		}
		br.close();
		if(StringUtils.isNotBlank(json)){
			System.out.println(json);
			JSONObject jObjet = JSONObject.parseObject(json.toString());
			JSONArray arts = jObjet.getJSONArray("articles");
			StringBuffer sql = new StringBuffer();
			for(int i=0;i<arts.size() ;i++){
				JSONObject art = (JSONObject) arts.get(i);
				art.put("content_html", replaceHtml(art.getString("content_html")));
				String strsql = "UPDATE parenting_articles set content_html=\""+art.getString("content_html")+"\" WHERE id="+art.getString("id")+";";
				sql.append(strsql+"\n");
			}
			
			if(!sqlFile.exists()){
				sqlFile.createNewFile();
			}
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sqlFile)));
			bw.write(sql.toString());
			bw.close();
			
			
			if(!file2.exists()){
				file2.createNewFile();
			}
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2)));
			bw.write(jObjet.toJSONString());
			bw.close();
		}
		//file.
		
	}
	
	private static  String replaceHtml(String oldHtml){
		String headNew = "<style> body {color:black;;margin: 0;padding: 5%;font-family: '黑体';}  body:after {content: ''; position:fixed;left:0;top:0;right:0;bottom:0;z-index:-1;display:block;background-color: #ECECEC;}  img {max-height: 700px; padding-bottom: 4%;width: 110%;display: block;margin-left: -5%;}  h1 {padding-top: 20%;padding-bottom: 7%;font-size: 153%;text-align: center}  h2 {font-size: 112%;}</style>";
		String headOld ="<style>body {color:#fff;margin: 0;padding: 5%;}body:after {content: ''; position:fixed;left:0;top:0;right:0;bottom:0;z-index:-1;display:block;background:url(yw4O0.png) no-repeat 50% 50%;background-size:cover;}img {max-height: 700px; padding-bottom: 4%;width: 110%;display: block;margin-left: -5%;}h1 {padding-top: 20%;padding-bottom: 7%;font-size: 120%;}h2 {font-size: 112%;}</style>";
		return oldHtml.replace(headOld, headNew);
	}
	
	public static void main(String[] args) throws IOException {
		gsql();
	}

}
