package com.davidtunnell.deliverypal;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.davidtunnell.deliverypal.R;

public class HistoryActivity extends SharedPref {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// dynamic layout - create scroll view
		ScrollView scrollableLayout = new ScrollView(this);
		// dynamic layout - create scroll layout
		LinearLayout linearLayout = new LinearLayout(this);
		// prevent horizontal layout
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		// add linear layout to scroll view
		scrollableLayout.addView(linearLayout);
		// setbackground color using RGB
		scrollableLayout.setBackgroundColor(Color.rgb(127, 33, 8));

		// create a text view
		TextView historyHeader = new TextView(this);
		// set its string content
		SpannableString title = new SpannableString("     History     ");
		// set its styling, centering, size etc
		title.setSpan(new UnderlineSpan(), 0, title.length(), 0);
		historyHeader.setText(title);
		historyHeader.setTextColor(Color.WHITE);
		historyHeader.setTypeface(Typeface.SERIF);
		historyHeader.setTextSize(30);
		historyHeader.setGravity(Gravity.CENTER);
		// add to layout
		linearLayout.addView(historyHeader);
		// get history counters current value (the number of times a user has
		// exported their weekly wage data)
		String histCount = getSavedData(historyCounter, HistoryActivity.this);
		// loop for as many times as the history counters current value,
		// counting backwards so the most recent week will be shown at the top
		for (int i = Integer.parseInt(histCount); 0 <= i; i--) {
			// create shared preferences key strings in loop
			String historyKey = historySave + (i + 1);
			String dateKey = calenderSave + (i + 1);
			// retrieve string separated by commas from the saving of the data
			// in wage activity
			String commaString = getSavedData(historyKey, HistoryActivity.this);
			// this if statement ensures that the following only gets added when
			// there is a specific save
			if (getSavedData(historyKey, HistoryActivity.this) != null) {
				// create an array out of the saved string with values that were
				// seperated by commas
				String[] fetchArray = commaString.split(",");
				// create text view
				TextView dataHeader = new TextView(this);
				// set styling values and string based on saved date from
				// datekey
				SpannableString dataTitle = new SpannableString("Week of: "
						+ getSavedData(dateKey, HistoryActivity.this));
				dataTitle
						.setSpan(new UnderlineSpan(), 0, dataTitle.length(), 0);
				dataHeader.setText(dataTitle);
				dataHeader.setTextColor(Color.YELLOW);
				dataHeader.setTypeface(Typeface.SERIF);
				dataHeader.setTextSize(20);
				dataHeader.setGravity(Gravity.CENTER);
				// add to layout
				linearLayout.addView(dataHeader);

				TextView hourlyWageText = new TextView(this);
				// set equal to value of the array we created from string with
				// commas in shared preferences
				hourlyWageText.setText("Hourly Wage: "
						+ String.format("%.2f",
								Double.parseDouble(fetchArray[0])));
				hourlyWageText.setTextColor(Color.CYAN);
				hourlyWageText.setTextSize(15);
				hourlyWageText.setGravity(Gravity.CENTER);
				linearLayout.addView(hourlyWageText);

				TextView hourlyGasText = new TextView(this);
				hourlyGasText.setText("Hourly After Gas: "
						+ String.format("%.2f",
								Double.parseDouble(fetchArray[1])));
				hourlyGasText.setTextColor(Color.CYAN);
				hourlyGasText.setTextSize(15);
				hourlyGasText.setGravity(Gravity.CENTER);
				linearLayout.addView(hourlyGasText);

				TextView totalTips = new TextView(this);
				totalTips.setText("Total Tips: "
						+ String.format("%.2f",
								Double.parseDouble(fetchArray[2])));
				totalTips.setTextColor(Color.CYAN);
				totalTips.setTextSize(15);
				totalTips.setGravity(Gravity.CENTER);
				linearLayout.addView(totalTips);

				TextView totalHours = new TextView(this);
				totalHours.setText("Total Hours: "
						+ String.format("%.2f",
								Double.parseDouble(fetchArray[3])));
				totalHours.setTextColor(Color.CYAN);
				totalHours.setTextSize(15);
				totalHours.setGravity(Gravity.CENTER);
				linearLayout.addView(totalHours);

				TextView totalIncome = new TextView(this);
				totalIncome.setText("Total Income: "
						+ String.format("%.2f",
								Double.parseDouble(fetchArray[4])));
				totalIncome.setTextColor(Color.CYAN);
				totalIncome.setTextSize(15);
				totalIncome.setGravity(Gravity.CENTER);
				linearLayout.addView(totalIncome);
			}
		}
		// set the view to the scrollable layout
		this.setContentView(scrollableLayout);

		// Prevent software keyboard from automatically opening in ScrollView
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}
}