package com.xrystalgenius.quartz;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ExistingRequest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.existing_request);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.existing_request, menu);
		return true;
	}

}
