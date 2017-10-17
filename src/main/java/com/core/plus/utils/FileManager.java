package com.core.plus.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

@Service
public class FileManager {
	
	public FileManager(){}
	
	public boolean doFileDownload(String file_name, String pathname, HttpServletResponse response) {
		/*String filePath = "downloads\\";
		/*String filePath = "C:\\users\\core\\desktop\\file\\";*/
/*		String fullpathname = pathname +filePath+ file_name;*/
		String filePath = "C:\\Users\\1234\\Desktop\\test\\";
		String fullpathname = filePath+ file_name;
		System.out.println(fullpathname.toString());
		String viewfilename = file_name.substring(10, file_name.length());
		
	    try {
	        File file = new File(fullpathname);

	        if (file.exists()){
	            byte readByte[] = new byte[4096];

	            response.setContentType("application/octet-stream");
				response.setHeader(
						"Content-disposition",
						"attachment; filename=" + UriUtils.encodeFragment(viewfilename, "UTF-8"));

	            BufferedInputStream  fin  = new BufferedInputStream(new FileInputStream(file));
	            OutputStream outs = response.getOutputStream();
	            
	   			int read;
	    		while ((read = fin.read(readByte, 0, 4096)) != -1)
	    				outs.write(readByte, 0, read);
	    		outs.flush();
	    		outs.close();
	            fin.close();
	            
	            return true;
	        }
	    } catch(Exception e) {
	    	
	    }
	    
	    return false;
	}
	
	public String doFileUpload(MultipartFile file, HttpServletRequest request)  throws IOException{
  
		request.setCharacterEncoding("euc-kr");
		
 		/*String filePath = "downloads\\";*/
		/*String filePath = "C:\\users\\core\\desktop\\file\\";*/
		String filePath = "C:\\Users\\1234\\Desktop\\test\\";
		String folderPath = "";
		String random = ""; 
		
			ServletContext context = request.getSession().getServletContext();
			/*String appPath = context.getRealPath("");*/
			
			for(int i=0; i<10; i++){
				char rtext = (char)((Math.random()*26)+65);
				random += rtext;
 			}
			
			String randomtext = random;
			
			// construct the complete absolute path of the file
			folderPath =  filePath;
			
		   String uploadFilePath =  filePath + randomtext + file.getOriginalFilename();
			/*String uploadFilePath = filePath + randomtext + file.getOriginalFilename();*/
			System.out.println("파일 경로" +uploadFilePath.toString());
			 
			File folderFile = new File(folderPath);	
			File uploadFile = new File(uploadFilePath);
			
			if (folderFile.isDirectory() == false) {
				folderFile.mkdirs();
			}
			
			try {

				byte[] input = file.getBytes();
				FileOutputStream fos = new FileOutputStream(uploadFile);
				fos.write(input);
				fos.close();
				
				return uploadFile.toString();

			} catch (IOException e1) {
				System.out.println("error!");
			}
		 
		return uploadFilePath;
	}
	
	public boolean doCompressFile(String[] source, HttpServletResponse response){
		
		if(source.length<1){
			return false;
		}
		
		File file = new File(source[0]);
		
		if(!file.isFile()){
			return false;
		}
		
		try {
			
			byte[] buf = new byte[4096];
			response.setContentType("application/octet-stream");
			response.setHeader(
					"Content-disposition",
					"attachment; filename=" + UriUtils.encodeFragment("다운로드파일.zip", "UTF-8"));
			OutputStream outs = response.getOutputStream();
			
			ZipOutputStream zipOut = new ZipOutputStream(outs);
			
			for(int i=0; i<source.length; i++){
				
				FileInputStream in = new FileInputStream(source[i]);
				
				String[] temp= source[i].split("\\\\");
				zipOut.putNextEntry(new ZipEntry(temp[temp.length-1])); 
				
				int len;
				while((len = in.read(buf))>0){
					zipOut.write(buf, 0, len);
				}
				
				zipOut.closeEntry();
				in.close();
				
			}
			
			zipOut.close();
			return true;

		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
}