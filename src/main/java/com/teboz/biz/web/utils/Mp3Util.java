package com.teboz.biz.web.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Frame;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v23Frame;
import org.jaudiotagger.tag.id3.framebody.FrameBodyAPIC;

public class Mp3Util {

    /**
     * 获取MP3歌曲名、歌手、时长信息
     * @param mp3File
     * @return
     */
   public static String getMP3Info(File mp3File) {
        //MP3Info mp3Info = new MP3Info();
	   	String str = "";
        try {
            MP3File file = new MP3File(mp3File);
            //mp3Info.setSongName(toGB2312(file.getID3v2Tag().frameMap.get("TIT2").toString()));
            str = toGB2312(file.getID3v2Tag().frameMap.get("TPE1").toString());
            MP3AudioHeader audioHeader = (MP3AudioHeader)file.getAudioHeader();
            audioHeader.getTrackLength();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TagException e) {
            e.printStackTrace();
        } catch (ReadOnlyFileException e) {
            e.printStackTrace();
        } catch (InvalidAudioFrameException e) {
            e.printStackTrace();
        }
        return str;
    }

   
   public static Integer getMP3Length(File mp3File) {
	   Integer length = 0;
       try {
           MP3File file = new MP3File(mp3File);
           MP3AudioHeader audioHeader = (MP3AudioHeader)file.getAudioHeader();
           length = audioHeader.getTrackLength();
       } catch (IOException e) {
           e.printStackTrace();
       } catch (TagException e) {
           e.printStackTrace();
       } catch (ReadOnlyFileException e) {
           e.printStackTrace();
       } catch (InvalidAudioFrameException e) {
           e.printStackTrace();
       }
       return length;
   }

    /**
     * 获取MP3封面图片
     * @param mp3File
     * @return
     * @throws InvalidAudioFrameException 
     * @throws ReadOnlyFileException 
     * @throws TagException 
     * @throws IOException 
     */
    public static byte[] getMP3Image(File mp3File) throws IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
        byte[] imageData = null;
        MP3File mp3file = new MP3File(mp3File);
        AbstractID3v2Tag tag = mp3file.getID3v2Tag();
        AbstractID3v2Frame frame = (AbstractID3v2Frame) tag.getFrame("APIC");
        FrameBodyAPIC body = (FrameBodyAPIC) frame.getBody();
        imageData = body.getImageData();
        return imageData;
    }


    /**
     * 设置MP3封面图片
     * @param mp3File
     * @return 返回当前MP3文件长度
     * @throws InvalidAudioFrameException 
     * @throws ReadOnlyFileException 
     * @throws TagException 
     * @throws IOException 
     */
    @SuppressWarnings("unchecked")
	public static Integer setMP3Image(File mp3File ,byte[] img) throws IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException{
    	 MP3File mp3file = new MP3File(mp3File);
    	 MP3AudioHeader audioHeader = (MP3AudioHeader)mp3file.getAudioHeader();
         Integer length = audioHeader.getTrackLength();
         AbstractID3v2Tag tag = mp3file.getID3v2Tag();
         if(tag == null ){
        	 tag = (AbstractID3v2Tag) mp3file.createDefaultTag();
        	 mp3file.setID3v2Tag(tag);
         }
         AbstractID3v2Frame frame = (AbstractID3v2Frame) tag.getFrame("APIC");
         if(null == frame ){
        	 frame = new ID3v23Frame("APIC"); 
        	 tag.frameMap.put("APIC", frame);
        	 FrameBodyAPIC body  =   new FrameBodyAPIC();
        	 body.setMimeType("image/jpeg");
        	 body.setDescription("描述");
        	 body.setTextEncoding((byte)0);
        	 body.setPictureType((byte) 0);
        	 ID3v23Frame header = new ID3v23Frame("APIC");
        	 body.setHeader(header);
        	 body.setSize(img.length);
        	 frame.setBody(body);
         }
         FrameBodyAPIC body = (FrameBodyAPIC) frame.getBody();
         body.setImageData(img);
         mp3file.save(mp3File);
         return length;
    }
    
    
    /**
     *获取mp3图片并将其保存至指定路径下
     * @param mp3File mp3文件对象
     * @param mp3ImageSavePath mp3图片保存位置（默认mp3ImageSavePath +"\" mp3File文件名 +".jpg" ）
     * @param cover 是否覆盖已有图片
     * @return 生成图片全路径
     * @throws InvalidAudioFrameException 
     * @throws ReadOnlyFileException 
     * @throws TagException 
     * @throws IOException 
     */
    public static String saveMP3Image(File mp3File, String mp3ImageSavePath, boolean cover) throws IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
        //生成mp3图片路径
        String mp3FileLabel = getFileLabel(mp3File.getName());
        String mp3ImageFullPath = mp3ImageSavePath + ("\\" + mp3FileLabel + ".jpg");

        //若为非覆盖模式，图片存在则直接返回（不再创建）
        if( !cover ) {
            File tempFile = new File(mp3ImageFullPath) ;
            if(tempFile.exists()) {
                return mp3ImageFullPath;
            }
        }

        //生成mp3存放目录
        File saveDirectory = new File(mp3ImageSavePath);
        saveDirectory.mkdirs();

        //获取mp3图片
        byte imageData[] = getMP3Image(mp3File);
        //若图片不存在，则直接返回null
        if (null == imageData || imageData.length == 0) {
            return null;
        }
        //保存mp3图片文件
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mp3ImageFullPath);
            fos.write(imageData);
            fos.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return mp3ImageFullPath;
    }

    
    public static byte[] toByteArray(String filename) throws IOException {  
    	  
        File f = new File(filename);  
        if (!f.exists()) {  
            throw new FileNotFoundException(filename);  
        }  
  
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());  
        BufferedInputStream in = null;  
        try {  
            in = new BufferedInputStream(new FileInputStream(f));  
            int buf_size = 1024;  
            byte[] buffer = new byte[buf_size];  
            int len = 0;  
            while (-1 != (len = in.read(buffer, 0, buf_size))) {  
                bos.write(buffer, 0, len);  
            }  
            return bos.toByteArray();  
        } catch (IOException e) {  
            e.printStackTrace();  
            throw e;  
        } finally {  
            try {  
                in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            bos.close();  
        }  
    }  
  

    /**
     * 仅返回文件名（不包含.类型）
     * @param fileName
     * @return
     */
    private static String getFileLabel(String fileName) {
        int indexOfDot = fileName.lastIndexOf(".");
        fileName = fileName.substring(0,(indexOfDot==-1?fileName.length():indexOfDot));
        return fileName;
    }
    private static String toGB2312(String s) {
        try {
            return new String(s.getBytes("ISO-8859-1"), "gb2312");
        } catch (Exception e) {
            return s;
        }
    }
}