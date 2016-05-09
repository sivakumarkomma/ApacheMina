package com.sample.client;

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
		Injector injector = Guice.createInjector(new FileUtilInjector()); 
		FileUtilService app = injector.getInstance(FileUtilService.class);
        
        String result = app.getMD5ForFile("C://bps-logs//bps.log");
        
        System.out.println("result =====>"+result);
        
        Injector epinjector = Guice.createInjector(new EndpointInjector()); 
        EndointService epapp = epinjector.getInstance(EndointService.class);
        boolean result1 = epapp.uploadFiletoServer();
        
        System.out.println("result1 =====>"+result1);
        
        //check REST service 
         String md5Server = RestClient.VerifyMD5("bps.log");

         System.out.println("md5Server =====>"+md5Server);
         if(md5Server.equals(result))
         {
        	 System.out.println("*****Both are same*****");
         }else{
        	 System.out.println("*****Both are wrong*****");
         }
        
	}

}
