package com.davidtunnell.deliverypal;

import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TipsActivity extends ManipulateInput {

	EditText[] tipArray;
	TextView totalTips;
	TextView totalTipsGas;
	Button update;
	Button finished;
	double runningTotalSum, totalWithFeeSum;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tips_layout);
		connectVariables();
		populateFields();
	}

	private void connectVariables() {
		// Connect XML layout with Java
		tipArray = new EditText[] { (EditText) findViewById(R.id.tip1),
				(EditText) findViewById(R.id.tip2),
				(EditText) findViewById(R.id.tip3),
				(EditText) findViewById(R.id.tip4),
				(EditText) findViewById(R.id.tip5),
				(EditText) findViewById(R.id.tip6),
				(EditText) findViewById(R.id.tip7),
				(EditText) findViewById(R.id.tip8),
				(EditText) findViewById(R.id.tip9),
				(EditText) findViewById(R.id.tip10),
				(EditText) findViewById(R.id.tip11),
				(EditText) findViewById(R.id.tip12),
				(EditText) findViewById(R.id.tip13),
				(EditText) findViewById(R.id.tip14),
				(EditText) findViewById(R.id.tip15),
				(EditText) findViewById(R.id.tip16),
				(EditText) findViewById(R.id.tip17),
				(EditText) findViewById(R.id.tip18),
				(EditText) findViewById(R.id.tip19),
				(EditText) findViewById(R.id.tip20),
				(EditText) findViewById(R.id.tip21),
				(EditText) findViewById(R.id.tip22),
				(EditText) findViewById(R.id.tip23),
				(EditText) findViewById(R.id.tip24),
				(EditText) findViewById(R.id.tip25),
				(EditText) findViewById(R.id.tip26),
				(EditText) findViewById(R.id.tip27),
				(EditText) findViewById(R.id.tip28),
				(EditText) findViewById(R.id.tip29),
				(EditText) findViewById(R.id.tip30),
				(EditText) findViewById(R.id.tip31),
				(EditText) findViewById(R.id.tip32),
				(EditText) findViewById(R.id.tip33),
				(EditText) findViewById(R.id.tip34),
				(EditText) findViewById(R.id.tip35),
				(EditText) findViewById(R.id.tip36),
				(EditText) findViewById(R.id.tip37),
				(EditText) findViewById(R.id.tip38),
				(EditText) findViewById(R.id.tip39),
				(EditText) findViewById(R.id.tip40),
				(EditText) findViewById(R.id.tip41),
				(EditText) findViewById(R.id.tip42),
				(EditText) findViewById(R.id.tip43),
				(EditText) findViewById(R.id.tip44),
				(EditText) findViewById(R.id.tip45),
				(EditText) findViewById(R.id.tip46),
				(EditText) findViewById(R.id.tip47),
				(EditText) findViewById(R.id.tip48),
				(EditText) findViewById(R.id.tip49),
				(EditText) findViewById(R.id.tip50) };

		totalTips = (TextView) findViewById(R.id.TotalTips);
		totalTipsGas = (TextView) findViewById(R.id.TotalTipsGas);
		update = (Button) findViewById(R.id.Update);
		finished = (Button) findViewById(R.id.Finished);
		// Set on click listener
		update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Save tip cells
				saveEditTextArray(tipCell, tipArray, null);
				// Make calculations on user input from tip cells
				runningTotalSum = ErrorCheckSum(tipArray);
				totalWithFeeSum = ErrorCheckSumDeliveryFee(tipArray);
				// save these calculations
				saveToSP(runningTotal, Double.toString(runningTotalSum),
						TipsActivity.this);
				saveToSP(totalWithFee, Double.toString(totalWithFeeSum),
						TipsActivity.this);
				// set edit text fields with calculations for user
				totalTips.setText(String.format("%.2f", runningTotalSum));
				totalTipsGas.setText(String.format("%.2f", totalWithFeeSum));

			}
		});
		// Set on click listener
		finished.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// change tab to wage tab
				TabLayoutActivity.tabHostObj.setCurrentTab(1);
				// export total tips to the correct edit text field based on
				// which day it currently is
				exportToDay();
				// clear and save tip tabs
				saveEditTextArray(tipCell, tipArray, "");
				// zero out and save text fields that show calculation sums to
				// user
				saveToSP(runningTotal, "0.00", TipsActivity.this);
				saveToSP(totalWithFee, "0.00", TipsActivity.this);
				// set the text fields to the new zeroed out saved data
				totalTips.setText(String.format("%.2f", Double
						.parseDouble(getSavedData(runningTotal,
								TipsActivity.this))));
				totalTipsGas.setText(String.format("%.2f", Double
						.parseDouble(getSavedData(totalWithFee,
								TipsActivity.this))));
				// tell user what happened
				Toast.makeText(getApplicationContext(), "Exported and Cleared",
						Toast.LENGTH_SHORT).show();

			}

			private void exportToDay() {
				// get calendar object
				Calendar checkDay = Calendar.getInstance();
				// switch/case is used to run particular instructions based on
				// which day it is
				switch (checkDay.get(Calendar.DAY_OF_WEEK)) {
				// if Monday:
				case Calendar.MONDAY:
					// set variable to total sum of delivery fees plus tips
					totalWithFeeSum = ErrorCheckSumDeliveryFee(tipArray);
					// save to corresponding part of tip list array based on
					// which day it is
					saveToSP(tipList[0], Double.toString(totalWithFeeSum),
							TipsActivity.this);
					// if the user does not have a different wage when in the
					// store, then the wage activity is different than if they
					// do, so changes must be made to what happens in these
					// circumstances
					if (Boolean.valueOf(getSavedData("Wage_Check_Box",
							TipsActivity.this)) == false) {
						// set edit text to calculated sum
						WageActivity.tipsArray[0].setText(String.format("%.2f",
								totalWithFeeSum));
						// set focus to the exported value than now populates
						// the edit text in the wage activity
						WageActivity.tipsArray[0].requestFocus();
					} else {
						// set edit text to calculated sum
						WageCheckedActivity.tipsCheckedArray[0].setText(String
								.format("%.2f", totalWithFeeSum));
						// set focus to the exported value than now populates
						// the edit text in the wage activity
						WageCheckedActivity.tipsCheckedArray[0].requestFocus();
					}
					break;
				// if Tuesday, do the same as above:
				case Calendar.TUESDAY:
					totalWithFeeSum = ErrorCheckSumDeliveryFee(tipArray);
					saveToSP(tipList[1], Double.toString(totalWithFeeSum),
							TipsActivity.this);
					if (Boolean.valueOf(getSavedData("Wage_Check_Box",
							TipsActivity.this)) == false) {
						WageActivity.tipsArray[1].setText(String.format("%.2f",
								totalWithFeeSum));
						WageActivity.tipsArray[1].requestFocus();
					} else {
						WageCheckedActivity.tipsCheckedArray[1].setText(String
								.format("%.2f", totalWithFeeSum));
						WageCheckedActivity.tipsCheckedArray[1].requestFocus();
					}
					break;
				case Calendar.WEDNESDAY:
					totalWithFeeSum = ErrorCheckSumDeliveryFee(tipArray);
					saveToSP(tipList[2], Double.toString(totalWithFeeSum),
							TipsActivity.this);
					if (Boolean.valueOf(getSavedData("Wage_Check_Box",
							TipsActivity.this)) == false) {
						WageActivity.tipsArray[2].setText(String.format("%.2f",
								totalWithFeeSum));
						WageActivity.tipsArray[2].requestFocus();
					} else {
						WageCheckedActivity.tipsCheckedArray[2].setText(String
								.format("%.2f", totalWithFeeSum));
						WageCheckedActivity.tipsCheckedArray[2].requestFocus();
					}
					break;
				case Calendar.THURSDAY:
					totalWithFeeSum = ErrorCheckSumDeliveryFee(tipArray);
					saveToSP(tipList[3], Double.toString(totalWithFeeSum),
							TipsActivity.this);
					if (Boolean.valueOf(getSavedData("Wage_Check_Box",
							TipsActivity.this)) == false) {
						WageActivity.tipsArray[3].setText(String.format("%.2f",
								totalWithFeeSum));
						WageActivity.tipsArray[3].requestFocus();
					} else {
						WageCheckedActivity.tipsCheckedArray[3].setText(String
								.format("%.2f", totalWithFeeSum));
						WageCheckedActivity.tipsCheckedArray[3].requestFocus();
					}
					break;
				case Calendar.FRIDAY:
					totalWithFeeSum = ErrorCheckSumDeliveryFee(tipArray);
					saveToSP(tipList[4], Double.toString(totalWithFeeSum),
							TipsActivity.this);
					if (Boolean.valueOf(getSavedData("Wage_Check_Box",
							TipsActivity.this)) == false) {
						WageActivity.tipsArray[4].setText(String.format("%.2f",
								totalWithFeeSum));
						WageActivity.tipsArray[4].requestFocus();
					} else {
						WageCheckedActivity.tipsCheckedArray[4].setText(String
								.format("%.2f", totalWithFeeSum));
						WageCheckedActivity.tipsCheckedArray[4].requestFocus();
					}
					break;
				case Calendar.SATURDAY:
					totalWithFeeSum = ErrorCheckSumDeliveryFee(tipArray);
					saveToSP(tipList[5], Double.toString(totalWithFeeSum),
							TipsActivity.this);
					if (Boolean.valueOf(getSavedData("Wage_Check_Box",
							TipsActivity.this)) == false) {
						WageActivity.tipsArray[5].setText(String.format("%.2f",
								totalWithFeeSum));
						WageActivity.tipsArray[5].requestFocus();
					} else {
						WageCheckedActivity.tipsCheckedArray[5].setText(String
								.format("%.2f", totalWithFeeSum));
						WageCheckedActivity.tipsCheckedArray[5].requestFocus();
					}
					break;
				case Calendar.SUNDAY:
					totalWithFeeSum = ErrorCheckSumDeliveryFee(tipArray);
					saveToSP(tipList[6], Double.toString(totalWithFeeSum),
							TipsActivity.this);
					if (Boolean.valueOf(getSavedData("Wage_Check_Box",
							TipsActivity.this)) == false) {
						WageActivity.tipsArray[6].setText(String.format("%.2f",
								totalWithFeeSum));
						WageActivity.tipsArray[6].requestFocus();
					} else {
						WageCheckedActivity.tipsCheckedArray[6].setText(String
								.format("%.2f", totalWithFeeSum));
						WageCheckedActivity.tipsCheckedArray[6].requestFocus();
					}
					break;
				}
			}
		});
	}

	private void populateFields() {
		// retrieve data from shared preferences and populated edit text fields
		getSavedEditTextArray(tipCell, tipArray);
		totalTips.setText(String.format("%.2f", Double
				.parseDouble(getSavedData(runningTotal, TipsActivity.this))));
		totalTipsGas.setText(String.format("%.2f", Double
				.parseDouble(getSavedData(totalWithFee, TipsActivity.this))));
	}

	protected void onPause() {
		super.onPause();
		saveEditTextArray(tipCell, tipArray, null);
	}

	protected void onResume() {
		super.onResume();
		getSavedEditTextArray(tipCell, tipArray);
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