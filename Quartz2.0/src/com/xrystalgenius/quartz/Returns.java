package com.xrystalgenius.quartz;

import java.util.ArrayList;
import java.util.List;

import com.xrystalgenius.quartz.FeedReaderHelper.DepartmentTable;

import android.os.Bundle;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Returns extends ListActivity {
	private ListView listView;
	private Button searchBtn, saveBtn;
	private ArrayList<String> list1;
	private ArrayAdapter<String> itemAdapter;
	
	private FeedReadHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_returns);
		
		listView = (ListView)findViewById(android.R.id.list);
		
		list1 = new ArrayList<String>();
		
		itemAdapter = new ArrayAdapter<String>(Returns.this, android.R.layout.simple_list_item_1, list1);
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
		getMenuInflater().inflate(R.menu.returns, menu);
		return true;
	}
	
	private List<String> getDepartmentNames() {
		List<String> deptlist = new ArrayList<String>();

		dbHelper = new FeedReadHelper(this);
		db = dbHelper.getReadableDatabase();
		//Log.v("In the query function ", "Its owesome");
		String selectQuery = "SELECT deptname FROM "+DepartmentTable.TABLE_NAME;
		//Log.v("writing a query", "Its owesome");
		Cursor cursor = db.rawQuery(selectQuery, null);
		// cursor.getColumnCount();
		// String[] data = null;
		//Log.v("Query result", "Fake result just");
		if (cursor.moveToFirst()) {
			Log.v("In the query function", "blaaaaaash");
			do {
				Log.v("In the query function", "waaaaaat");

				deptlist.add(cursor.getString(0));

			} while (cursor.moveToNext());

		}
		db.close();
		// return data;
		return deptlist;
	}

}
