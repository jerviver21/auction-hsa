package edu.auctionhsa.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

import edu.auctionhsa.Constants;

public class FileManager {
	
	public String saveFile(String dir, String name, MultipartFile io)throws IOException{
		System.out.println("************"+io.getOriginalFilename()+"************");
		
		String ext = io.getOriginalFilename().replaceAll(".*\\.(.*)", "$1");
		File file = new File(dir+File.separator+name+"."+ext);


	    byte[] buffer = new byte[io.getInputStream().available()];
	    io.getInputStream().read(buffer);
	 
	    OutputStream outStream = new FileOutputStream(file);
	    outStream.write(buffer);
		
		//io.transferTo(file);
		return file.getAbsolutePath();
	}

}
