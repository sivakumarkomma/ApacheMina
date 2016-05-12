package com.sample.endpoint;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.inject.Singleton;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@Singleton
public class SFTPEndpointService implements EndointService {

	@Override
	public boolean uploadFiletoServer(String filePath, String fileName) {
		String srcFullFilePath = filePath + File.separator + fileName;
		String desFileName = fileName;

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = SFTPEndpointService.class.getClassLoader().getResourceAsStream(
					"config.properties");

			// load a properties file
			prop.load(input);

			String userName = prop.getProperty("username");
			String pwd = prop.getProperty("password");
			int port = Integer.parseInt(prop.getProperty("port"));
			String host = prop.getProperty("host");
			JSch jsch = new JSch();

			Session session;

			session = jsch.getSession(userName, host, port);

			session.setPassword(pwd);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp channelSftp = (ChannelSftp) channel;
			FileInputStream fi = new FileInputStream(srcFullFilePath);
			channelSftp.put(fi, desFileName, 0);
			channelSftp.disconnect();
			channel.disconnect();
			session.disconnect();
			return true;
		} catch (JSchException e) {
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SftpException e) {
			e.printStackTrace();
			return false;
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		

	}
}
