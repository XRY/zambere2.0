package com.xrystalgenius.quartz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.xrystalgenius.quartz.FeedReaderHelper.InternalRequisitionTable;
import com.xrystalgenius.quartz.FeedReaderHelper.StockCardTable;
import com.xrystalgenius.quartz.FeedReaderHelper.SupplierTable;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Summary extends Activity {
	private ListView listView;
	private Button searchBtn, backBtn;
	private EditText fromEt, toEt;
	private DatePicker datePicker;
	private AutoCompleteTextView itemAcTv;

	private TableRow tableRow;
	private TextView dateTitleTv, inChargeTitleTv, typeTitleTv, qtyTitleTv,
			dateTv, inChargeTv, typeTv, qtyTv;
	private TableLayout tablelayout;

	private ArrayList<String> list1;
	private ArrayAdapter<String> itemAdapter;
	private Calendar calendar;
	private Set set;
	private Iterator it;

	private FeedReadHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);

		itemAcTv = (AutoCompleteTextView) findViewById(R.id.summaryItemAcTv1);
		searchBtn = (Button) findViewById(R.id.summarySearchBtn1);
		backBtn = (Button) findViewById(R.id.summaryBackBtn1);
		fromEt = (EditText) findViewById(R.id.summaryFromEt1);
		toEt = (EditText) findViewById(R.id.summaryToEt1);

		String[] names = { "Alex", "Allan", "Allen", "Albert", "Ann", "Ben",
				"Benedict", "Benon", "Balliks", "Cameroon", "Carol", "Chan" };
		final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, names);

		itemAcTv.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				itemAcTv.setAdapter(adapter1);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				itemAcTv.setAdapter(adapter1);
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				itemAcTv.setAdapter(adapter1);
			}
		});

		/*
		 * itemAcTv.addTextChangedListener(new TextWatcher() {
		 * 
		 * @Override public void onTextChanged(CharSequence s, int start, int
		 * before, int count) { // TODO Auto-generated method stub
		 * 
		 * }
		 * 
		 * @Override public void beforeTextChanged(CharSequence s, int start,
		 * int count, int after) { // TODO Auto-generated method stub
		 * 
		 * }
		 * 
		 * @Override public void afterTextChanged(Editable s) { // TODO
		 * Auto-generated method stub
		 * 
		 * } });
		 */

		fromEt.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				showDatePickerDialog();

			}
		});

		toEt.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				showDatePickerDialog2();

			}
		});

		TableRow tableRow = new TableRow(this);
		tableRow.setPadding(5, 5, 5, 5);
		dateTitleTv = new TextView(this);
		inChargeTitleTv = new TextView(this);
		typeTitleTv = new TextView(this);
		qtyTitleTv = new TextView(this);

		dateTitleTv.setText("Date ");
		inChargeTitleTv.setText(" Incharge ");
		typeTitleTv.setText(" Type ");
		qtyTitleTv.setText(" Quantity");

		tableRow.addView(dateTitleTv);
		tableRow.addView(inChargeTitleTv);
		tableRow.addView(typeTitleTv);
		tableRow.addView(qtyTitleTv);
		tablelayout = (TableLayout) findViewById(R.id.summaryTb1);
		tablelayout.addView(tableRow);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	private void showDatePickerDialog() {
		calendar = Calendar.getInstance();
		DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				calendar.set(Calendar.YEAR, year);
				calendar.set(Calendar.MONTH, monthOfYear);
				calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				if (monthOfYear < 9 && dayOfMonth < 9) {
					fromEt.setText(new StringBuilder().append(year).append("-")
							.append("0" + (monthOfYear + 1)).append("-")
							.append("0" + dayOfMonth));
				} else if (monthOfYear < 9 && dayOfMonth > 9) {
					fromEt.setText(new StringBuilder().append(year).append("-")
							.append("0" + (monthOfYear + 1)).append("-")
							.append(dayOfMonth));
				} else if (monthOfYear > 9 && dayOfMonth < 9) {
					fromEt.setText(new StringBuilder().append(year).append("-")
							.append(monthOfYear + 1).append("-")
							.append("0" + dayOfMonth));
				} else {
					fromEt.setText(new StringBuilder().append(year).append("-")
							.append(monthOfYear + 1).append("-")
							.append(dayOfMonth).append(" "));

				}
				// set selected date into datepicker also
				// listener.init(year, monthOfYear, dayOfMonth, null);

			}
		};

		DatePickerDialog dialog = new DatePickerDialog(this, listener,
				calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));
		// setDatePickerDialogRange(dialog);
		dialog.show();
	}

	private void showDatePickerDialog2() {
		calendar = Calendar.getInstance();
		DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				calendar.set(Calendar.YEAR, year);
				calendar.set(Calendar.MONTH, monthOfYear);
				calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				if (monthOfYear < 9 && dayOfMonth < 9) {
					toEt.setText(new StringBuilder().append(year).append("-")
							.append("0" + (monthOfYear + 1)).append("-")
							.append("0" + dayOfMonth));
				} else if (monthOfYear < 9 && dayOfMonth > 9) {
					toEt.setText(new StringBuilder().append(year).append("-")
							.append("0" + (monthOfYear + 1)).append("-")
							.append(dayOfMonth));
				} else if (monthOfYear > 9 && dayOfMonth < 9) {
					toEt.setText(new StringBuilder().append(year).append("-")
							.append(monthOfYear + 1).append("-")
							.append("0" + dayOfMonth));
				} else {
					toEt.setText(new StringBuilder().append(year).append("-")
							.append(monthOfYear + 1).append("-")
							.append(dayOfMonth).append(" "));

				}

				// set selected date into datepicker also
				// listener.init(year, monthOfYear, dayOfMonth, null);

			}
		};

		DatePickerDialog dialog = new DatePickerDialog(this, listener,
				calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));
		// setDatePickerDialogRange(dialog);
		dialog.show();
	}

	public void getItemDetailsInThatDuration(View view) {
		String myFirstDate = fromEt.getText().toString();
		String mySecondDate = toEt.getText().toString();

		HashMap<String, String> hm = getItemDetails(myFirstDate, mySecondDate);
		set = hm.entrySet();
		it = set.iterator();

		set = hm.entrySet();
		it = set.iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> me = (Map.Entry<String, String>) it
					.next();
			Log.v("It is finished", "am a hero");
			tableRow = new TableRow(this);
			tableRow.setPadding(5, 5, 5, 5);
			dateTv = new TextView(this);
			inChargeTv = new TextView(this);
			typeTv = new TextView(this);
			qtyTv = new TextView(this);

			String dateACharge = me.getKey();
			String[] dateNCharge = dateACharge.split("#");
			dateTv.setText(dateNCharge[0]);
			inChargeTv.setText(dateNCharge[1]);

			String typeAQty = me.getValue();
			String[] typeNQty = typeAQty.split("#");
			typeTv.setText(typeNQty[0]);
			qtyTv.setText(typeNQty[1]);

			tableRow.addView(dateTv);
			tableRow.addView(inChargeTv);
			tableRow.addView(typeTv);
			tableRow.addView(qtyTv);
			tablelayout.addView(tableRow);
		}
	}

	private HashMap<String, String> getItemDetails(String date1, String date2) {
		HashMap<String, String> detailsHm = new HashMap<String, String>();
		String dateStr, typeStr, inChargeStr;
		int qtyInt, stcInt;
		dbHelper = new FeedReadHelper(this);
		db = dbHelper.getReadableDatabase();
		// Calendar cal = Calendar.getInstance();

		String selectQuery = "SELECT stockcard.quantity, stockcard.type, stockcard.date,"
				+ " stockcard.stcid, supplier.suppliername FROM "	+ StockCardTable.TABLE_NAME
				+ " LEFT OUTER JOIN "+ SupplierTable.TABLE_NAME
				+ " ON stockcard.supplierid = supplier.supplierid "
				+ "WHERE date BETWEEN '" + date1 + "' AND '" + date2 + "'";
		// +"WHERE itemid = "itemIntr;

		// Log.v("writing a query", "Its owesome");
		Cursor cursor = db.rawQuery(selectQuery, null);

		// cursor.getColumnCount();
		// String[] data = null;
		Log.v("Query result", "Fake result just");
		if (cursor.moveToFirst()) {
			Log.v("In the query function", "blaaaaaash");
			do {
				Log.v("In the query function", "waat "+cursor.getString(4));
				dateStr = cursor.getString(2);
				typeStr = cursor.getString(1);
				qtyInt = cursor.getInt(0);
				inChargeStr = cursor.getString(4);
				stcInt = cursor.getInt(3); 
				if(inChargeStr == null){
					inChargeStr = getPersonInCharge(stcInt);
				}

				detailsHm.put(dateStr + "#" + inChargeStr, (typeStr + "#" + qtyInt + ""));

			} while (cursor.moveToNext());

		}
		// detailsHm.put(key, value);
		db.close();
		// return data;
		return detailsHm;
	}

	private List<String> getAvailableItems() {
		List<String> detailsLst = new ArrayList<String>();

		dbHelper = new FeedReadHelper(this);
		db = dbHelper.getReadableDatabase();

		String selectQuery = "SELECT itemname FROM item";

		// Log.v("writing a query", "Its owesome");
		Cursor cursor = db.rawQuery(selectQuery, null);
		// cursor.getColumnCount();
		// String[] data = null;
		Log.v("Query result", "Fake result just");
		if (cursor.moveToFirst()) {
			Log.v("In the query function", "blaaaaaash");
			do {
				Log.v("In the query function", cursor.getString(0));

				detailsLst.add(cursor.getString(0));
				// detailsStr[1] = resultInt+"";

			} while (cursor.moveToNext());

		}
		// detailsStr[] = "";
		db.close();
		// return data;
		return detailsLst;
	}
	
	private String getPersonInCharge(int stcdInt) {
		String inChargePer = null;

		dbHelper = new FeedReadHelper(this);
		db = dbHelper.getReadableDatabase();
		Log.v("I enter this", "watch out");
		String selectQuery1 = "SELECT requestername FROM "
				+ StockCardTable.TABLE_NAME + " WHERE stcid = "+stcdInt;
		Cursor cursor = db.rawQuery(selectQuery1, null);
		Log.v("Query result", "Fake result just");
		if (cursor.moveToFirst()) {
			Log.v("In the query function", "blaaaaaash");
			do {
				Log.v("In the query function", cursor.getString(0));

				inChargePer = cursor.getString(0);
				// detailsStr[1] = resultInt+"";

			} while (cursor.moveToNext());

		}
		// detailsStr[] = "";
		db.close();
		// return data;
		return inChargePer;
	}

}
