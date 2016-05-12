package com.sample.client;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sample.endpoint.EndointService;
import com.sample.injector.EndpointInjector;
import com.sample.injector.FileUtilInjector;
import com.sample.service.RestClient;
import com.sample.util.FileUtilService;

public class ClientCLI {
	
	public static void main(String args[])
	{
		
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
        System.out.println("Java SFTP Client ");
        System.out.println("Please enter transfer file details ");
        System.out.println("File Name : ");
        String fileName = scanner.nextLine();
        System.out.println("File Path :");
        String filePath = scanner.nextLine();
        System.out.println("Checking file existence :");
        
        File file = new File(filePath+File.separator+fileName);
        boolean existFlag = file.exists();
        System.out.println(existFlag);
        if(existFlag)
        {
        	 System.out.println("MD5 for given file is: ");
        	 Injector injector = Guice.createInjector(new FileUtilInjector()); 
     		 FileUtilService app = injector.getInstance(FileUtilService.class);
             
             String result = app.getMD5ForFile(filePath+File.separator+fileName);
             System.out.println(result);
             
             System.out.println("Uplodaing file into SFTP embedded server: ");
             Injector epinjector = Guice.createInjector(new EndpointInjector()); 
             EndointService epapp = epinjector.getInstance(EndointService.class);
             boolean result1 = epapp.uploadFiletoServer(filePath,fileName);
             
             if(result1)
             {
            	  System.out.println("File Uploaded ");
            	  System.out.println("Comparing MD5 : ");
            	  
            	  //check REST service 
            	  String md5Server = RestClient.VerifyMD5(fileName);
            	  if(md5Server.equals(result))
                  {
                 	 System.out.println("Matched");
                  }else{
                 	 System.out.println("Not Matched");
                  }
             }else{
            	 System.out.println("Something went wrong please check server connection details");
             }
            
                      
        }
        
       
       
        
        
		
       
        
       
        
	}

}
