package com.example.chooserftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;

public class AndroidDownloader extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
	    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
downfolder();
	}

	private void downfolder() {
		// TODO Auto-generated method stub
		FTPClient downclient = new FTPClient();
		try {
			
			downclient.connect("ftp.camimac.com",21);
			downclient.login("abcchk@camimac.com", "pass123");
		//	upclient.setType(FTPClient.TYPE_BINARY);
			Log.d("Client ", "Connected");
		//	client.changeDirectory("/upload/");
			
			//upload folder
			File SyncMediaDown = new File(Environment.getExternalStorageDirectory(), "/sdcard/SyncMediaDown");

			if(!SyncMediaDown.exists()) {                                 
			  SyncMediaDown.mkdirs();
			}
			
			downclient.changeWorkingDirectory("/computermedia/");
			 File local_dir=new File("/sdcard/SyncMediaDown");
			  
			//    String filename = uploadFiles.toString();
			  
			    downclient.enterLocalPassiveMode();
	            System.out.println("Remote system is " + downclient.getSystemName());
	            //change current directory
	          
	            System.out.println("Current directory is " +local_dir);

	            //get list of filenames
	            FTPFile[] ftpFiles = downclient.listFiles();

	            if (ftpFiles != null && ftpFiles.length > 0) {
	                //loop thru files
	                for (FTPFile file : ftpFiles) {
	                    if (!file.isFile()) {
	                        continue;
	                    }
	                   
	                   
	                    		 System.out.println("File is " + file.getName());
	                    		OutputStream output;
	                            output = new FileOutputStream(local_dir + "/" + file.getName());
	                            //get the file from the remote system
	                            downclient.retrieveFile(file.getName(), output);
	                            //close output stream
	                            output.close();

	                    	//}
	       	            //}

	                }
	            }
				
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	}
}
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
		

