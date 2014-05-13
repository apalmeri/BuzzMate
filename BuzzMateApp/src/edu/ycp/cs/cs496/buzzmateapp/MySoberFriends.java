package edu.ycp.cs.cs496.buzzmateapp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import edu.ycp.cs.cs496.buzzmateapp.R.layout;
import edu.ycp.cs.cs496.locations.mobilecontrollers.GetSoberFriendList;
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
		try {
			setDefaultView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void setDefaultView() throws ClientProtocolException, URISyntaxException, IOException {
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
				finish();
			}
		});
		
		Button addButton = new Button(this);
		addButton.setText(" + ");
		addButton.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		addButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MySoberFriends.this, AddSoberFriendPage.class);
				startActivity(intent);
			}
		});

		// Add button to layout
		layout.addView(backButton);
		layout.addView(addButton);
		
		GetSoberFriendList friendList = new GetSoberFriendList();
		
			
		Toast.makeText(MySoberFriends.this, "Pulling Friends", Toast.LENGTH_SHORT).show();
		
		final List<SoberFriend> friends = friendList.getSoberFriend();
		
		Toast.makeText(MySoberFriends.this, "Pulled Friends", Toast.LENGTH_SHORT).show();
		
		ListAdapter la = new ArrayAdapter<SoberFriend>(this, R.layout.list_item, friends);
		ListView lv = new ListView(this);
		lv.setAdapter(la); 
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(MySoberFriends.this, SoberFriendInformation.class);
				intent.putExtra("Name", friends.get(position).getName());
				startActivity(intent);
			}
			
		});
		// Make inventory view visible
		layout.addView(lv);
		setContentView(layout,llp);	
	}
}