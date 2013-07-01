package com.xrystalgenius.quartz;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class NewSupplier extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_supplier);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_supplier, menu);
		return true;
	}

}
