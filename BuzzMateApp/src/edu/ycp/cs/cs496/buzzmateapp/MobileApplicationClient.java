package edu.ycp.cs.cs496.buzzmateapp;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.ClientProtocolException;
import org.xml.sax.SAXException;

import edu.ycp.cs.cs496.locations.mobilecontrollers.GetLocation;
import edu.ycp.cs.cs496.locations.mobilecontrollers.GetLocationList;
import edu.ycp.cs.cs496.locations.mobilecontrollers.GetLocationsByType;
import edu.ycp.cs.cs496.locations.model.Location;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MobileApplicationClient extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setDefaultView();
	}
	
	public void getLocationList() throws URISyntaxException, ClientProtocolException, 
	IOException, ParserConfigurationException, SAXException{
		GetLocationList locationList = new GetLocationList();
		Location[] locations = locationList.getLocationList();
		if(locations != null) {
			displayLocationsView(locations);
		} else {
			Toast.makeText(MobileApplicationClient.this, "No Locations Found!", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void getLocationsByType(String type) throws URISyntaxException, ClientProtocolException, 
	IOException, ParserConfigurationException, SAXException{
		GetLocationsByType locationList = new GetLocationsByType();
		Location[] locations = locationList.GetLocationsByType(type);
		if(locations != null) {
			displayLocationsView(locations);
		} else {
			Toast.makeText(MobileApplicationClient.this, "No Locations Found!", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void getLocation(String locationName) throws URISyntaxException, ClientProtocolException, 
	IOException, ParserConfigurationException, SAXException{
		GetLocation controller = new GetLocation();
		Location location = controller.getLocation(locationName);
		Location[] singleLocationArray = new Location[1];
		singleLocationArray[0] = location;
		if(location != null) {
			displayLocationsView(singleLocationArray);
		} else {
			Toast.makeText(MobileApplicationClient.this, "No Locations Found!", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void setDefaultView() {
        setContentView(R.layout.activity_main);
        
        // TODO: Obtain references to widgets
        //Button showButton = (Button) findViewById(R.id.showButton);   
        Button getButton = (Button) findViewById(R.id.getButton);
        ImageButton barButton = (ImageButton) findViewById(R.id.barButton);
        ImageButton foodButton = (ImageButton) findViewById(R.id.foodButton);
        ImageButton settingsButton = (ImageButton) findViewById(R.id.settingsButton);
        ImageButton cabButton = (ImageButton) findViewById(R.id.cab_button);
        ImageButton soberButton = (ImageButton) findViewById(R.id.soberButton);
        ImageButton checkInButton = (ImageButton) findViewById(R.id.checkin_button);
        Button drunkTestButton = (Button) findViewById(R.id.drunkTest);
        
        
        // TODO: Set onClickListeners for buttons
       barButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					getLocationsByType("Bar");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
       
       drunkTestButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					Intent intent = new Intent(MobileApplicationClient.this, DrunkTest.class);
					startActivity(intent);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
      
       
       foodButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					getLocationsByType("Food");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
        
        getButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText getLocation = (EditText) findViewById(R.id.locationName);
				try {
					getLocation(getLocation.getText().toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
        
	}

	private void displayLocationsView(final Location[] locations) {
		// Create Linear layout
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
						setDefaultView();
					}
				});
				

				// Add button to layout
				layout.addView(backButton);

				// TODO: Add ListView with inventory - Implemented
				String listArray [] = new String[locations.length];
				for(int i = 0; i < locations.length; i++){
					String str = locations[i].getName() + " - " + locations[i].getType();
					listArray[i] = str;
				}
				ListAdapter la = new ArrayAdapter<String>(this, R.layout.list_item, listArray);
				ListView lv = new ListView(this);
				lv.setAdapter(la); 
				lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(MobileApplicationClient.this, LocationInformation.class);
						intent.putExtra("Name", locations[position].getName());
						startActivity(intent);
					}
					
				});
			
				layout.addView(lv);
				// Make inventory view visible
				setContentView(layout,llp);				
		    }
}

