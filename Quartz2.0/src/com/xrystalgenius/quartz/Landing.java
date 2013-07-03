package com.xrystalgenius.quartz;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Landing extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landing);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.landing, menu);
		return true;
	}
	
	public void getRollCall(View view){
		
	}
	
	public void getInventory(View view){
		Intent inventoryIntent = new Intent(this, Inventory.class);
		Landing.this.startActivity(inventoryIntent);
		
	}
	
	public void getNotifications(View view){
		
	}

}
