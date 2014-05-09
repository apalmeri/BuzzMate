package edu.ycp.cs.cs496.buzzmateapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import edu.ycp.cs.cs496.locations.mobilecontrollers.GetCab;
import edu.ycp.cs.cs496.locations.model.Cab;


public class CabInformation  extends Activity{
protected void onCreate(Bundle savedInstanceState) { 
		
		String value = "";
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    value = extras.getString("Name");
		}
				
		Cab cab = new Cab();
		GetCab controller = new GetCab();
		try {
			cab = controller.getCab(value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String phone = cab.getPhonenumber();
		String notes = cab.getNotes();
		
		// Create Linear layout
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);

		// Add back button
		Button backButton = new Button(this);
		backButton.setText("Back");
		backButton.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		// TODO: Add back button onClickListener - Implemented
		backButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub - Implemented
				finish();
			}
		});
		
		TextView nameBox = new TextView(this);
		nameBox.setText(value);
		
		TextView phoneBox = new TextView(this);
		phoneBox.setText(phone);
		Linkify.addLinks(phoneBox, Linkify.PHONE_NUMBERS);
		
		TextView notesBox = new TextView(this);
		notesBox.setText(notes);
		
		// Add button to layout
		//Linkify http://developer.android.com/reference/android/text/util/Linkify.html
		layout.addView(backButton);
		layout.addView(nameBox);
		layout.addView(phoneBox);
		layout.addView(notesBox);
		
		setContentView(layout,llp);	
	}
}
