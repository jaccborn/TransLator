package com.hui.translator.ui;

import com.hui.translator.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

/**
 * 欢迎界面
 */
public class SplashActivity extends Activity  {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		
		GradientDrawable gradientDrawable = new GradientDrawable(
				Orientation.TOP_BOTTOM, new int[] { Color.WHITE,
						Color.rgb(100, 255, 255) });
		getWindow().setBackgroundDrawable(gradientDrawable);
		
		
		//2秒跳转到主界面
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				startActivity(new Intent(SplashActivity.this, MainActivity.class));
				finish();
				
			}
		}, 3000);
	}
}
