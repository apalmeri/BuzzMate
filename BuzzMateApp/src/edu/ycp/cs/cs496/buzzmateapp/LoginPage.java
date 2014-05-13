package edu.ycp.cs.cs496.buzzmateapp;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs.cs496.locations.mobilecontrollers.GetLocation;
import edu.ycp.cs.cs496.locations.mobilecontrollers.GetUser;
import edu.ycp.cs.cs496.locations.model.Location;
import edu.ycp.cs.cs496.locations.model.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setDefaultView();
	}
		
	public void setDefaultView() {
        setContentView(R.layout.login_page);
        
        Button loginButton = (Button) findViewById(R.id.logInButton);
        final EditText usernameInput = (EditText) findViewById(R.id.UsernameText);
        final EditText passwordInput = (EditText) findViewById(R.id.passwordText);
        
        loginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					String username = usernameInput.getText().toString();
					String password = passwordInput.toString();
					
					GetUser controller = new GetUser();
					User user = controller.getUSer(username);
					String userActualPass = user.getPassword();
					if(password.equals(userActualPass)) {
						Intent intent = new Intent(LoginPage.this, MobileApplicationClient.class);
						startActivity(intent);
					}else{
						Toast.makeText(LoginPage.this, "Not a User, try hitting New User", Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
