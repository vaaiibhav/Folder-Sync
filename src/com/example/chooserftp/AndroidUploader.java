package com.example.chooserftp;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;
import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPFile;

import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;




public class AndroidUploader extends ListActivity {

 

	

 
		
    /** Called when the activity is first created. */

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

       


uploadfolder();
  

 }
 
 
     

 private void uploadfolder() {
	// TODO Auto-generated method stub
	    // in application never use hardcoded paths
	 FTPClient upclient = new FTPClient();
		try {
			
			upclient.connect("ftp.camimac.com",21);
			upclient.login("abcchk@camimac.com", "pass123");
			upclient.setType(FTPClient.TYPE_BINARY);
			Log.d("Client ", "Connected");
		//	client.changeDirectory("/upload/");
			
			//upload folder
			
			
			upclient.changeDirectory("/mobilemedia/");
			 File local_dir=new File("/sdcard/SyncMediaUp");
			    File[] uploadFiles=local_dir.listFiles();
			//    String filename = uploadFiles.toString();
			    for (File uploadfile : uploadFiles) {
			    	Log.wtf("File  uploaded is", uploadfile.getName());
			    try {
			    	upclient.upload(uploadfile);
				} catch (Exception e) {
					// TODO: handle exception
				}
			    	
			    }
			  
		}
			    catch (Exception e) {
					e.printStackTrace();
			    }
			// upload folder ends
			
			
 }


public void uploadFile(File fileName){
 	
 	
	FTPClient client = new FTPClient();
	try {
		
		client.connect("ftp.camimac.com",21);
		client.login("abcchk@camimac.com", "pass123");
		client.setType(FTPClient.TYPE_BINARY);
		Log.d("Client ", "Connected");
	//	client.changeDirectory("/upload/");
		try {
			client.changeDirectory("/mobilemedia/");
			client.upload(fileName, new MyTransferListener());
			Log.wtf("Uploading", "Yep i uploaded");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	} catch (Exception e) {
		e.printStackTrace();
		try {
			client.disconnect(true);	
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
}
 public class MyTransferListener implements FTPDataTransferListener {

 	public void started() {
 		
 		//btn.setVisibility(View.GONE);
 		// Transfer started
 		Toast.makeText(getBaseContext(), " Upload Started ...", Toast.LENGTH_SHORT).show();
 		//System.out.println(" Upload Started ...");
 	}

 	public void transferred(int length) {
 		
 		// Yet other length bytes has been transferred since the last time this
 		// method was called
 		Toast.makeText(getBaseContext(), " transferred ..." + length, Toast.LENGTH_SHORT).show();
 		//System.out.println(" transferred ..." + length);
 	}

 	public void completed() {
 		
 		//btn.setVisibility(View.VISIBLE);
 		// Transfer completed
 		
 		Toast.makeText(getBaseContext(), " completed ...", Toast.LENGTH_SHORT).show();
 		//System.out.println(" completed ..." );
 	}

 	public void aborted() {
 		
 	//	btn.setVisibility(View.VISIBLE);
 		// Transfer aborted
 		Toast.makeText(getBaseContext()," transfer aborted , please try again...", Toast.LENGTH_SHORT).show();
 		//System.out.println(" aborted ..." );
 	}

 	public void failed() {
 		
 	//	btn.setVisibility(View.VISIBLE);
 		// Transfer failed
 		System.out.println(" failed ..." );
 	}

 }
}
