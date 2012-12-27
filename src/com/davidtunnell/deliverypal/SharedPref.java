package com.davidtunnell.deliverypal;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.EditText;

public class SharedPref extends Activity {

	// create global variables for all of the shared preferences keys
	public static final String firstCheck = "First_Run_Check";
	public static final String tipSave = "Tip_Save";
	public static final String[] tipList = new String[] { "Tip_Save1",
			"Tip_Save2", "Tip_Save3", "Tip_Save4", "Tip_Save5", "Tip_Save6",
			"Tip_Save7" };
	public static final String hoursSave = "Hours_Save";
	public static final String[] hoursList = new String[] { "Hours_Save1",
			"Hours_Save2", "Hours_Save3", "Hours_Save4", "Hours_Save5",
			"Hours_Save6", "Hours_Save7" };
	public static final String gasSave = "Gas_Save";
	public static final String[] gasList = new String[] { "Gas_Save1",
			"Gas_Save2", "Gas_Save3", "Gas_Save4", "Gas_Save5", "Gas_Save6",
			"Gas_Save7" };
	public static final String tipCheckedSave = "Tip_Checked_Save";
	public static final String[] tipCheckedList = new String[] {
			"Tip_Checked_Save1", "Tip_Checked_Save2", "Tip_Checked_Save3",
			"Tip_Checked_Save4", "Tip_Checked_Save5", "Tip_Checked_Save6",
			"Tip_Checked_Save7" };
	public static final String hoursInStoreSave = "Hours_InStore_Save";
	public static final String[] hoursInStoreList = new String[] {
			"Hours_InStore_Save1", "Hours_InStore_Save2",
			"Hours_InStore_Save3", "Hours_InStore_Save4",
			"Hours_InStore_Save5", "Hours_InStore_Save6", "Hours_InStore_Save7" };
	public static final String hoursOnRoadSave = "Hours_OnRoad_Save";
	public static final String[] hoursOnRoadList = new String[] {
			"Hours_OnRoad_Save1", "Hours_OnRoad_Save2", "Hours_OnRoad_Save3",
			"Hours_OnRoad_Save4", "Hours_OnRoad_Save5", "Hours_OnRoad_Save6",
			"Hours_OnRoad_Save7" };
	public static final String gasCheckedSave = "Gas_Checked_Save";
	public static final String[] gasCheckedList = new String[] {
			"Gas_Checked_Save1", "Gas_Checked_Save2", "Gas_Checked_Save3",
			"Gas_Checked_Save4", "Gas_Checked_Save5", "Gas_Checked_Save6",
			"Gas_Checked_Save7" };
	public static final String historyCounter = "History_Counter";
	public static final String calenderSave = "Calender_Date";
	public static final String historySave = "History_Save";
	public static final String hourlyWage = "Hourly_Wage";
	public static final String deliveryFee = "Delivery_Fee";
	public static final String roadWage = "Road_Wage";
	public static final String tipCell = "Tip";
	public static final String subName = "Sub_Name";
	public static final String subCode = "Sub_Code";
	public static final String runningTotal = "Running_Total";
	public static final String totalWithFee = "Total_With_Fee";
	public static final String homeHourlyNoGas = "Home_Hourly_No_Gas";
	public static final String homeHourlyWage = "Home_Hourly_Wage";
	public static final String homeTotalTips = "Home_Total_Tips";
	public static final String homeTotalHours = "Home_Total_Hours";
	public static final String homeTotalIncome = "Home_Total_Income";
	public static final String wageCheckBox = "Wage_Check_Box";
	public static final String checkPreviousWageBox = "Wage_Previous_Box";

	public static void saveToSP(String key, String value, Context context) {
		SharedPreferences data = PreferenceManager
				.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = data.edit();
		// save to shared preferences
		editor.putString(key, value);
		editor.commit();
	}

	public static String getSavedData(String key, Context context) {
		// utilized preference manager
		SharedPreferences data = PreferenceManager
				.getDefaultSharedPreferences(context);
		// return saved data from shared preferences
		return data.getString(key, null);
	}

	public void saveEditTextArray(String key, EditText[] editTextArray,
			String setText) {
		// for the length of the array passed as a parameter:
		for (int i = 0; i < editTextArray.length; i++) {
			// generate a key
			String getString = key + (i + 1);
			// if the user does not pass null, set the edit texts equal to what
			// was passed
			if (setText != null) {
				editTextArray[i].setText(setText);
			}
			// save array to shared preferences by use of generated key
			saveToSP(getString, editTextArray[i].getText().toString(), this);
		}
	}

	public void getSavedEditTextArray(String key, EditText[] editTextArray) {
		// for the length of the array passed as a parameter:
		for (int i = 0; i < editTextArray.length; i++) {
			// generate a key
			String getString = key + (i + 1);
			// get data from shared preferences using generated key
			String getSave = getSavedData(getString, this);
			// set text of edit text array parameter to these values
			editTextArray[i].setText(getSave);
		}
	}

	public void populatedSharedPref() {
		// if shared preferences values are empty for these keys, populate them
		// with default values
		if (getSavedData(tipList[0], this) == null) {
			saveToSP(tipList[0], "0", this);
		}
		if (getSavedData(tipList[0], this) == null) {
			saveToSP(tipList[0], "0", this);
		}
		if (getSavedData(tipList[1], this) == null) {
			saveToSP(tipList[1], "0", this);
		}
		if (getSavedData(tipList[2], this) == null) {
			saveToSP(tipList[2], "0", this);
		}
		if (getSavedData(tipList[3], this) == null) {
			saveToSP(tipList[3], "0", this);
		}
		if (getSavedData(tipList[4], this) == null) {
			saveToSP(tipList[4], "0", this);
		}
		if (getSavedData(tipList[5], this) == null) {
			saveToSP(tipList[5], "0", this);
		}
		if (getSavedData(tipList[6], this) == null) {
			saveToSP(tipList[6], "0", this);
		}
		if (getSavedData(hoursList[0], this) == null) {
			saveToSP(hoursList[0], "0", this);
		}
		if (getSavedData(hoursList[1], this) == null) {
			saveToSP(hoursList[1], "0", this);
		}
		if (getSavedData(hoursList[2], this) == null) {
			saveToSP(hoursList[2], "0", this);
		}
		if (getSavedData(hoursList[3], this) == null) {
			saveToSP(hoursList[3], "0", this);
		}
		if (getSavedData(hoursList[4], this) == null) {
			saveToSP(hoursList[4], "0", this);
		}
		if (getSavedData(hoursList[5], this) == null) {
			saveToSP(hoursList[5], "0", this);
		}
		if (getSavedData(hoursList[6], this) == null) {
			saveToSP(hoursList[6], "0", this);
		}
		if (getSavedData(gasList[0], this) == null) {
			saveToSP(gasList[0], "0", this);
		}
		if (getSavedData(gasList[1], this) == null) {
			saveToSP(gasList[1], "0", this);
		}
		if (getSavedData(gasList[2], this) == null) {
			saveToSP(gasList[2], "0", this);
		}
		if (getSavedData(gasList[3], this) == null) {
			saveToSP(gasList[3], "0", this);
		}
		if (getSavedData(gasList[4], this) == null) {
			saveToSP(gasList[4], "0", this);
		}
		if (getSavedData(gasList[5], this) == null) {
			saveToSP(gasList[5], "0", this);
		}
		if (getSavedData(gasList[6], this) == null) {
			saveToSP(gasList[6], "0", this);
		}
		if (getSavedData(tipCheckedList[0], this) == null) {
			saveToSP(tipCheckedList[0], "0", this);
		}
		if (getSavedData(tipCheckedList[1], this) == null) {
			saveToSP(tipCheckedList[1], "0", this);
		}
		if (getSavedData(tipCheckedList[2], this) == null) {
			saveToSP(tipCheckedList[2], "0", this);
		}
		if (getSavedData(tipCheckedList[3], this) == null) {
			saveToSP(tipCheckedList[3], "0", this);
		}
		if (getSavedData(tipCheckedList[4], this) == null) {
			saveToSP(tipCheckedList[4], "0", this);
		}
		if (getSavedData(tipCheckedList[5], this) == null) {
			saveToSP(tipCheckedList[5], "0", this);
		}
		if (getSavedData(tipCheckedList[6], this) == null) {
			saveToSP(tipCheckedList[6], "0", this);
		}
		if (getSavedData(hoursOnRoadList[0], this) == null) {
			saveToSP(hoursOnRoadList[0], "0", this);
		}
		if (getSavedData(hoursOnRoadList[1], this) == null) {
			saveToSP(hoursOnRoadList[1], "0", this);
		}
		if (getSavedData(hoursOnRoadList[2], this) == null) {
			saveToSP(hoursOnRoadList[2], "0", this);
		}
		if (getSavedData(hoursOnRoadList[3], this) == null) {
			saveToSP(hoursOnRoadList[3], "0", this);
		}
		if (getSavedData(hoursOnRoadList[4], this) == null) {
			saveToSP(hoursOnRoadList[4], "0", this);
		}
		if (getSavedData(hoursOnRoadList[5], this) == null) {
			saveToSP(hoursOnRoadList[5], "0", this);
		}
		if (getSavedData(hoursOnRoadList[6], this) == null) {
			saveToSP(hoursOnRoadList[6], "0", this);
		}
		if (getSavedData(hoursInStoreList[0], this) == null) {
			saveToSP(hoursInStoreList[0], "0", this);
		}
		if (getSavedData(hoursInStoreList[1], this) == null) {
			saveToSP(hoursInStoreList[1], "0", this);
		}
		if (getSavedData(hoursInStoreList[2], this) == null) {
			saveToSP(hoursInStoreList[2], "0", this);
		}
		if (getSavedData(hoursInStoreList[3], this) == null) {
			saveToSP(hoursInStoreList[3], "0", this);
		}
		if (getSavedData(hoursInStoreList[4], this) == null) {
			saveToSP(hoursInStoreList[4], "0", this);
		}
		if (getSavedData(hoursInStoreList[5], this) == null) {
			saveToSP(hoursInStoreList[5], "0", this);
		}
		if (getSavedData(hoursInStoreList[6], this) == null) {
			saveToSP(hoursInStoreList[6], "0", this);
		}
		if (getSavedData(gasCheckedList[0], this) == null) {
			saveToSP(gasCheckedList[0], "0", this);
		}
		if (getSavedData(gasCheckedList[1], this) == null) {
			saveToSP(gasCheckedList[1], "0", this);
		}
		if (getSavedData(gasCheckedList[2], this) == null) {
			saveToSP(gasCheckedList[2], "0", this);
		}
		if (getSavedData(gasCheckedList[3], this) == null) {
			saveToSP(gasCheckedList[3], "0", this);
		}
		if (getSavedData(gasCheckedList[4], this) == null) {
			saveToSP(gasCheckedList[4], "0", this);
		}
		if (getSavedData(gasCheckedList[5], this) == null) {
			saveToSP(gasCheckedList[5], "0", this);
		}
		if (getSavedData(gasCheckedList[6], this) == null) {
			saveToSP(gasCheckedList[6], "0", this);
		}
		if (getSavedData(historyCounter, this) == null) {
			saveToSP(historyCounter, "1", this);
		}
		if (getSavedData(hourlyWage, this) == null) {
			saveToSP(hourlyWage, "7.25", this);
		}
		if (getSavedData(deliveryFee, this) == null) {
			saveToSP(deliveryFee, "1.25", this);
		}
		if (getSavedData(roadWage, this) == null) {
			saveToSP(roadWage, "0", this);
		}
		if (getSavedData("Sub_Name1", this) == null) {
			saveToSP("Sub_Name1", "Southlake Ranch (example)", this);
		}
		if (getSavedData("Sub_Code1", this) == null) {
			saveToSP("Sub_Code1", "12345", this);
		}
		if (getSavedData(runningTotal, this) == null) {
			saveToSP(runningTotal, "0", this);
		}
		if (getSavedData(totalWithFee, this) == null) {
			saveToSP(totalWithFee, "0", this);
		}
		if (getSavedData(homeHourlyNoGas, this) == null) {
			saveToSP(homeHourlyNoGas, "0", this);
		}
		if (getSavedData(homeHourlyWage, this) == null) {
			saveToSP(homeHourlyWage, "0", this);
		}
		if (getSavedData(homeTotalTips, this) == null) {
			saveToSP(homeTotalTips, "0", this);
		}
		if (getSavedData(homeTotalHours, this) == null) {
			saveToSP(homeTotalHours, "0", this);
		}
		if (getSavedData(homeTotalIncome, this) == null) {
			saveToSP(homeTotalIncome, "0", this);
		}
		if (getSavedData(wageCheckBox, this) == null) {
			saveToSP(wageCheckBox, "false", this);
		}
		if (getSavedData(checkPreviousWageBox, this) == null) {
			saveToSP(checkPreviousWageBox, "false", this);
		}
	}
}
