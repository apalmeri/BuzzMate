package edu.ycp.cs.cs496.buzzmateapp;

import java.util.Date;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DrunkTest extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setDefaultView();
	}
		
	public void setDefaultView() {
        setContentView(R.layout.drunk_test);
		// Add back button
		Button backButton = (Button) findViewById(R.id.drunkBackButton);
		
		// TODO: Add back button onClickListener - Implemented
		
		backButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub - Implemented
				finish();
			}
		});
		
		final Button pushButton = (Button) findViewById(R.id.pushButton);
		final View pushButtonView = (View) findViewById(R.id.pushButton);
		
		pushButton.setOnClickListener(new View.OnClickListener() {
			
			long totalTime;
			long startTime;
			long endTime;
			int numberOfClicks = 0;
			
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				// TODO Auto-generated method stub - Implemented
				numberOfClicks++;
				if(numberOfClicks <= 11 &&  numberOfClicks > 1){
					endTime = new Date().getTime();
					totalTime += (endTime - startTime);
					startTime = new Date().getTime();
				}else if(numberOfClicks == 1){
					startTime = new Date().getTime();
				}else{
					finishResultsView(totalTime);
				}
				//Move Button
				Random randomGenerator = new Random();
				double newX = Math.random() * 264 + 1;
				//randomGenerator.nextInt(100);
				double newY = Math.random() * 436 + 1;
				//randomGenerator.nextInt(100);
				
				AbsoluteLayout.LayoutParams buttonParams = (AbsoluteLayout.LayoutParams)pushButton.getLayoutParams();
				buttonParams.x = (int) newX;
				buttonParams.y = (int) newY;
				pushButton.setLayoutParams(buttonParams);
			}
		});
	}
	
	public void finishResultsView(long totalTime) {
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
		
		TextView results = new TextView(this);
		totalTime /= 1000;
		results.setText("It took you " + totalTime + " seconds");
		
		layout.addView(backButton);
		layout.addView(results);
		setContentView(layout,llp);
	}
}
