package com.xrystalgenius.quartz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            String pName = this.getClass().getPackage().getName();
            this.copy("quartz.db","/data/data/"+pName+"/databases/");
        	/*File CheckDirectory;
        	CheckDirectory = new File("quartz.db","/mnt/sdcard/Quartz/");
        	if (!CheckDirectory.exists())
        	{ 
        		this.copy("quartz.db","/mnt/sdcard/Quartz/");
        	} */

        	
        	
        }
        catch (IOException e)
        {
        	e.printStackTrace();
        }
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
				
			}}, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }
    
    void copy(String file, String folder) throws IOException {
		File CheckDirectory;
		CheckDirectory = new File(folder);
		if (!CheckDirectory.exists())
		{ 
			CheckDirectory.mkdir();
		}

		InputStream in = getApplicationContext().getAssets().open(file);
		OutputStream out = new FileOutputStream(folder+file);

		// Transfer bytes from in to out
		byte[] buf = new byte[1024];
		int len; while ((len = in.read(buf)) > 0) out.write(buf, 0, len);
		in.close(); out.close();

	}
    
}
