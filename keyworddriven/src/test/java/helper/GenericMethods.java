package helper;

import java.io.File;

public class GenericMethods {
	
	public static String getPath(String folderName, String fileName) {
		return new StringBuilder(System.getProperty("user.dir") + File.separator + folderName + File.separator + fileName).toString();
	}

}
