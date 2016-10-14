package test;


import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class MD5test {

	@Test
	public void MD5Secret(){
		System.out.println(DigestUtils.md5Hex("huang6864889"));
	}
}
