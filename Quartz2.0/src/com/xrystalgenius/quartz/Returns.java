package com.xrystalgenius.quartz;

import java.util.ArrayList;
import java.util.List;

import com.xrystalgenius.quartz.FeedReaderHelper.DepartmentTable;
import com.xrystalgenius.quartz.FeedReaderHelper.InternalRequisitionTable;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Returns extends ListActivity {
	private ListView listView;
	private Button searchBtn, saveBtn;
	private EditText searchEt;
	private ArrayList<String> list1;
	private ArrayAdapter<String> itemAdapter;
	private String searchTxt;

	private FeedReadHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_returns);

		searchEt = (EditText) findViewById(R.id.returnsSearchEt1);
		searchBtn = (Button) findViewById(R.id.returnsSearchBtn1);
		listView = (ListView) findViewById(android.R.id.list);

		list1 = (ArrayList<String>) getReturnNames();

		searchBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				searchTxt = searchEt.getText().toString();

				if (searchTxt != "") {
					Log.v("Success: ", "Got results I have been searching 4");
					list1 = (ArrayList<String>) getSearchResults(searchTxt);

					itemAdapter = new ArrayAdapter<String>(Returns.this,
							android.R.layout.simple_list_item_1, list1);
					listView.setAdapter(itemAdapter);

					itemAdapter.notifyDataSetChanged();

				} else if (searchTxt == "") {
					Log.v("Error: ", "Nothing to search for");

				}
			}
		});

		itemAdapter = new ArrayAdapter<String>(Returns.this,
				android.R.layout.simple_list_item_1, list1);
		listView.setAdapter(itemAdapter);

		itemAdapter.notifyDataSetChanged();

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// Log.d("Peter do u like me? ",
				// "Yes Lord, you know that I do");
				
				String requesterName = (String) arg0.getItemAtPosition(arg2);
				List<String> personReason = getPersonDetails(requesterName);
				String[] personInternalReq = {requesterName,personReason.get(0),personReason.get(1)};
				
				Intent returnDIntent = new Intent(Returns.this, ReturnDetails.class);
				returnDIntent.putExtra("myreturnsArray", personInternalReq);
				startActivity(returnDIntent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.returns, menu);
		return true;
	}

	private List<String> getReturnNames() {
		List<String> returnlist = new ArrayList<String>();

		dbHelper = new FeedReadHelper(this);
		db = dbHelper.getReadableDatabase();
		// Log.v("In the query function ", "Its owesome");
		// String selectQuery =
		// "SELECT requester FROM "+InternalRequisitionTable.TABLE_NAME;

		String selectQuery = "SELECT requester FROM "
				+ InternalRequisitionTable.TABLE_NAME
				+ " ORDER BY irn DESC LIMIT 3";

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

	private List<String> getSearchResults(String searchString) {
		List<String> returnlist = new ArrayList<String>();

		dbHelper = new FeedReadHelper(this);
		db = dbHelper.getReadableDatabase();

		String selectQuery = "SELECT requester FROM "
				+ InternalRequisitionTable.TABLE_NAME
				+ " WHERE requester LIKE '%" + searchString + "%'";

		// Log.v("writing a query", "Its owesome");
		Cursor cursor = db.rawQuery(selectQuery, null);
		// cursor.getColumnCount();
		// String[] data = null;
		Log.v("Query result", "Fake result just");
		if (cursor.moveToFirst()) {
			Log.v("In the query function", "blaaaaaash");
			do {
				Log.v("In the query function", cursor.getString(0));

				returnlist.add(cursor.getString(0));

			} while (cursor.moveToNext());

		}
		db.close();
		// return data;
		return returnlist;
	}

	private List<String> getPersonDetails(String personStr) {
		List<String> detailsLst = new ArrayList<String>();
		String resultStr = null;
		int resultInt = 0;

		dbHelper = new FeedReadHelper(this);
		db = dbHelper.getReadableDatabase();

		String selectQuery = "SELECT reason, irn FROM "
				+ InternalRequisitionTable.TABLE_NAME + " WHERE requester = '"
				+ personStr + "' ORDER BY irn DESC LIMIT 1";

		// Log.v("writing a query", "Its owesome");
		Cursor cursor = db.rawQuery(selectQuery, null);
		// cursor.getColumnCount();
		// String[] data = null;
		Log.v("Query result", "Fake result just");
		if (cursor.moveToFirst()) {
			Log.v("In the query function", "blaaaaaash");
			do {
				Log.v("In the query function", cursor.getString(0));
				
				resultStr = cursor.getString(0);
				resultInt = cursor.getInt(1);
				detailsLst.add(0, resultStr);
				detailsLst.add(1, resultInt+"");
				//detailsStr[1] = resultInt+"";

			} while (cursor.moveToNext());

		}
		//detailsStr[] = "";
		db.close();
		// return data;
		return detailsLst;
	}

}
