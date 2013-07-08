package com.xrystalgenius.quartz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.xrystalgenius.quartz.FeedReaderHelper.GoodsInDocketTable;
import com.xrystalgenius.quartz.FeedReaderHelper.InternalRequisitionDetailsTable;
import com.xrystalgenius.quartz.FeedReaderHelper.ItemTable;
import com.xrystalgenius.quartz.FeedReaderHelper.ReturnsTable;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ReturnDetails extends Activity {
	private ListView listView;
	private List<String> list1, requesterDetailsList;
	private ArrayAdapter<String> itemAdapter;
	private String personIrnStr;
	private int personIrnInt;
	private TableRow tRow;
	private ViewGroup tablelayout;
	private TextView itemTitleTv, quantityTitleTv, itemTv, quantityTv, returnsTv;
	private EditText quantityReturnedEt;
	private HashMap<String, Integer> hm;
	private Set<Entry< String,Integer> > set;
	private Iterator<Entry<String, Integer>> it;
	private String[] perIrnReq;
	private Button saveIndividualItemsBtn;
	private int x, y;

	private FeedReadHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.return_details);

		Bundle extras = getIntent().getExtras();
		perIrnReq = extras.getStringArray("myreturnsArray");// include
																		// person's
																		// irn
		personIrnStr = perIrnReq[2];
		personIrnInt = Integer.parseInt(personIrnStr);

		listView = (ListView) findViewById(android.R.id.list);

		list1 = new ArrayList<String>();
		itemAdapter = new ArrayAdapter<String>(ReturnDetails.this,
				android.R.layout.simple_list_item_1, list1);
		listView.setAdapter(itemAdapter);
		list1.add("Person : " + perIrnReq[0]);
		list1.add("Reason : " + perIrnReq[1]);
		itemAdapter.notifyDataSetChanged();

		//requesterDetailsList = getSearchResults(personIrnInt);
		hm = getSearchResults(personIrnInt);

		// Generate items borrowed + qty
		// Draw table to accomodate them
	   
			tRow= new TableRow(this);
	        tRow.setPadding(5,5,5,5);
	        itemTitleTv = new TextView(this);
	        quantityTitleTv = new TextView(this);
	        returnsTv = new TextView(this);
	        
	        itemTitleTv.setText("Item Taken ");
	        quantityTitleTv.setText(" Qty Issued ");
	        returnsTv.setText(" Qty Returned ");
	        

	        tRow.addView(itemTitleTv);
	        tRow.addView(quantityTitleTv);
	        tRow.addView(returnsTv);
	        //tRow.addView(quantityTitleTv);
	        tablelayout = (TableLayout)findViewById(R.id.rtndetailsTl2);
			tablelayout.addView(tRow);
		
		set = hm.entrySet();
		it = set.iterator();
		x=100;
		y=1000;
		while(it.hasNext()){
			Map.Entry<String, Integer> me = (Map.Entry<String, Integer>)it.next();
			
			tRow = new TableRow(this);
	        tRow.setPadding(5,5,5,5);
	        itemTv = new TextView(this);
	        quantityTv = new TextView(this);
	        quantityReturnedEt = new EditText(this);
	        quantityReturnedEt.setRawInputType(InputType.TYPE_CLASS_NUMBER);
	        quantityReturnedEt.setId(android.R.id.edit);
	        
	        saveIndividualItemsBtn = new Button(this);
	        saveIndividualItemsBtn.setId(y);
	        saveIndividualItemsBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					saveIndividualItems();
					
				}
			});
	        //quantityReceivedEt.isEnabled();
	        
	        itemTv.setText((CharSequence) me.getKey());

	        quantityTv.setText(me.getValue()+"");
	        saveIndividualItemsBtn.setText("Save");
	        
	        tRow.addView(itemTv);
	        tRow.addView(quantityTv);
	        tRow.addView(quantityReturnedEt);
	        tRow.addView(saveIndividualItemsBtn);

	        //tablelayout = (TableLayout)findViewById(R.id.rtndetailsTl2);
			tablelayout.addView(tRow);
			x++;
			
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.return_details, menu);
		return true;
	}

	private HashMap<String, Integer> getSearchResults(int searchInt) {
		HashMap<String, Integer> returnHm = new HashMap<String, Integer>();

		dbHelper = new FeedReadHelper(this);
		db = dbHelper.getReadableDatabase();

		String selectQuery = "SELECT internalrequisitiondetails.qtyissued, item.itemname FROM "
				+ InternalRequisitionDetailsTable.TABLE_NAME
				+ " LEFT OUTER JOIN "
				+ ItemTable.TABLE_NAME
				+ " ON internalrequisitiondetails.itemid = "
				+ "item.itemid"
				+ " WHERE "
				+ InternalRequisitionDetailsTable.COLUMN_NAME_IRN
				+ " = " + searchInt;

		// Log.v("writing a query", "Its owesome");
		Cursor cursor = db.rawQuery(selectQuery, null);
		// cursor.getColumnCount();
		// String[] data = null;
		Log.v("Query result", "Fake result just");
		if (cursor.moveToFirst()) {
			Log.v("In the query function", "blaaaaaash");
			do {
				Log.v(cursor.getString(0)+"", cursor.getString(1));

				//returnlist.add(cursor.getInt(0)+"");
				//returnlist.add(cursor.getString(1));
				returnHm.put(cursor.getString(1), Integer.valueOf(cursor.getInt(0)));

			} while (cursor.moveToNext());

		}
		db.close();
		// return data;
		return returnHm;
	}
	
	public void saveReturnedQty(View view){
		
		
	}
	
	private void saveIndividualItems(){
		dbHelper = new FeedReadHelper(this);
		db = dbHelper.getWritableDatabase();

		try {
			String itemText = itemTv.getText().toString();
			int returnedItemId =  getItemId(itemText);//query for itemid of the itemname
			String requesterName = perIrnReq[0];
			String returnedQtyStr = quantityReturnedEt.getText().toString();
			int returnedQty = Integer.parseInt(returnedQtyStr);

			ContentValues values = new ContentValues();
			values.put(ReturnsTable.COLUMN_NAME_RETURNED_BY, requesterName);
			values.put(ReturnsTable.COLUMN_NAME_ITEM_ID, returnedItemId);
			values.put(ReturnsTable.COLUMN_NAME_QUANTITY, returnedQty);
			// long newRowId;

			Log.d("Insert: ", "Inserting ..");
			// newRowId =
			db.insert(ReturnsTable.TABLE_NAME , null , values);
		} catch (Exception ex) {
			Log.e("Error : ", "Error on insertingData " + ex.getMessage());
			ex.printStackTrace();

		} finally {
			db.close();
		}

		// Log.v("EditText value=", delNoteEt.getText().toString());
		 Toast.makeText(getApplicationContext(),"Entries saved successfully",
		 Toast.LENGTH_LONG).show();
		
		Log.v("hey there", "Its almost getting done");
	}
	
	public int getItemId(String itemDesc){
		int itemident = 0;
		dbHelper = new FeedReadHelper(this);
		db = dbHelper.getReadableDatabase();

		String selectQuery = "SELECT itemid FROM "+ ItemTable.TABLE_NAME
				+ " WHERE itemname = '"+itemDesc+"'";

		// Log.v("writing a query", "Its owesome");
		Cursor cursor = db.rawQuery(selectQuery, null);
		// cursor.getColumnCount();
		// String[] data = null;
		Log.v("Query result", "Fake result just");
		if (cursor.moveToFirst()) {
			Log.v("In the query function", "blaaaaaash");
			do {
				Log.v("Its fine", cursor.getInt(0)+"");

				//returnlist.add(cursor.getInt(0)+"");
				//returnlist.add(cursor.getString(1));
				itemident = cursor.getInt(0);

			} while (cursor.moveToNext());

		}
		// return data;
		return itemident;
	}

}
