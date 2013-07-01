package com.xrystalgenius.quartz;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){

			@Override
			public void run() {
				Intent mainIntent = new Intent(Splash.this, Landing.class);
				Splash.this.startActivity(mainIntent);
				Splash.this.finish();
				//finish();
				//if(!is)
				
			}}, 5000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }
    
}
