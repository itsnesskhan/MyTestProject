package com.java.helper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Helper {
	
	public static boolean saveProfile(InputStream stream, String path) {
		boolean flag = false;
		try {
			byte[] bArray = new byte[stream.available()];
			stream.read(bArray);
			
			FileOutputStream outputStream = new FileOutputStream(path);
			outputStream.write(bArray);
			flag = true;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
