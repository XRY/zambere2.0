package com.xrystalgenius.quartz;

import com.xrystalgenius.quartz.FeedReaderHelper.InternalRequisitionTable;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
//import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InternalRequisition extends Activity {
	private EditText reasonEt, requesterEt;
	// private EditText departmentEt, reasonEt, requesterEt;
	private String reason, request;
	// private String department, reason, request;
	private FeedReadHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.internal_requisition);

		// departmentEt = (EditText)findViewById(R.id.departEt1);
		reasonEt = (EditText) findViewById(R.id.reasonEt1);
		requesterEt = (EditText) findViewById(R.id.requesterEt1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.internal_requisition, menu);
		return true;
	}

	public void addItemsToDepartment(View view) {
		dbHelper = new FeedReadHelper(this);
		db = dbHelper.getWritableDatabase();
		try {
			// department = departmentEt.getText().toString();
			reason = reasonEt.getText().toString();
			request = requesterEt.getText().toString();

			ContentValues values = new ContentValues();
			values.put(InternalRequisitionTable.COLUMN_NAME_DEPT_ID, 1);
			values.put(InternalRequisitionTable.COLUMN_NAME_REASON, reason);
			values.put(InternalRequisitionTable.COLUMN_NAME_REQUESTER, request);

			Log.d("Insert Irn ", "Inserting irn values....");

			db.insert(InternalRequisitionTable.TABLE_NAME, null, values);
		} catch (Exception ex) {

		} finally {
			db.close();
		}

		int val = getPersonIrn();
		Log.d("Inserting irn : ", val + " hehehehe");
		
		Toast.makeText(getApplicationContext(),"Entries saved successfully",
				 Toast.LENGTH_LONG).show();
		
		Intent irnd = new Intent(InternalRequisition.this,
				InternalRequisitionDetails.class);
		irnd.putExtra("requesterirn", val);
		startActivity(irnd);

	}

	private int getPersonIrn() {
		int personirn = 0;

		dbHelper = new FeedReadHelper(this);
		db = dbHelper.getReadableDatabase();
		Log.v("In the query function ", "Its owesome");
		String selectQuery = "SELECT irn FROM "
				+ InternalRequisitionTable.TABLE_NAME
				+ " ORDER BY irn DESC LIMIT 1";
		;
		Log.v("writing a query", "Its owesome");
		Cursor cursor = db.rawQuery(selectQuery, null);
		// cursor.getColumnCount();
		// String[] data = null;
		Log.v("Query result", "Fake result just");
		if (cursor.moveToFirst()) {
			Log.v("In the query function", "blaaaaaash");
			do {

				personirn = cursor.getInt(0);

			} while (cursor.moveToNext());

		}
		db.close();
		// return data;
		return personirn;
	}

}
