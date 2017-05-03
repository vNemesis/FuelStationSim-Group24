//StringToFile.java
package com.aston.group24.util;

import java.io.FileWriter;
import java.io.IOException;

/**
 * This class sends a String to a text file in the local directory
 * 
 * @author HarmanU
 *@version 02.05.2017/2350
 */
public class StringToFile {
	
	public static void sendToFile(String text, String nameOfFile) throws IOException
	{
		FileWriter writer = null;
		 try
		 {
			 writer = new FileWriter(nameOfFile + ".txt");
			 writer.write(text);
		 } 
		 finally 
		 {
			 try {
				 if (writer != null) writer.close();
			 }
			 catch (Exception e) 
			 {
				 
			 }
		 }
	}

}
