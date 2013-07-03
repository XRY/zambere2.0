package com.xrystalgenius.quartz;

import com.xrystalgenius.quartz.FeedReaderHelper.InternalRequisitionTable;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
//import android.widget.Button;
import android.widget.EditText;

public class InternalRequisition extends Activity {
	private EditText reasonEt, requesterEt;
	//private EditText departmentEt, reasonEt, requesterEt;
	private String reason, request;
	//private String department, reason, request;
	private FeedReadHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.internal_requisition);
		
		//departmentEt = (EditText)findViewById(R.id.departEt1);
		reasonEt = (EditText)findViewById(R.id.reasonEt1);
		requesterEt = (EditText)findViewById(R.id.requesterEt1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.internal_requisition, menu);
		return true;
	}
	
	public void addItemsToDepartment(View view){
		dbHelper = new FeedReadHelper(this);
		db = dbHelper.getWritableDatabase();
		try{
			//department = departmentEt.getText().toString();
			reason = reasonEt.getText().toString();
			request = requesterEt.getText().toString();

			ContentValues values = new ContentValues();
			values.put(InternalRequisitionTable.COLUMN_NAME_DEPT_ID, 1);
			values.put(InternalRequisitionTable.COLUMN_NAME_REASON, reason);
			values.put(InternalRequisitionTable.COLUMN_NAME_REQUESTER, request);
			
			Log.d("Insert Irn ", "Inserting irn values....");
			
			db.insert(InternalRequisitionTable.TABLE_NAME, null, values);
		}
		catch(Exception ex){
			
		}
		finally{
			db.close();
		}
		
		Intent irnd = new Intent(InternalRequisition.this, InternalRequisitionDetails.class);
		//irnd.putExtra("Person irn", 1);
		startActivity(irnd);
		
	}

}
