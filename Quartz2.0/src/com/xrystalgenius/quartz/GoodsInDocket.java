package com.xrystalgenius.quartz;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class GoodsInDocket extends Activity {
	
	//private String[] categoryArray = {"Plumbing", "Construction","Roofing"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_in_docket);
		
		
		//Spinner catSpinner = (Spinner)findViewById(R.id.spinner2);
		//ArrayAdapter<String> catAdapter = new ArrayAdapter<String>(this, R.layout.goods_in_docket, categoryArray);
		//catAdapter.setDropDownViewResource(R.layout.goods_in_docket);
		//catSpinner.setAdapter(catAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.goods_in_docket, menu);
		
		return true;
	}

}
