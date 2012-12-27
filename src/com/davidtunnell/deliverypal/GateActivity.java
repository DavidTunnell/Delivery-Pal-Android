package com.davidtunnell.deliverypal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GateActivity extends SharedPref {

	EditText[] nameArray, codeArray;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gate_layout);
		connectVariables();
		populateFields();

	}

	private void connectVariables() {
		// Connect XML layout with Java
		nameArray = new EditText[] { (EditText) findViewById(R.id.subName1),
				(EditText) findViewById(R.id.subName2),
				(EditText) findViewById(R.id.subName3),
				(EditText) findViewById(R.id.subName4),
				(EditText) findViewById(R.id.subName5),
				(EditText) findViewById(R.id.subName6),
				(EditText) findViewById(R.id.subName7),
				(EditText) findViewById(R.id.subName8),
				(EditText) findViewById(R.id.subName9),
				(EditText) findViewById(R.id.subName10),
				(EditText) findViewById(R.id.subName11),
				(EditText) findViewById(R.id.subName12),
				(EditText) findViewById(R.id.subName13),
				(EditText) findViewById(R.id.subName14),
				(EditText) findViewById(R.id.subName15),
				(EditText) findViewById(R.id.subName16),
				(EditText) findViewById(R.id.subName17),
				(EditText) findViewById(R.id.subName18),
				(EditText) findViewById(R.id.subName19),
				(EditText) findViewById(R.id.subName20),
				(EditText) findViewById(R.id.subName21),
				(EditText) findViewById(R.id.subName22),
				(EditText) findViewById(R.id.subName23),
				(EditText) findViewById(R.id.subName24),
				(EditText) findViewById(R.id.subName25),
				(EditText) findViewById(R.id.subName26),
				(EditText) findViewById(R.id.subName27),
				(EditText) findViewById(R.id.subName28),
				(EditText) findViewById(R.id.subName29),
				(EditText) findViewById(R.id.subName30), };

		codeArray = new EditText[] { (EditText) findViewById(R.id.subCode1),
				(EditText) findViewById(R.id.subCode2),
				(EditText) findViewById(R.id.subCode3),
				(EditText) findViewById(R.id.subCode4),
				(EditText) findViewById(R.id.subCode5),
				(EditText) findViewById(R.id.subCode6),
				(EditText) findViewById(R.id.subCode7),
				(EditText) findViewById(R.id.subCode8),
				(EditText) findViewById(R.id.subCode9),
				(EditText) findViewById(R.id.subCode10),
				(EditText) findViewById(R.id.subCode11),
				(EditText) findViewById(R.id.subCode12),
				(EditText) findViewById(R.id.subCode13),
				(EditText) findViewById(R.id.subCode14),
				(EditText) findViewById(R.id.subCode15),
				(EditText) findViewById(R.id.subCode16),
				(EditText) findViewById(R.id.subCode17),
				(EditText) findViewById(R.id.subCode18),
				(EditText) findViewById(R.id.subCode19),
				(EditText) findViewById(R.id.subCode20),
				(EditText) findViewById(R.id.subCode21),
				(EditText) findViewById(R.id.subCode22),
				(EditText) findViewById(R.id.subCode23),
				(EditText) findViewById(R.id.subCode24),
				(EditText) findViewById(R.id.subCode25),
				(EditText) findViewById(R.id.subCode26),
				(EditText) findViewById(R.id.subCode27),
				(EditText) findViewById(R.id.subCode28),
				(EditText) findViewById(R.id.subCode29),
				(EditText) findViewById(R.id.subCode30) };

		Button save = (Button) findViewById(R.id.saveButton);
		// set on click listener
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// save edit text fields
				saveEditTextArray(subName, nameArray, null);
				saveEditTextArray(subCode, codeArray, null);
				// tell user they are saved
				Toast.makeText(getApplicationContext(), "Saved",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	//
	private void populateFields() {
		// retrieve data from shared preferences and populated edit text fields
		getSavedEditTextArray(subName, nameArray);
		getSavedEditTextArray(subCode, codeArray);
	}

	// create an inflatable preferences menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater mf = getMenuInflater();
		mf.inflate(R.layout.pref_pop, menu);
		return true;
	}

	// open activities based on selection
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.about:
			Intent i = new Intent("com.davidtunnell.deliverypal.ABOUT");
			startActivity(i);
			break;
		case R.id.help:
			Intent i2 = new Intent("com.davidtunnell.deliverypal.TUTORIAL");
			startActivity(i2);
			break;
		case R.id.preferences:
			Intent i3 = new Intent("com.davidtunnell.deliverypal.PREFERENCES");
			startActivity(i3);
			break;
		}
		return false;
	}

}