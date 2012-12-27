package com.davidtunnell.deliverypal;

import java.util.Calendar;

import android.widget.EditText;
import com.davidtunnell.deliverypal.R;

public class ManipulateInput extends SharedPref {

	protected double ErrorCheckSum(EditText[] editTextArray) {
		double sum = 0;
		// for the length of the array that was passed as an arguement:
		for (int i = 0; i < editTextArray.length; i++) {
			// if each value a value of the array is empty, skip it
			if (editTextArray[i].getText().toString().length() <= 0) {
				// Skip
			}
			// if each value a value of the array is less than or equal to
			// 0.0 (because its a double), skip it
			else if (Double.parseDouble(editTextArray[i].getText().toString()) == 0.0) {
				// Skip
			} else {
				// else add it together into a total sum
				sum += Double
						.parseDouble(editTextArray[i].getText().toString());
			}
		}
		return sum;
	}

	protected double ErrorCheckSumDeliveryFee(EditText[] editTextArray) {
		double sum = 0;
		// get the user inputted delivery fee and set to variable
		String getDf = getSavedData(deliveryFee, ManipulateInput.this);
		// turn the string into a double
		double deliveryFee = Double.parseDouble(getDf);
		// for the length of the array that was passed as an arguement:
		for (int i = 0; i < editTextArray.length; i++) {
			// if each value a value of the array is empty, skip it
			if (editTextArray[i].getText().toString().length() <= 0) {
				// Skip
			}
			// if each value a value of the array is less than or equal to
			// 0.0 (because its a double), only add the deliveryfee to the sum
			else if (Double.parseDouble(editTextArray[i].getText().toString()) == 0.0) {
				sum += deliveryFee;
			}
			// else add the delivery fee and the tip to the sum
			else {
				sum += Double
						.parseDouble(editTextArray[i].getText().toString())
						+ deliveryFee;
			}
		}
		return sum;
	}

	protected double hourlyWageCalc(double hoursSum, double tipSum) {
		// get data from shared preferences and turn to double
		String getWage = getSavedData(hourlyWage, ManipulateInput.this);
		double hourlyWage = Double.parseDouble(getWage);
		// return calculation
		return ((hourlyWage * hoursSum) + tipSum) / hoursSum;
	}

	protected double hourlyWageLessGasCalc(double hoursSum, double tipSum,
			double gasSum) {
		// get data from shared preferences and turn to double
		String getWage = getSavedData(hourlyWage, ManipulateInput.this);
		double hourlyWage = Double.parseDouble(getWage);
		// return calculation
		return ((hourlyWage * hoursSum) + tipSum - gasSum) / hoursSum;
	}

	protected double totalIncomeCalc(double hoursSum, double tipSum) {
		// get data from shared preferences and turn to double
		String getWage = getSavedData(hourlyWage, ManipulateInput.this);
		double hourlyWage = Double.parseDouble(getWage);
		// return calculation
		return (hourlyWage * hoursSum) + tipSum;
	}

	protected double hourlyWageCheckedCalc(double hoursInStoreSum,
			double hoursOnRoadSum, double tipSum) {
		// get data from shared preferences and turn to double
		String getWageInStore = getSavedData(hourlyWage, ManipulateInput.this);
		String getWageOnRoad = getSavedData(roadWage, ManipulateInput.this);
		double hourlyWageInStore = Double.parseDouble(getWageInStore);
		double hourlyWageOnRoad = Double.parseDouble(getWageOnRoad);
		// return calculation
		return ((hourlyWageInStore * hoursInStoreSum)
				+ (hourlyWageOnRoad * hoursOnRoadSum) + tipSum)
				/ (hoursInStoreSum + hoursOnRoadSum);
	}

	protected double hourlyWageLessGasCheckedCalc(double hoursInStoreSum,
			double hoursOnRoadSum, double tipSum, double gasSum) {
		// get data from shared preferences and turn to double
		String getWageInStore = getSavedData(hourlyWage, ManipulateInput.this);
		String getWageOnRoad = getSavedData(roadWage, ManipulateInput.this);
		double hourlyWageInStore = Double.parseDouble(getWageInStore);
		double hourlyWageOnRoad = Double.parseDouble(getWageOnRoad);
		// return calculation
		return ((hourlyWageInStore * hoursInStoreSum)
				+ (hourlyWageOnRoad * hoursOnRoadSum) + tipSum - gasSum)
				/ (hoursInStoreSum + hoursOnRoadSum);
	}

	protected double totalIncomeCheckedCalc(double hoursInStoreSum,
			double hoursOnRoadSum, double tipSum) {
		// get data from shared preferences and turn to double
		String getWageInStore = getSavedData(hourlyWage, ManipulateInput.this);
		String getWageOnRoad = getSavedData(roadWage, ManipulateInput.this);
		double hourlyWageInStore = Double.parseDouble(getWageInStore);
		double hourlyWageOnRoad = Double.parseDouble(getWageOnRoad);
		// return calculation
		return ((hourlyWageInStore * hoursInStoreSum)
				+ (hourlyWageOnRoad * hoursOnRoadSum) + tipSum);
	}

	protected String findDate() {
		// create an array of month strings
		String[] monthName = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };
		// create calander object
		Calendar checkDay = Calendar.getInstance();
		// return standard month, date, year format string
		return monthName[checkDay.get(Calendar.MONTH)] + " "
				+ checkDay.get(Calendar.DATE) + ", "
				+ checkDay.get(Calendar.YEAR);
	}
}