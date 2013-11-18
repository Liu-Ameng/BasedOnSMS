package com.example.basedonsms;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class SMS_Test_Activity extends Activity {

	TextView tView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms__test_);
		tView = (TextView)findViewById(R.id.sms_test_tv);
		showResults();
	}

	private void showResults() {
		Bundle bundle = this.getIntent().getExtras();
		int requestCode = bundle.getInt("requestCode");
		StringBuilder sBuilder = new StringBuilder();
		if(requestCode == 0){
			sBuilder.append("Sender : "+bundle.getString("sendAddr")+"\n");
			sBuilder.append("When   : "+bundle.getString("time")+"\n");
			sBuilder.append("Content: "+bundle.getString("msgBody")+"\n");
		}
		tView.setText(sBuilder);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sms__test_, menu);
		return true;
	}

}
