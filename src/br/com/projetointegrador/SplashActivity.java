package br.com.projetointegrador;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		new Handler().postDelayed(new Runnable() {
			
			public void run() {
				Intent mainIntent = new Intent(SplashActivity.this , ArrecebaAndroidActivity.class);
				SplashActivity.this.startActivity(mainIntent);
				SplashActivity.this.finish();
			}
		} , 2000);
	}
	
}
