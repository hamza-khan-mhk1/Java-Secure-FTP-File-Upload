package com.test.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class FTPFileUpload{

	static	String SERVER 	= "";
	static    int PORT 	= ;
	static  String USER 	= "";
	static  String PASS 	= "";


	public static void uploadFile() {

		try {

			JSch jsch = new JSch();
			Session session = jsch.getSession( USER, SERVER, PORT);

			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			config.put("PreferredAuthentications", "password" );

			session.setConfig(config);
			session.setPassword(PASS);
			session.connect();

			Channel channel = session.openChannel( "sftp" );
			ChannelSftp sftp = ( ChannelSftp ) channel;
			sftp.connect();

			File localFile = new File("D:\\data.csv");

			String remoteFileName = "data.csv";
			InputStream inputStream = new FileInputStream(localFile);

			System.out.println("Start Uploading");
			sftp.put(inputStream,remoteFileName);
			System.out.println("File Uploaded");

			inputStream.close();

		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
		} catch (SftpException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (JSchException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}