package com.bankroute.datatools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5Encryption {
	final static String format="%02x";
	final static char addedArg=0xab;
	
	final static String encrypteString(String str) {
		String encryptedMessage="";
		MessageDigest messageDisgest;
		try {
			messageDisgest = MessageDigest.getInstance("MD5");
			messageDisgest.update(str.getBytes());
			byte [] bytes= messageDisgest.digest();
			StringBuffer stringBuffer= new StringBuffer();
			for(byte bite : bytes) {
				stringBuffer.append(String.format(format, bite & addedArg));
			}
			encryptedMessage=stringBuffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encryptedMessage;
	}
}
