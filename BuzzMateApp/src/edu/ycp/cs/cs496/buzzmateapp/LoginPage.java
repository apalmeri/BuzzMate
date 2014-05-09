package edu.ycp.cs.cs496.buzzmateapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginPage extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setDefaultView();
	}
		
	public void setDefaultView() {
        setContentView(R.layout.login_page);
      
		// Add back button
		Button backButton = (Button) findViewById(R.id.backButton);
		
		backButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
