package com.aptosstbbq.bbqapp.web;

import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FTPOut {
	 String server = "www.digitalrocketry.com";
     int port = 21;
     String user = "u857457562.larry";
     String pass = "LarryIsRad";
     String file = "menu.json";
     
     public FTPOut() {
    	 
     }
     
     public boolean write(String content) {
    	 FTPClient ftpClient = new FTPClient();
         try {
  
             ftpClient.connect(server, port);
             ftpClient.login(user, pass);
             ftpClient.enterLocalPassiveMode();
  
             ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
             
             return true;
         }  catch (IOException ex) {
             //System.out.println("Error: " + ex.getMessage());
             //ex.printStackTrace();
        	 return false;
         } finally {
             try {
                 if (ftpClient.isConnected()) {
                     ftpClient.logout();
                     ftpClient.disconnect();
                 }
             } catch (IOException ex) {
                 ex.printStackTrace();
             }
         }
     }
}
