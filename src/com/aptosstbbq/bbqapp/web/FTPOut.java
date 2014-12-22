package com.aptosstbbq.bbqapp.web;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.net.ftp.FTPClient;

import com.aptosstbbq.bbqapp.Logger;

public class FTPOut {

	public String server = "www.digitalrocketry.com";
	public int port = 21;
	public String user = "u857457562.larry";
	public String pass = "LarryIsRad";
	public String localPath = "menu.json";
	public String remotePath = "menu.json";
	public int retryCount = 4; // how many times do we retry the connection
								// before giving up?
	public long retryWaitTime = 5000; // in ms

	public boolean write() {
		try (InputStream fileReader = new FileInputStream(new File(localPath))) {
			return write(fileReader);
		} catch (Exception e) {
			Logger.FTP.log(e.getMessage());
			return false;
		}
	}

	public boolean write(String content) {
		InputStream stream = new ByteArrayInputStream(
				content.getBytes(StandardCharsets.UTF_8));
		return write(stream);
	}

	public boolean write(InputStream stream) {
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();
			boolean done = ftpClient.storeFile(remotePath, stream);
			if (done) {
				Logger.FTP.log("Transfer successful: " + remotePath);
			} else {
				Logger.FTP.log("Transfer failed: " + remotePath);
			}
			return done;
		} catch (IOException e) {
			// System.out.println("Error: " + ex.getMessage());
			Logger.FTP.log(e.getMessage());
			return false;
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException e) {
				Logger.FTP.log(e.getMessage());
			}
		}
	}

	private class FTPThread extends Thread {
		
		InputStream stream;
		boolean done = false;
		
		FTPThread(InputStream stream) {
			this.stream = stream;
		}
		
		public void run() {
			FTPClient ftpClient = new FTPClient();
			try {
				ftpClient.connect(server, port);
				ftpClient.login(user, pass);
				ftpClient.enterLocalPassiveMode();
				done = ftpClient.storeFile(remotePath, stream);
				if (done) {
					Logger.FTP.log("Transfer successful: " + remotePath);
				} else {
					Logger.FTP.log("Transfer failed: " + remotePath);
				}
			} catch (IOException e) {
				// System.out.println("Error: " + ex.getMessage());
				Logger.FTP.log(e.getMessage());
			} finally {
				try {
					if (ftpClient.isConnected()) {
						ftpClient.logout();
						ftpClient.disconnect();
					}
				} catch (IOException e) {
					Logger.FTP.log(e.getMessage());
				}
			}
		}
	}
}
