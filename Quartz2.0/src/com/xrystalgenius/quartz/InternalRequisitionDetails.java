package com.xrystalgenius.quartz;

import java.util.ArrayList;
import java.util.List;

import com.xrystalgenius.quartz.FeedReaderHelper.DepartmentTable;
import com.xrystalgenius.quartz.FeedReaderHelper.ItemTable;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class InternalRequisitionDetails extends Activity{
	private EditText qtyRequestedEt, qtyIssuedEt;
	private Spinner catSpinner, itSpinner;
	private int itemid, quantityRequested, quantityIssued;
	
	
	private FeedReadHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.internal_requisition_details);

		// addItemsOnCatSpinner();
		addItemsOnCatSpinner(getDepartmentNames());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.internal_requisition_details, menu);

		return true;
	}

	private void addItemsOnCatSpinner(List<String> itResult) {

		catSpinner = (Spinner) findViewById(R.id.departmentSp2);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, itResult);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		catSpinner.setAdapter(dataAdapter);
		
		catSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				Log.v(arg0.toString(), arg2 + " sup?");

				addItemsOnItSpinner(getItemNames(arg2));
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void addItemsOnItSpinner(List<String> itResult) {

		itSpinner = (Spinner) findViewById(R.id.itemSp2);
		// List<String> list = new ArrayList<String>();
		/*
		 * list.add(""); list.add("Plumbing"); list.add("Roofing");
		 */
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, itResult);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		itSpinner.setAdapter(dataAdapter);
		
		itSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

/*	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		
		Log.v("Something selected", arg2 + " weweweweweweeeeeeeeeewe?");
		
		if (arg0 == catSpinner) {

			Log.v(arg0.toString(), arg2 + " sup?");

			addItemsOnItSpinner(getItemNames(arg2));

		}
		
		else if(arg0 == itSpinner){
			
		}

	}*/

	/*@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		Log.v("Playing is my hobby", "I like it");
		if(arg0 == catSpinner){
			Log.v("Playing is not my hobby", "I hate it");
		}
	}*/
	
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

	private List<String> getItemNames(int selection) {
		List<String> list = new ArrayList<String>();

		dbHelper = new FeedReadHelper(this);
		db = dbHelper.getReadableDatabase();
		Log.v("In the query function ", "Its owesome");
		String selectQuery = "SELECT itemname FROM " + ItemTable.TABLE_NAME
				+ " WHERE deptid = " + (selection + 1);
		Log.v("writing a query", "Its owesome");
		Cursor cursor = db.rawQuery(selectQuery, null);
		// cursor.getColumnCount();
		// String[] data = null;
		Log.v("Query result", "Fake result just");
		if (cursor.moveToFirst()) {
			Log.v("In the query function", "blaaaaaash");
			do {

				list.add(cursor.getString(0));

			} while (cursor.moveToNext());

		}
		db.close();
		// return data;
		return list;
	}
}
