package edu.ycp.cs.cs496.buzzmateapp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.ClientProtocolException;
import org.xml.sax.SAXException;

import edu.ycp.cs.cs496.locations.mobilecontrollers.GetCab;
import edu.ycp.cs.cs496.locations.mobilecontrollers.GetCabList;
import edu.ycp.cs.cs496.locations.mobilecontrollers.GetLocation;
import edu.ycp.cs.cs496.locations.mobilecontrollers.GetLocationList;
import edu.ycp.cs.cs496.locations.mobilecontrollers.GetLocationsByType;
import edu.ycp.cs.cs496.locations.mobilecontrollers.GetUserList;
import edu.ycp.cs.cs496.locations.model.Cab;
import edu.ycp.cs.cs496.locations.model.Location;
import edu.ycp.cs.cs496.locations.model.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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
		List<Location> locations = locationList.getLocationList();
		if(locations != null) {
			displayLocationsView(locations);
		} else {
			Toast.makeText(MobileApplicationClient.this, "No Locations Found!", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void getUsers() throws ClientProtocolException, URISyntaxException, IOException {
		GetUserList userlist = new GetUserList();
		Toast.makeText(MobileApplicationClient.this, "Grabbing User", Toast.LENGTH_SHORT).show();
		List<User> users = userlist.getUser();
		Toast.makeText(MobileApplicationClient.this, "Grabbed User?", Toast.LENGTH_SHORT).show();
		if(users != null) {
			Toast.makeText(MobileApplicationClient.this, "Entering View", Toast.LENGTH_SHORT).show();
			displayUsersView(users);
		} else {
			Toast.makeText(MobileApplicationClient.this, "No Users Found!", Toast.LENGTH_SHORT).show();
		}		
	}


	public void getLocationsByType(String type) throws URISyntaxException, ClientProtocolException, 
	IOException, ParserConfigurationException, SAXException{
		GetLocationsByType locationList = new GetLocationsByType();
		List<Location> locations = locationList.GetLocationsByType(type);
		if(locations != null) {
			displayLocationsView(locations);
		} else {
			Toast.makeText(MobileApplicationClient.this, "No Locations Found!", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void getCab(String cabName) throws URISyntaxException, ClientProtocolException, 
	IOException, ParserConfigurationException, SAXException{
		GetCab controller = new GetCab();
		Cab cab = controller.getCab(cabName);
		List<Cab> singleLocationArray = new ArrayList<Cab>();
		singleLocationArray.add(cab);
		if(cab != null) {
			displayCabsView(singleLocationArray);
		} else {
			Toast.makeText(MobileApplicationClient.this, "No Cabs Found!", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void getCabList() throws URISyntaxException, ClientProtocolException, 
	IOException, ParserConfigurationException, SAXException{
		GetCabList cabList = new GetCabList();
		List<Cab> cabs = cabList.getCab();
		if(cabs != null) {
			displayCabsView(cabs);
		} else {
			Toast.makeText(MobileApplicationClient.this, "No Cabs Found!", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void getLocation(String locationName) throws URISyntaxException, ClientProtocolException, 
	IOException, ParserConfigurationException, SAXException{
		GetLocation controller = new GetLocation();
		Location location = controller.getLocation(locationName);
		List<Location> singleLocationArray = new ArrayList<Location>();
		singleLocationArray.add(location);
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
        Button friendButton = (Button) findViewById(R.id.friends_button);
        
        
        // TODO: Set onClickListeners for buttons
       barButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					getLocationsByType("Bar");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
       
       friendButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					getUsers();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
       
       drunkTestButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					Intent intent = new Intent(MobileApplicationClient.this, DrunkTest.class);
					startActivity(intent);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
       
      	settingsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					Intent intent = new Intent(MobileApplicationClient.this, LoginPage.class);
					startActivity(intent);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
       
       foodButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					getLocationsByType("Food");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        
       getButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText getText = (EditText) findViewById(R.id.locationName);
				try {
					getLocation(getText.getText().toString());				
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					getCab(getText.getText().toString());				
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
       
		
		cabButton.setOnClickListener(new View.OnClickListener() {
	
			@Override
			public void onClick(View v) {
				try {
					getCabList();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		soberButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					Intent intent = new Intent(MobileApplicationClient.this, MySoberFriends.class);
					startActivity(intent);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	private void displayLocationsView(final List<Location> locations) {
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
				backButton.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						setDefaultView();
					}
				});

				// Add button to layout
				layout.addView(backButton);
				
				ListAdapter la = new ArrayAdapter<Location>(this, R.layout.list_item, locations);
				ListView lv = new ListView(this);
				lv.setAdapter(la); 
				lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Intent intent = new Intent(MobileApplicationClient.this, LocationInformation.class);
						intent.putExtra("Name", locations.get(position).getName());
						startActivity(intent);
					}
					
				});
				
				layout.addView(lv);
				// Make inventory view visible
				setContentView(layout,llp);				
		    }
	private void displayCabsView(final List<Cab> cabs) {
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
						setDefaultView();
					}
				});


				// Add button to layout
				layout.addView(backButton);

				ListAdapter la = new ArrayAdapter<Cab>(this, R.layout.list_item, cabs);
				ListView lv = new ListView(this);
				lv.setAdapter(la); 
				lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Intent intent = new Intent(MobileApplicationClient.this, CabInformation.class);
						intent.putExtra("Name", cabs.get(position).getName());
						startActivity(intent);
					}

				});

				layout.addView(lv);
				// Make inventory view visible
				setContentView(layout,llp);				
		    }
	
	private void displayUsersView(final List<User> users) {
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
				setDefaultView();
			}
		});


		// Add button to layout
		layout.addView(backButton);

		ListAdapter la = new ArrayAdapter<User>(this, R.layout.list_item, users);
		ListView lv = new ListView(this);
		lv.setAdapter(la); 
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(MobileApplicationClient.this, UserInformation.class);
				intent.putExtra("Name", users.get(position).getUsername());
				startActivity(intent);
			}

		});

		layout.addView(lv);
		// Make inventory view visible
		setContentView(layout,llp);	
		
	}
}

