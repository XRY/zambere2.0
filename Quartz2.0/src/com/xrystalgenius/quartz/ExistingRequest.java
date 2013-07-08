package com.xrystalgenius.quartz;

import java.util.ArrayList;
import java.util.List;

import com.xrystalgenius.quartz.FeedReaderHelper.InternalRequisitionTable;
import com.xrystalgenius.quartz.FeedReaderHelper.ItemTable;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ExistingRequest extends Activity {

	private ListView listView;
	private ArrayList<String> list1;
	private ArrayAdapter<String> itemAdapter;

	private FeedReadHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.existing_request);

		listView = (ListView) findViewById(android.R.id.list);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String selectedItemTxt = (String) arg0.getItemAtPosition(arg2);

				Log.d("The user has selected : ", selectedItemTxt
						+ " In this era");

				int val = getPersonIrn(selectedItemTxt);

				Intent irndetails = new Intent(ExistingRequest.this,
						InternalRequisitionDetails.class);
				irndetails.putExtra("requesterirn", val);
				startActivity(irndetails);
			}
		});

		addItemsToListView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.existing_request, menu);
		return true;
	}

	private void addItemsToListView() {
		list1 = (ArrayList<String>) getExistingRequesters();

		itemAdapter = new ArrayAdapter<String>(ExistingRequest.this,
				android.R.layout.simple_list_item_1, list1);
		listView.setAdapter(itemAdapter);

		itemAdapter.notifyDataSetChanged();
	}

	private List<String> getExistingRequesters() {
		List<String> returnlist = new ArrayList<String>();

		dbHelper = new FeedReadHelper(this);
		db = dbHelper.getReadableDatabase();
		// Log.v("In the query function ", "Its owesome");
		// String selectQuery =
		// "SELECT requester FROM "+InternalRequisitionTable.TABLE_NAME;

		String selectQuery = "SELECT requester FROM "
				+ InternalRequisitionTable.TABLE_NAME
				+ " ORDER BY requester DESC LIMIT 3";

		// Log.v("writing a query", "Its owesome");
		Cursor cursor = db.rawQuery(selectQuery, null);
		// cursor.getColumnCount();
		// String[] data = null;
		Log.v("Query result", "Fake result just");
		if (cursor.moveToFirst()) {
			Log.v("In the query function", "blaaaaaash");
			do {
				Log.v("In the query function", "waaaaaat");

				returnlist.add(cursor.getString(0));

			} while (cursor.moveToNext());

		}
		db.close();
		// return data;
		return returnlist;
	}

	private int getPersonIrn(String selection) {
		int personirn = 0;

		dbHelper = new FeedReadHelper(this);
		db = dbHelper.getReadableDatabase();
		Log.v("In the query function ", "Its owesome");
		String selectQuery = "SELECT irn FROM "
				+ InternalRequisitionTable.TABLE_NAME + " WHERE requester = '"
				+ selection + "'";
		Log.v("writing a query", "Its owesome");
		Cursor cursor = db.rawQuery(selectQuery, null);
		// cursor.getColumnCount();
		// String[] data = null;
		// Log.v("Query result", "Fake result just");
		if (cursor.moveToFirst()) {
			Log.v("In the query function", "blaaaaaash");
			do {

				personirn = cursor.getInt(0);

			} while (cursor.moveToNext());

		}
		Log.v("Query result", personirn + " Fake result just");
		db.close();
		// return data;
		return personirn;
	}

}
