package com.hackathon.woofy.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;


public class getAES256EncStr {

	public static Object getAES256EncStr(List<Object> params) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
	    if (params == null || params.size() < 2 || params.get(0) == null || params.get(1) == null)
			return null;

	    String str = params.get(0).toString(); // 암호화 대상 주민등록번호
	    String key = params.get(1).toString(); // 시크릿 키
	    String iv = "0000000000000000";

	    Key keySpec;
	    byte[] keyBytes = new byte[key.length()];
	    byte[] b = key.getBytes("UTF 8");
	    int len = b.length;
	    
	    if (len > keyBytes.length) {
		    len = keyBytes.length;
	    }
	    
	    System.arraycopy(b, 0, keyBytes, 0, len);
	    keySpec = new SecretKeySpec(keyBytes, "AES");
	    Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
	    byte[] encrypted = c.doFinal(str.getBytes("UTF 8"));
	    String encStr = new String(Base64.encodeBase64(encrypted));
	    
	    return encStr;
	}
}
