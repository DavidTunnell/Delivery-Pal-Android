package com.davidtunnell.deliverypal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Preferences extends SharedPref {

	EditText hourlyWageBox, roadWageBox, deliveryFeeBox;
	CheckBox diffRate;
	Button saveExitPref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preferences);
		connectVariables();
		populateFields();

	}

	private void connectVariables() {
		// Prevent software keyboard from automatically opening in ScrollView
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		// connect xml layout with java
		hourlyWageBox = (EditText) findViewById(R.id.MainWage);
		roadWageBox = (EditText) findViewById(R.id.RoadWage);
		deliveryFeeBox = (EditText) findViewById(R.id.DeliveryFee);
		diffRate = (CheckBox) findViewById(R.id.wageCheck);
		saveExitPref = (Button) findViewById(R.id.ExitPref);
		saveExitPref.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Retrieve first run value for if statement to change how
				// activity runs based on where activity is being started from
				String firstRunCheck = getSavedData(firstCheck,
						Preferences.this);
				// Save to Shared Preferences
				saveToSP(firstCheck, "1", Preferences.this);
				saveToSP(hourlyWage, hourlyWageBox.getText().toString(),
						Preferences.this);
				saveToSP(roadWage, roadWageBox.getText().toString(),
						Preferences.this);
				saveToSP(deliveryFee, deliveryFeeBox.getText().toString(),
						Preferences.this);
				// Tell user data was saved
				Toast.makeText(getApplicationContext(), "Saved",
						Toast.LENGTH_SHORT).show();
				// Save setting when activity opened SavedPreferences to compare
				// so correct operation is done (restart app or close activity)
				saveToSP(checkPreviousWageBox,
						getSavedData(wageCheckBox, Preferences.this),
						Preferences.this);
				// Save CheckBox status to SharedPreferences
				if (diffRate.isChecked()) {
					saveToSP(wageCheckBox, "true", Preferences.this);
				} else if (diffRate.isChecked() == false) {
					saveToSP(wageCheckBox, "false", Preferences.this);
				}
				// check of this is first run (firstRunCheck == null), if so
				// launch main TabActiviy
				if (firstRunCheck == null) {
					Intent openStartingPoint = new Intent(
							"com.davidtunnell.deliverypal.MAINTABS");
					startActivity(openStartingPoint);
					//
				} else {
					// if ensure the setting of of the CheckBox is the same as
					// before, if so close activity
					if (getSavedData(checkPreviousWageBox, Preferences.this) == getSavedData(
							wageCheckBox, Preferences.this)) {
						finish();
					} else {
						// If not restart application to load new wage activity
						// (this must be done so that the correct xml layout is
						// present to user) if wage structure CheckBox changes
						Intent i = getBaseContext().getPackageManager()
								.getLaunchIntentForPackage(
										getBaseContext().getPackageName());
						i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(i);
					}
				}
			}
		});
	}

	private void populateFields() {
		// set CheckBox based on SharedPreferences saved data
		diffRate.setChecked(Boolean.valueOf(getSavedData(wageCheckBox,
				Preferences.this)));
		// retrieve and set fields to previous save based on user input
		String getHw = getSavedData(hourlyWage, Preferences.this);
		String getRw = getSavedData(roadWage, Preferences.this);
		String getDf = getSavedData(deliveryFee, Preferences.this);
		hourlyWageBox.setText(getHw);
		roadWageBox.setText(getRw);
		deliveryFeeBox.setText(getDf);
	}
}
