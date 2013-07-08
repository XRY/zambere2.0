package com.xrystalgenius.quartz;

import java.util.ArrayList;
import java.util.List;

import com.xrystalgenius.quartz.FeedReaderHelper.DepartmentTable;
import com.xrystalgenius.quartz.FeedReaderHelper.InternalRequisitionDetailsTable;
import com.xrystalgenius.quartz.FeedReaderHelper.ItemTable;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
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
import android.widget.Toast;

public class InternalRequisitionDetails extends Activity{
	private EditText qtyRequestedEt, qtyIssuedEt;
	private Spinner catSpinner, itSpinner;
	private int itemid, quantityRequested, quantityIssued, personIrn;
	private String qtyRequested, qtyIssued;
	
	private FeedReadHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.internal_requisition_details);
		
		qtyRequestedEt = (EditText)findViewById(R.id.irndQtyRequestedEt2);
		qtyIssuedEt = (EditText)findViewById(R.id.irndQtyIssuedEt2);
		
		Bundle extras = getIntent().getExtras();
		personIrn = extras.getInt("requesterirn");
		
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
				itemid = arg2 + 1;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
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
	
	public void saveIrnDetails(View view){
		dbHelper = new FeedReadHelper(this);
		db = dbHelper.getWritableDatabase();
		try {
			// department = departmentEt.getText().toString();
			qtyRequested = qtyRequestedEt.getText().toString();
			qtyIssued = qtyIssuedEt.getText().toString();
			
			quantityRequested = Integer.parseInt(qtyRequested);
			quantityIssued = Integer.parseInt(qtyIssued);

			ContentValues values = new ContentValues();
			values.put(InternalRequisitionDetailsTable.COLUMN_NAME_IRN, personIrn);
			values.put(InternalRequisitionDetailsTable.COLUMN_NAME_ITEM_ID, itemid);
			values.put(InternalRequisitionDetailsTable.COLUMN_NAME_QUANTITY_REQUESTED, quantityRequested);
			values.put(InternalRequisitionDetailsTable.COLUMN_NAME_QUANTITY_ISSUED, quantityIssued);

			Log.d("Insert Irn ", "Inserting irn values....");

			db.insert(InternalRequisitionDetailsTable.TABLE_NAME, null, values);
		} catch (Exception ex) {

		} finally {
			db.close();
		}
		
		Toast.makeText(getApplicationContext(),"Entries saved successfully",
				 Toast.LENGTH_LONG).show();
	}
}
