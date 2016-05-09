package com.sample.injector;

import com.google.inject.AbstractModule;
import com.sample.endpoint.EndointService;
import com.sample.endpoint.SFTPEndpointService;

public class EndpointInjector extends AbstractModule {

	@Override
	protected void configure() {
		
		 //bind EndpointService to SFTP Endpoint implementation
        bind(EndointService.class).to(SFTPEndpointService.class);
		
	}

}
