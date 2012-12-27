package com.davidtunnell.deliverypal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends SharedPref {

	public static TextView[] homeUpdateArray;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_layout);
		connectVariables();
		populateFields();
	}

	private void populateFields() {
		// retrieve data from shared preferences and populated edit text fields
		homeUpdateArray[0]
				.setText(String.format("%.2f", Double.parseDouble(getSavedData(
						homeHourlyNoGas, HomeActivity.this))));
		homeUpdateArray[1].setText(String.format("%.2f", Double
				.parseDouble(getSavedData(homeHourlyWage, HomeActivity.this))));
		homeUpdateArray[2].setText(String.format("%.2f", Double
				.parseDouble(getSavedData(homeTotalTips, HomeActivity.this))));
		homeUpdateArray[3].setText(String.format("%.2f", Double
				.parseDouble(getSavedData(homeTotalHours, HomeActivity.this))));
		homeUpdateArray[4]
				.setText(String.format("%.2f", Double.parseDouble(getSavedData(
						homeTotalIncome, HomeActivity.this))));
	}

	private void connectVariables() {
		// Connect XML layout with Java
		homeUpdateArray = new TextView[] { (TextView) findViewById(R.id.Home1),
				(TextView) findViewById(R.id.Home2),
				(TextView) findViewById(R.id.Home3),
				(TextView) findViewById(R.id.Home4),
				(TextView) findViewById(R.id.Home5) };
		Button historyButton = (Button) findViewById(R.id.HistoryButton);
		Button calcButton = (Button) findViewById(R.id.CalcButton);
		historyButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// open history activity when history button is checked
				Intent i = new Intent("com.davidtunnell.deliverypal.HISTORY");
				startActivity(i);
			}
		});
		calcButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// open default calculator when calculator button is checked
				Intent calcIntent = new Intent();
				calcIntent.setClassName("com.android.calculator2",
						"com.android.calculator2.Calculator");
				startActivity(calcIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Options menu pop-up
		super.onCreateOptionsMenu(menu);
		MenuInflater mf = getMenuInflater();
		mf.inflate(R.layout.pref_pop, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Select activity to launch based on selection from options menu
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