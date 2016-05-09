package com.sample.util;

import java.io.FileInputStream;
import java.io.IOException;

import javax.inject.Singleton;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

@Singleton
public class MD5UtilService implements FileUtilService {

	@Override
	public String getMD5ForFile(String filePath) {
		String results = null;
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(filePath);

			// md5Hex converts an array of bytes into an array of characters
			// representing the hexadecimal values of each byte in order.
			// The returned array will be double the length of the passed array,
			// as it takes two characters to represent any given byte.
			results = DigestUtils.md5Hex(IOUtils.toByteArray(inputStream));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return results;
	}

}
