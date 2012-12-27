package com.davidtunnell.deliverypal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WageActivity extends ManipulateInput {
	public static EditText[] tipsArray;
	EditText[] hoursArray, gasArray;
	Button save, finish;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wage_layout);
		connectVariables();
		populateFields();
	}

	private void connectVariables() {
		// Connect XML layout with Java
		tipsArray = new EditText[] { (EditText) findViewById(R.id.tipBox1),
				(EditText) findViewById(R.id.tipBox2),
				(EditText) findViewById(R.id.tipBox3),
				(EditText) findViewById(R.id.tipBox4),
				(EditText) findViewById(R.id.tipBox5),
				(EditText) findViewById(R.id.tipBox6),
				(EditText) findViewById(R.id.tipBox7) };
		hoursArray = new EditText[] { (EditText) findViewById(R.id.hoursBox1),
				(EditText) findViewById(R.id.hoursBox2),
				(EditText) findViewById(R.id.hoursBox3),
				(EditText) findViewById(R.id.hoursBox4),
				(EditText) findViewById(R.id.hoursBox5),
				(EditText) findViewById(R.id.hoursBox6),
				(EditText) findViewById(R.id.hoursBox7) };
		gasArray = new EditText[] { (EditText) findViewById(R.id.gasBox1),
				(EditText) findViewById(R.id.gasBox2),
				(EditText) findViewById(R.id.gasBox3),
				(EditText) findViewById(R.id.gasBox4),
				(EditText) findViewById(R.id.gasBox5),
				(EditText) findViewById(R.id.gasBox6),
				(EditText) findViewById(R.id.gasBox7) };
		save = (Button) findViewById(R.id.SaveBut);
		finish = (Button) findViewById(R.id.FinishBut);
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Calculate sums from ManipulateInput.java
				double tipSum = ErrorCheckSum(tipsArray);
				double hoursSum = ErrorCheckSum(hoursArray);
				double gasSum = ErrorCheckSum(gasArray);
				// Save EditText fields
				saveEditTextArray(tipSave, tipsArray, null);
				saveEditTextArray(hoursSave, hoursArray, null);
				saveEditTextArray(gasSave, gasArray, null);

				// Prevent user from dividing by zero, if so alert
				if (hoursSum <= 0) {
					hideSoftwareKeyboard();
					Toast.makeText(getApplicationContext(),
							"Please enter how many how many hours you worked.",
							Toast.LENGTH_LONG).show();
				} else {
					hideSoftwareKeyboard();
					// Set view to home page
					TabLayoutActivity.tabHostObj.setCurrentTab(2);
					// Make calculations from ManipulateInput.java
					double totalIncome = totalIncomeCalc(hoursSum, tipSum);
					double hourlyBeforeGas = hourlyWageCalc(hoursSum, tipSum);
					double hourlyAfterGas = hourlyWageLessGasCalc(hoursSum,
							tipSum, gasSum);
					// Save and publish results to home page
					saveToSP(homeHourlyNoGas, Double.toString(hourlyBeforeGas),
							WageActivity.this);
					saveToSP(homeHourlyWage, Double.toString(hourlyAfterGas),
							WageActivity.this);
					saveToSP(homeTotalTips, Double.toString(tipSum),
							WageActivity.this);
					saveToSP(homeTotalHours, Double.toString(hoursSum),
							WageActivity.this);
					saveToSP(homeTotalIncome, Double.toString(totalIncome),
							WageActivity.this);
					HomeActivity.homeUpdateArray[0].setText(String.format(
							"%.2f", hourlyBeforeGas));
					HomeActivity.homeUpdateArray[1].setText(String.format(
							"%.2f", hourlyAfterGas));
					HomeActivity.homeUpdateArray[2].setText(String.format(
							"%.2f", tipSum));
					HomeActivity.homeUpdateArray[3].setText(String.format(
							"%.2f", hoursSum));
					HomeActivity.homeUpdateArray[4].setText(String.format(
							"%.2f", totalIncome));
				}
			}
		});
		finish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// pull counter from shared preferences to be used to correctly
				// order they dynamic history activity
				int histCounter = Integer.parseInt(getSavedData(historyCounter,
						WageActivity.this));
				// these strings are used as new shared preference slots/keys
				String histSave = historySave + histCounter;
				String dateSave = calenderSave + histCounter;
				// saves date in Month, day, year format from method in
				// ManipulateInput.java
				saveToSP(dateSave, findDate(), WageActivity.this);
				// Sum while checking for errors of Edit Texts in activity
				double tipSum = ErrorCheckSum(tipsArray);
				double hoursSum = ErrorCheckSum(hoursArray);
				double gasSum = ErrorCheckSum(gasArray);
				// ensure user isn't trying to divide by zero, if so prompt them
				// to fix
				if (hoursSum <= 0) {
					hideSoftwareKeyboard();
					Toast.makeText(getApplicationContext(),
							"Please enter how many how many hours you worked.",
							Toast.LENGTH_LONG).show();
				} else {
					hideSoftwareKeyboard();
					// set current tab view to home page
					TabLayoutActivity.tabHostObj.setCurrentTab(2);
					// make calculations to be used
					double totalIncome = totalIncomeCalc(hoursSum, tipSum);
					double hourlyBeforeGas = hourlyWageCalc(hoursSum, tipSum);
					double hourlyAfterGas = hourlyWageLessGasCalc(hoursSum,
							tipSum, gasSum);
					// store calculations in single string to be saved to shared
					// preferences that will be .split(",") in the history
					// activity for presentation to user
					String dataSeperatedByComma = hourlyBeforeGas + ","
							+ hourlyAfterGas + "," + tipSum + "," + hoursSum
							+ "," + totalIncome;
					// Save previous calculations
					saveToSP(histSave, dataSeperatedByComma, WageActivity.this);
					saveToSP(historyCounter, Integer.toString(histCounter + 1),
							WageActivity.this);
					// clear Edit Texts in activity
					saveEditTextArray(tipSave, tipsArray, "0");
					saveEditTextArray(hoursSave, hoursArray, "0");
					saveEditTextArray(gasSave, gasArray, "0");
					// clear edit texts on home page
					for (int i = 0; i < HomeActivity.homeUpdateArray.length; i++) {
						HomeActivity.homeUpdateArray[i].setText("0.00");
					}
					// save 0's to shared preferences for home page
					saveToSP(homeHourlyNoGas, "0.00", WageActivity.this);
					saveToSP(homeHourlyWage, "0.00", WageActivity.this);
					saveToSP(homeTotalTips, "0.00", WageActivity.this);
					saveToSP(homeTotalHours, "0.00", WageActivity.this);
					saveToSP(homeTotalIncome, "0.00", WageActivity.this);
					// open history activity what will dynamically update with
					// saved calculations
					Intent i = new Intent(
							"com.davidtunnell.deliverypal.HISTORY");
					startActivity(i);
					// inform user of success
					Toast.makeText(getApplicationContext(),
							"Exported and Saved", Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	private void populateFields() {
		// retrieve data from shared preferences and populated edit text fields
		getSavedEditTextArray(tipSave, tipsArray);
		getSavedEditTextArray(hoursSave, hoursArray);
		getSavedEditTextArray(gasSave, gasArray);
	}

	private void hideSoftwareKeyboard() {
		// Hides software keyboard
		InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManager.hideSoftInputFromWindow(
				getCurrentFocus().getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
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