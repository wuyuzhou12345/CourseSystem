package org.javatribe.courseSystem.util;

import java.io.File;
import java.io.FileInputStream;

import android.util.Base64;

public class EncodeBase64File {
	public static String encodeBase64File(String path) throws Exception
	{
		File file=new File(path);
		FileInputStream inputFile=new FileInputStream(file);
		byte[] buffer=new byte[(int)file.length()];
		inputFile.read(buffer);
		inputFile.close();
		return Base64.encodeToString(buffer, Base64.DEFAULT);
	}
	//public static void decodeBase64File(String )
}
