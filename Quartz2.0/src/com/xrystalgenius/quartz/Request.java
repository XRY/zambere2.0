package com.xrystalgenius.quartz;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Request extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_request);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.request, menu);
		return true;
	}
	
	public void getNewRequest(View view){
		Intent newReqIntent = new Intent(Request.this, InternalRequisition.class);
		startActivity(newReqIntent);
	}
	
	public void addOnExistingRequest(View view){
		Intent existIntent = new Intent(Request.this, ExistingRequest.class);
		startActivity(existIntent);
		/*Intent existIntent = new Intent(Request.this, InternalRequisitionDetails.class);
		//existIntent.putExtra("com.xrystalgenius.personIrn", "person irn number");
		//At2: Retrieve from next Activity
		startActivity(existIntent);*/
	}

}
