package com.sample.injector;

import com.google.inject.AbstractModule;
import com.sample.util.FileUtilService;
import com.sample.util.MD5UtilService;

public class FileUtilInjector extends AbstractModule {

	@Override
	protected void configure() {
		
		// bind FileUtilService to MD5 util service implementation
		bind(FileUtilService.class).to(MD5UtilService.class);
	}
}
