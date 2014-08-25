package com.example.chooserftp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyfirstScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	setContentView(R.layout.firstscreen);
	Button upload = (Button) findViewById(R.id.btn_upload);
	Button download = (Button) findViewById(R.id.btn_download);
	
	upload.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		Intent uplme= new Intent(MyfirstScreen.this, AndroidUploader.class);
		startActivity(uplme);
		}
	});
	
	download.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent dnlme = new Intent (MyfirstScreen.this, AndroidDownloader.class);
			startActivity(dnlme);
		}
	});
	
	}
}
