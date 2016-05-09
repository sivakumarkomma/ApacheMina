package com.sample.endpoint;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
	public boolean uploadFiletoServer() {
		String filePth = "C://bps-logs//bps.log";
		String fileName = "bps.log";
		JSch jsch = new JSch();
		String user = "user";
		String password = "password";
		String host = "192.168.0.172";
		int port = 22;
		Session session;
		try {
			session = jsch.getSession(user, host, port);

			session.setPassword(password);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp channelSftp = (ChannelSftp) channel;
			FileInputStream fi = new FileInputStream(filePth);
			channelSftp.put(fi, fileName, 0);
			channelSftp.disconnect();
			channel.disconnect();
			session.disconnect();
			return true;
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
