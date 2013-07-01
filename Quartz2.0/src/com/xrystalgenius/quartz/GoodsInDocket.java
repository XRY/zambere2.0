package com.xrystalgenius.quartz;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class GoodsInDocket extends Activity implements OnItemSelectedListener {
	private Spinner spSpinner, catSpinner, itSpinner;

	// private Button btnSubmit;

	// private String[] categoryArray = {"Plumbing", "Construction","Roofing"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_in_docket);

		addItemsOnCatSpinner();
		addItemsOnSpSpinner();
		addItemsOnItSpinner();
		// addListenerOnButton();
		addListenerOnSpinnerItemSelection();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.goods_in_docket, menu);

		return true;
	}

	// add items into spinner dynamically
	public void addItemsOnCatSpinner() {

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
	}

	public void addItemsOnSpSpinner() {

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
	}

	public void addItemsOnItSpinner() {

		itSpinner = (Spinner) findViewById(R.id.itemSpinner);
		List<String> list = new ArrayList<String>();
		/*
		 * list.add(""); list.add("Plumbing"); list.add("Roofing");
		 */
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
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
		if (arg1 == spSpinner) {
			// assign the supplier variable with arg2
		} else if (arg1 == catSpinner) {
			/*
			 * assign the category variable with arg2 and search for items in
			 * that category
			 */
		}

		else if (arg1 == itSpinner) {
			// assign the item variable with arg2
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
