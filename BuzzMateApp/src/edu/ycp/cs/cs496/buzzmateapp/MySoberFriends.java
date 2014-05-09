package edu.ycp.cs.cs496.buzzmateapp;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;

import edu.ycp.cs.cs496.locations.mobilecontrollers.GetLocationList;
import edu.ycp.cs.cs496.locations.mobilecontrollers.GetSoberFriendList;
import edu.ycp.cs.cs496.locations.model.Location;
import edu.ycp.cs.cs496.locations.model.SoberFriend;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MySoberFriends extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setDefaultView();
	}
		
	public void setDefaultView() {
        setContentView(R.layout.soberbuddies_layout);
        
		// Add back button
		Button backButton = (Button) findViewById(R.id.backButton);
		
		// TODO: Add back button onClickListener - Implemented
		
		backButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub - Implemented
				finish();
			}
		});
		
		GetSoberFriendList soberFriendList = new GetSoberFriendList();
		SoberFriend[] soberFriends = null;
		try {
			soberFriends = soberFriendList.getSoberFriend();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(soberFriends != null) {
			
			String listArray [] = new String[soberFriends.length];
			for(int i = 0; i < soberFriends.length; i++){
				String str = soberFriends[i].getName() + " - " + soberFriends[i].getIsAvail();
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

				}
				
			});
			
			this.addContentView(lv, null);
			
			
		} else {
			Toast.makeText(MySoberFriends.this, "No Sober Friends Found!", Toast.LENGTH_SHORT).show();
		}			
	}
}