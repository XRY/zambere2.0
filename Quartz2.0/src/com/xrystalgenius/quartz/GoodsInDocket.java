package com.xrystalgenius.quartz;

import java.util.ArrayList;
import java.util.List;

import com.xrystalgenius.quartz.FeedReaderHelper.DepartmentTable;
import com.xrystalgenius.quartz.FeedReaderHelper.GoodsInDocketTable;
import com.xrystalgenius.quartz.FeedReaderHelper.ItemTable;
import com.xrystalgenius.quartz.FeedReaderHelper.SupplierTable;

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
import android.widget.Toast;
//import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class GoodsInDocket extends Activity implements OnItemSelectedListener {
	private Spinner spSpinner, catSpinner, itSpinner;
	private EditText delNoteEt, qtyDelEt, expDateEt;
	private String deliveryNote, expiryDate, qty;
	private int quantity, supplierid, itemid;
	private FeedReadHelper dbHelper;
	private SQLiteDatabase db;

	// private Button saveBtn;

	// private Button btnSubmit;

	// private String[] categoryArray = {"Plumbing", "Construction","Roofing"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_in_docket);

		addItemsOnCatSpinner(getDepartmentNames());;
		//addItemsOnSpSpinner();
		addItemsOnSpSpinner(getSupplierNames());
		//addItemsOnItSpinner();
		// addListenerOnButton();
		addListenerOnSpinnerItemSelection();

		delNoteEt = (EditText) findViewById(R.id.delNoteET);
		qtyDelEt = (EditText) findViewById(R.id.qtyDeliveredET);
		expDateEt = (EditText) findViewById(R.id.expiryDateET);

		// saveBtn = (Button)findViewById(R.id.savebtn);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.goods_in_docket, menu);

		return true;
	}

	// add items into spinner dynamically
	/*private void addItemsOnCatSpinner() {

		catSpinner = (Spinner) findViewById(R.id.categorySpinner);
		List<String> list = new ArrayList<String>();
		list.add("Construction");
		list.add("Plumbing");
		list.add("Roofing");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		catSpinner.setAdapter(dataAdapter);
	}*/
	
	private void addItemsOnCatSpinner(List<String> itResult) {

		catSpinner = (Spinner) findViewById(R.id.categorySpinner);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, itResult);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		catSpinner.setAdapter(dataAdapter);
	}

	/*private void addItemsOnSpSpinner() {

		spSpinner = (Spinner) findViewById(R.id.supplierSpinner);
		List<String> list = new ArrayList<String>();
		list.add("Uganda Martyrs Hardware");
		list.add("Roofings Ltd");
		list.add("Hima");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spSpinner.setAdapter(dataAdapter);
	}*/
	
	private void addItemsOnSpSpinner(List<String> itResult) {

		spSpinner = (Spinner) findViewById(R.id.supplierSpinner);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, itResult);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spSpinner.setAdapter(dataAdapter);
	}

	private void addItemsOnItSpinner(List<String> itResult) {

		itSpinner = (Spinner) findViewById(R.id.itemSpinner);
		//List<String> list = new ArrayList<String>();
		/*
		 * list.add(""); list.add("Plumbing"); list.add("Roofing");
		 */
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, itResult);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		itSpinner.setAdapter(dataAdapter);
	}

	private void addListenerOnSpinnerItemSelection() {
		spSpinner = (Spinner) findViewById(R.id.supplierSpinner);
		catSpinner = (Spinner) findViewById(R.id.categorySpinner);
		itSpinner = (Spinner) findViewById(R.id.itemSpinner);
		spSpinner.setOnItemSelectedListener(this);
		catSpinner.setOnItemSelectedListener(this);
		itSpinner.setOnItemSelectedListener(this);

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		//Log.v("outside having fun ", arg0.toString());
		//Log.v("outside having fun ", spSpinner.toString());
		if (arg0 == spSpinner) {
			// assign the supplier variable with arg2
			
			Log.v("Supplier Selected ", "Its owesome");
			
			supplierid = arg2;

		} else if (arg0 == catSpinner) {
			
			Log.v("Department selected ", "Its owesome");
			/*
			 * assign the category variable with arg2 and search for items in
			 * that category
			 */
			//departmentid = arg2;
			Log.v(arg0.toString(), arg2+"sup?");
			
			addItemsOnItSpinner(getItemNames(arg2));

			// String[] projection = {ItemTable.COLUMN_NAME_ITEM_NAME};
			// String sortOrder = ItemTable.COLUMN_NAME_TABLE_Id + " ASC";
			/*
			 * Cursor cursor = db.query( ItemTable.TABLE_NAME, projection,
			 * ItemTable.COLUMN_NAME_DEPT_ID, arg2, null, null, sortOrder);
			 */

		}

		else if (arg0 == itSpinner) {
			Log.v("Item selected ", "Its owesome");
			// assign the item variable with arg2
			itemid = arg2;
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	public void saveEntries(View view) {
		dbHelper = new FeedReadHelper(this);
		db = dbHelper.getWritableDatabase();

		try {
			deliveryNote = delNoteEt.getText().toString();
			expiryDate = expDateEt.getText().toString();
			qty = qtyDelEt.getText().toString();
			quantity = Integer.parseInt(qty);

			ContentValues values = new ContentValues();
			values.put(GoodsInDocketTable.COLUMN_NAME_SUPPLIER_ID, (supplierid + 1));
			values.put(GoodsInDocketTable.COLUMN_NAME_ITEM_ID, (itemid + 1));
			values.put(GoodsInDocketTable.COLUMN_NAME_QUATITY_REQUESTED,
					quantity);
			values.put(GoodsInDocketTable.COLUMN_NAME_DELIVERY_NOTE_NUMBER,
					deliveryNote);
			values.put(GoodsInDocketTable.COLUMN_NAME_EXPIRY_DATE, expiryDate);
			// long newRowId;

			Log.d("Insert: ", "Inserting ..");
			// newRowId =
			db.insert(GoodsInDocketTable.TABLE_NAME,
					GoodsInDocketTable.COLUMN_NAME_EXPIRY_DATE, values);
		} catch (Exception ex) {
			Log.e("Error : ", "Error on insertingData " + ex.getMessage());
			ex.printStackTrace();

		} finally {
			db.close();
		}

		// Log.v("EditText value=", delNoteEt.getText().toString());
		 Toast.makeText(getApplicationContext(),"Entries saved successfully",
		 Toast.LENGTH_LONG).show();

	}
	
	private List<String> getSupplierNames() {
		List<String> suplist = new ArrayList<String>();

		dbHelper = new FeedReadHelper(this);
		db = dbHelper.getReadableDatabase();
		Log.v("In the query function ", "Its owesome");
		String selectQuery = "SELECT suppliername FROM "+SupplierTable.TABLE_NAME;
		Log.v("writing a query", "Its owesome");
		Cursor cursor = db.rawQuery(selectQuery, null);
		// cursor.getColumnCount();
		// String[] data = null;
		Log.v("Query result", "Fake result just");
		if (cursor.moveToFirst()) {
			Log.v("In the query function", "blaaaaaash");
			do {
				Log.v("In the query function", "waaaaaat");

				suplist.add(cursor.getString(0));

			} while (cursor.moveToNext());

		}
		db.close();
		// return data;
		return suplist;
	}
	
	private List<String> getDepartmentNames() {
		List<String> deptlist = new ArrayList<String>();

		dbHelper = new FeedReadHelper(this);
		db = dbHelper.getReadableDatabase();
		Log.v("In the query function ", "Its owesome");
		String selectQuery = "SELECT deptname FROM "+DepartmentTable.TABLE_NAME;
		Log.v("writing a query", "Its owesome");
		Cursor cursor = db.rawQuery(selectQuery, null);
		// cursor.getColumnCount();
		// String[] data = null;
		Log.v("Query result", "Fake result just");
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
		String selectQuery = "SELECT itemname FROM "+ItemTable.TABLE_NAME
				+ " WHERE deptid = " + (selection + 1);
		Log.v("writing a query", "Its owesome");
		Cursor cursor = db.rawQuery(selectQuery, null);
		//cursor.getColumnCount();
		//String[] data = null;
		Log.v("Query result", "Fake result just");
		if (cursor.moveToFirst()) {
			Log.v("In the query function", "blaaaaaash");
			do {
				
				list.add(cursor.getString(0));
				

			} while (cursor.moveToNext());

		}
		db.close();
		//return data;
		return list;
	}

}
