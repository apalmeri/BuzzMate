package edu.ycp.cs.cs496.buzzmateapp;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;

import edu.ycp.cs.cs496.locations.mobilecontrollers.GetLocation;
import edu.ycp.cs.cs496.locations.model.Location;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LocationInformation extends Activity{
	protected void onCreate(Bundle savedInstanceState) { 
		
		String value = "";
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    value = extras.getString("Name");
		}
		
		Location location = new Location();
		GetLocation controller = new GetLocation();
		try {
			location = controller.getLocation(value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String street = location.getStreet1();
		String city = location.getCity();
		String state = location.getState();
		String mailcode = location.getMailcode();
		String phone = location.getPhonenumber();
		
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
		
		TextView streetBox = new TextView(this);
		streetBox.setText(street+"\n"+city+", "+state+" "+mailcode);
		Linkify.addLinks(streetBox, Linkify.MAP_ADDRESSES);
		
		TextView address2 = new TextView(this);
		address2.setText(city+", "+state+" "+mailcode);
		
		TextView phoneBox = new TextView(this);
		phoneBox.setText(phone);
		Linkify.addLinks(phoneBox, Linkify.PHONE_NUMBERS);
		
		// Add button to layout
		//Linkify http://developer.android.com/reference/android/text/util/Linkify.html
		layout.addView(backButton);
		layout.addView(nameBox);
		layout.addView(streetBox);
		//layout.addView(address2);
		layout.addView(phoneBox);
		
		setContentView(layout,llp);	
	}
}
