package com.xrystalgenius.quartz;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.SumPathEffect;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Inventory extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory);

		ListView invListView = (ListView) findViewById(R.id.inventoryList);
		invListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				// Toast.makeText(getApplicationContext(),"1 item clicked ",
				// Toast.LENGTH_SHORT).show();
				if (arg2 == 0) {
					Intent gidIntent = new Intent(Inventory.this,
							GoodsInDocket.class);
					startActivity(gidIntent);
				}

				else if (arg2 == 1) {
					Intent irnIntent = new Intent(Inventory.this,
							Request.class);
					// gidIntent.putExtra("Clicked", arg2);
					startActivity(irnIntent);

				}
				
				else if (arg2 == 2) {
					Intent irnIntent = new Intent(Inventory.this,
							Returns.class);
					// gidIntent.putExtra("Clicked", arg2);
					startActivity(irnIntent);

				}
				
				else if (arg2 == 3) {
					Intent irnIntent = new Intent(Inventory.this,
							Summary.class);
					// gidIntent.putExtra("Clicked", arg2);
					startActivity(irnIntent);

				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inventory, menu);
		return true;
	}

}
