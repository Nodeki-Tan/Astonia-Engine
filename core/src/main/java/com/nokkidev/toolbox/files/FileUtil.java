package com.nokkidev.toolbox.files;

import com.badlogic.gdx.files.FileHandle;
import com.nokkidev.toolbox.data.StringUtils;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class FileUtil
{
	/** Change file extension. */
	public static String setExtension(String extension, String fileName)
	{
		int a = fileName.length();
		int num = 0;
		
		for (int i = a; i > 0; --i)
		{
			char data = fileName.charAt(i-1);
			if (data == '.')
			{
				fileName = StringUtils.inSubString(fileName, num);
				fileName += extension;
				return fileName;
			} else if (data == '\\' || data == '/')
			{
				fileName += "." + extension;
				return fileName;
			} else num++;
		}
		return fileName;
	}
	
	/** Get file extension. */
	public static String getExtension(String fileName)
	{
		int a = fileName.length();
		int num = 0;
		
		for (int i = a; i > 0; --i)
		{
			char data = fileName.charAt(i-1);
			if (data == '.') {
				return fileName.substring(a-num);
			} else if (data == '\\' || data == '/') return null;
			num++;
		}
		
		return null;	
	}
	
	/* Is extension match to file's extension. */
	public static boolean extensionOf(String extension, String fileName) {
		return StringUtils.matches(extension, getExtension(fileName));
	}

	/* Convenience method that returns a FileType.Internal file handle. */
	public static FileHandle getFile(String path) {

		return new FileHandle( "core/assets/" + path);
	}

	// Might get upgraded with LibGDX functions!
	public static String loadFileAsString(String path){
		StringBuilder builder = new StringBuilder();

		try{

			FileInputStream br = new FileInputStream(path);
			ObjectInputStream ou = new ObjectInputStream(br);

			while(ou.available() > 0)
				builder.append(ou.readInt() + "_");


			ou.close();
		}catch(EOFException e){
		}catch(IOException e){
			e.printStackTrace();
		}

		return builder.toString();
	}

}
