package test.mp3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.junit.Test;

import com.teboz.biz.web.utils.Mp3Util;

public class Mp3Test {

	public static void  test1() throws Exception {
		File [] dirs = new File[] {
				//new File("F:/myvoices"),
				new File("F:/music")
		};
		
		for(File dir : dirs) {
			File [] fs = dir.listFiles();
			for(File file : fs) {
				int length = getMp3TrackLength(file);
				if(length <= 0) {
					System.err.println("###出错" + file.getName() + "=" + getMp3TrackLength(file));	
				} else {
					System.out.println(file.getName() + "=" + getMp3TrackLength(file));
				}
				
			}
		}
	}
	
	public static void  testImg() throws Exception {
		File mp3 = new File("F:/music/浜崎あゆみ - TO BE.mp3");
		Mp3Util.saveMP3Image(mp3,"F:/music/", true);
	}
	
	public static void main(String[] args) throws Exception {
		testImg();
	}
	public static int getMp3TrackLength(File mp3File) {
		try {
			MP3File f = (MP3File)AudioFileIO.read(mp3File);
			MP3AudioHeader audioHeader = (MP3AudioHeader)f.getAudioHeader();
			//audioHeader.get
			return audioHeader.getTrackLength();	
		} catch(Exception e) {
			return -1;
		}
	}
	
	@Test
	public void setMP3ImageTest() throws IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException{
		File mp3 = new File("F:/music/王心凌 - 花的嫁纱 - 《天国的嫁衣》电视剧主题曲.mp3");
		File img = new File("F:/Group/$I~4F0FSL~]QK2XCU(NEQ36.jpg");
		InputStream in = new FileInputStream(img);
		byte[] b = null;
		in.read(b);
		in.close();
		Mp3Util.setMP3Image(mp3, b);
	}
}
