package com.smartgrup;

import com.smarpgrup.smartcontrol.lgnActv;
import com.smarpgrup.smartcontrol.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);		
		Intent toactivity = null;
		toactivity = new Intent(WelcomeActivity.this, lgnActv.class);
		startActivity(toactivity);
	}
}
