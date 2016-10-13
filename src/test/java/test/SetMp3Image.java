package test;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.junit.Test;

import com.teboz.biz.web.utils.Mp3Util;

public class SetMp3Image {
	
	
	
	
	@Test
	public void setMP3ImageTest() throws IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException{
		File mp3 = new File("F:/music/王心凌 - 花的嫁纱 - 《天国的嫁衣》电视剧主题曲.mp3");
		byte[] b  = getBytes("F:/Group/$I~4F0FSL~]QK2XCU(NEQ36.jpg");
		Mp3Util.setMP3Image(mp3, b);
	}
	
	
	
	@Test
	public void setMP3ImagesTest() throws IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException{
		File MP3Dir = new File("F:/BaiduMusic/Songs");
		String imageDir = "F:/BaiduMusic/tagImages";
		File [] MP3Arr = MP3Dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				boolean flag = false;
				if(StringUtils.isNotBlank(name)&&".mp3".equals(name.substring(name.lastIndexOf(".")))){
					flag = true;
				}
				return flag;
			}
		});
		for(int i=0; i<MP3Arr.length;i++){
			File MP3 = MP3Arr[i];
			String imgFilePath = imageDir +"/"+ MP3.getName().substring(MP3.getName().lastIndexOf("/")+1, MP3.getName().lastIndexOf("."))+".jpg";
			File image = new File(imgFilePath);
			System.out.println(imgFilePath);
			if(image.exists()){
				Mp3Util.setMP3Image(MP3, getBytes(imgFilePath));
				FileInputStream fis = new FileInputStream(MP3);
				File newMp3File = new File("F:/BaiduMusic/setImage/"+MP3.getName().substring(MP3.getName().lastIndexOf("/")+1));
				if(!newMp3File.exists()){
					newMp3File.createNewFile();
				}
				FileOutputStream fos = new FileOutputStream(newMp3File);
				byte [] b = new byte[1000];
				while (-1 !=fis.read(b)) {
					fos.write(b);
				}
				fis.close();
				fos.flush();
				fos.close();
			}
		}
		System.out.println(MP3Arr.length);
	}
	
	
	/** 
     * 获得指定文件的byte数组 
     */  
    public static byte[] getBytes(String filePath){  
        byte[] buffer = null;  
        try {  
            File file = new File(filePath);  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
            byte[] b = new byte[1000];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer;  
    }
    
    /** 
     * 根据byte数组，生成文件 
     */  
    public static void getFile(byte[] bfile, String filePath,String fileName) {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        try {  
            File dir = new File(filePath);  
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在  
                dir.mkdirs();  
            }  
            file = new File(filePath+"\\"+fileName);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(bfile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
    } 

}
