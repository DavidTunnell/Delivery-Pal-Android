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

public class WageCheckedActivity extends ManipulateInput {
	public static EditText[] tipsCheckedArray;
	EditText[] hoursInStoreArray, hoursOnRoadArray, gasCheckedArray;
	Button save, finish;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wage_checked_layout);
		connectVariables();
		populateFields();
	}

	private void connectVariables() {
		// Connect XML layout with Java
		tipsCheckedArray = new EditText[] {
				(EditText) findViewById(R.id.TipBoxCheck1),
				(EditText) findViewById(R.id.TipBoxCheck2),
				(EditText) findViewById(R.id.TipBoxCheck3),
				(EditText) findViewById(R.id.TipBoxCheck4),
				(EditText) findViewById(R.id.TipBoxCheck5),
				(EditText) findViewById(R.id.TipBoxCheck6),
				(EditText) findViewById(R.id.TipBoxCheck7) };
		hoursInStoreArray = new EditText[] {
				(EditText) findViewById(R.id.InStore1),
				(EditText) findViewById(R.id.InStore2),
				(EditText) findViewById(R.id.InStore3),
				(EditText) findViewById(R.id.InStore4),
				(EditText) findViewById(R.id.InStore5),
				(EditText) findViewById(R.id.InStore6),
				(EditText) findViewById(R.id.InStore7) };
		hoursOnRoadArray = new EditText[] {
				(EditText) findViewById(R.id.OnRoad1),
				(EditText) findViewById(R.id.OnRoad2),
				(EditText) findViewById(R.id.OnRoad3),
				(EditText) findViewById(R.id.OnRoad4),
				(EditText) findViewById(R.id.OnRoad5),
				(EditText) findViewById(R.id.OnRoad6),
				(EditText) findViewById(R.id.OnRoad7) };
		gasCheckedArray = new EditText[] {
				(EditText) findViewById(R.id.GasSpent1),
				(EditText) findViewById(R.id.GasSpent2),
				(EditText) findViewById(R.id.GasSpent3),
				(EditText) findViewById(R.id.GasSpent4),
				(EditText) findViewById(R.id.GasSpent5),
				(EditText) findViewById(R.id.GasSpent6),
				(EditText) findViewById(R.id.GasSpent7) };
		save = (Button) findViewById(R.id.SaveBut);
		finish = (Button) findViewById(R.id.FinishBut);
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Calculate sums from ManipulateInput.java
				double tipSum = ErrorCheckSum(tipsCheckedArray);
				double hoursInStoreSum = ErrorCheckSum(hoursInStoreArray);
				double hoursOnRoadSum = ErrorCheckSum(hoursOnRoadArray);
				double gasSum = ErrorCheckSum(gasCheckedArray);
				double hoursSum = hoursInStoreSum + hoursOnRoadSum;
				// Save EditText fields
				saveEditTextArray(tipCheckedSave, tipsCheckedArray, null);
				saveEditTextArray(hoursInStoreSave, hoursInStoreArray, null);
				saveEditTextArray(hoursOnRoadSave, hoursOnRoadArray, null);
				saveEditTextArray(gasCheckedSave, gasCheckedArray, null);

				// Prevent user from dividing by zero, if so alert
				if (hoursInStoreSum <= 0) {
					hideSoftwareKeyboard();
					Toast.makeText(getApplicationContext(),
							"Please enter how many how many hours you worked.",
							Toast.LENGTH_LONG).show();
				} else {
					hideSoftwareKeyboard();
					// Set view to home page
					TabLayoutActivity.tabHostObj.setCurrentTab(2);
					// Make calculations from ManipulateInput.java
					double totalIncome = totalIncomeCheckedCalc(
							hoursInStoreSum, hoursOnRoadSum, tipSum);
					double hourlyBeforeGas = hourlyWageCheckedCalc(
							hoursInStoreSum, hoursOnRoadSum, tipSum);
					double hourlyAfterGas = hourlyWageLessGasCheckedCalc(
							hoursInStoreSum, hoursOnRoadSum, tipSum, gasSum);
					// Save and publish results to home page
					saveToSP(homeHourlyNoGas, Double.toString(hourlyBeforeGas),
							WageCheckedActivity.this);
					saveToSP(homeHourlyWage, Double.toString(hourlyAfterGas),
							WageCheckedActivity.this);
					saveToSP(homeTotalTips, Double.toString(tipSum),
							WageCheckedActivity.this);
					saveToSP(homeTotalHours, Double.toString(hoursSum),
							WageCheckedActivity.this);
					saveToSP(homeTotalIncome, Double.toString(totalIncome),
							WageCheckedActivity.this);
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
						WageCheckedActivity.this));
				// these strings are used as new shared preference slots/keys
				String histSave = historySave + histCounter;
				String dateSave = calenderSave + histCounter;
				// saves date in Month, day, year format from method in
				// ManipulateInput.java
				saveToSP(dateSave, findDate(), WageCheckedActivity.this);
				// Sum while checking for errors of Edit Texts in activity
				double tipSum = ErrorCheckSum(tipsCheckedArray);
				double hoursInStoreSum = ErrorCheckSum(hoursInStoreArray);
				double hoursOnRoadSum = ErrorCheckSum(hoursOnRoadArray);
				double gasSum = ErrorCheckSum(gasCheckedArray);
				double hoursSum = hoursInStoreSum + hoursOnRoadSum;
				// ensure user isn't trying to divide by zero, if so prompt them
				// to fix
				if (hoursSum <= 0) {
					hideSoftwareKeyboard();
					Toast.makeText(getApplicationContext(),
							"Please enter how many how many hours you worked.",
							Toast.LENGTH_LONG).show();
				} else {
					// Hide software keyboard
					hideSoftwareKeyboard();
					// set current tab view to home page
					TabLayoutActivity.tabHostObj.setCurrentTab(2);
					// make calculations to be used
					double totalIncome = totalIncomeCheckedCalc(
							hoursInStoreSum, hoursOnRoadSum, tipSum);
					double hourlyBeforeGas = hourlyWageCheckedCalc(
							hoursInStoreSum, hoursOnRoadSum, tipSum);
					double hourlyAfterGas = hourlyWageLessGasCheckedCalc(
							hoursInStoreSum, hoursOnRoadSum, tipSum, gasSum);
					// store calculations in single string to be saved to shared
					// preferences that will be .split(",") in the history
					// activity for presentation to user
					String dataSeperatedByComma = hourlyBeforeGas + ","
							+ hourlyAfterGas + "," + tipSum + "," + hoursSum
							+ "," + totalIncome;
					// Save previous calculations
					saveToSP(histSave, dataSeperatedByComma,
							WageCheckedActivity.this);
					saveToSP(historyCounter, Integer.toString(histCounter + 1),
							WageCheckedActivity.this);
					// clear Edit Texts in activity
					saveEditTextArray(tipCheckedSave, tipsCheckedArray, "0");
					saveEditTextArray(hoursInStoreSave, hoursInStoreArray, "0");
					saveEditTextArray(hoursOnRoadSave, hoursOnRoadArray, "0");
					saveEditTextArray(gasCheckedSave, gasCheckedArray, "0");
					// clear edit texts on home page
					for (int i = 0; i < HomeActivity.homeUpdateArray.length; i++) {
						HomeActivity.homeUpdateArray[i].setText("0.00");
					}
					// save 0's to shared preferences for home page
					saveToSP(homeHourlyNoGas, "0.00", WageCheckedActivity.this);
					saveToSP(homeHourlyWage, "0.00", WageCheckedActivity.this);
					saveToSP(homeTotalTips, "0.00", WageCheckedActivity.this);
					saveToSP(homeTotalHours, "0.00", WageCheckedActivity.this);
					saveToSP(homeTotalIncome, "0.00", WageCheckedActivity.this);
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
		getSavedEditTextArray(tipCheckedSave, tipsCheckedArray);
		getSavedEditTextArray(hoursInStoreSave, hoursInStoreArray);
		getSavedEditTextArray(hoursOnRoadSave, hoursOnRoadArray);
		getSavedEditTextArray(gasCheckedSave, gasCheckedArray);
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
