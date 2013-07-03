package com.xrystalgenius.quartz;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Summary extends Activity {
	private ListView listView;
	private Button searchBtn, backBtn;
	private ArrayList<String> list1;
	private ArrayAdapter<String> itemAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);

		listView = (ListView) findViewById(android.R.id.list);

		list1 = new ArrayList<String>();

		itemAdapter = new ArrayAdapter<String>(Summary.this,
				android.R.layout.simple_list_item_1, list1);
		listView.setAdapter(itemAdapter);

		list1.add("Leonel Messi");
		list1.add("Van persie");
		list1.add("Wyn Roonie");
		list1.add("Maron Chamack");

		itemAdapter.notifyDataSetChanged();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

}
