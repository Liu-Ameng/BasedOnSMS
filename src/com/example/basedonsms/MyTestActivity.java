package com.example.basedonsms;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector;
import android.view.View.OnTouchListener;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MyTestActivity extends Activity implements OnTouchListener,
		GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
	int test_type = 0;
	private GestureDetectorCompat mDetector;
	private static final String DEBUG_TAG = "Gestures";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_test);
		mDetector = new GestureDetectorCompat(this, this);
		mDetector.setOnDoubleTapListener(this);
		TextView tView = (TextView) findViewById(R.id.tv_test_result);
		tView.setOnTouchListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_test, menu);
		return true;
	}

	public void onRadioButtonClicked(View view) {
		// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();

		// Check which radio button was clicked
		switch (view.getId()) {
		case R.id.radioButton1:
			if (checked)
				test_type = 0;
			break;
		case R.id.radioButton2:
			if (checked)
				test_type = 1;
			break;
		}
		Toast.makeText(MyTestActivity.this, "当前测试模式是：" + test_type,
				Toast.LENGTH_SHORT).show();
	}

	public boolean onTouch(View view, MotionEvent event) {
		StringBuilder sBuilder = new StringBuilder();
		if (test_type == 0) {
			sBuilder.append(String.format("current pointer: (%.1f,%.1f)%n",
					event.getX(), event.getY()));
			sBuilder.append("touch state: ");
			if (event.getAction() == MotionEvent.ACTION_DOWN)
				sBuilder.append("touch down\n");
			else if (event.getAction() == MotionEvent.ACTION_MOVE)
				sBuilder.append("touch move\n");
			else if (event.getAction() == MotionEvent.ACTION_UP)
				sBuilder.append("touch up\n");

			int pointer_count = event.getPointerCount();
			sBuilder.append(String.format("point count; %d %n", pointer_count));
			for (int i = 0; i < pointer_count; ++i) {
				sBuilder.append(String.format("pointer %d: (%.1f,%.1f)%n",
						event.getPointerId(i), event.getX(i), event.getY(i)));
			}
		}

		TextView tView = (TextView) findViewById(R.id.tv_test_result);
		tView.setText(sBuilder);

		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		this.mDetector.onTouchEvent(event);
		// Be sure to call the superclass implementation
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent event) {
		Log.d(DEBUG_TAG, "onDown: " + event.toString());
		return true;
	}

	@Override
	public boolean onFling(MotionEvent event1, MotionEvent event2,
			float velocityX, float velocityY) {
		Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
		return true;
	}

	@Override
	public void onLongPress(MotionEvent event) {
		Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		Log.d(DEBUG_TAG, "onScroll: " + e1.toString() + e2.toString());
		return true;
	}

	@Override
	public void onShowPress(MotionEvent event) {
		Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
	}

	@Override
	public boolean onSingleTapUp(MotionEvent event) {
		Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
		return true;
	}

	@Override
	public boolean onDoubleTap(MotionEvent event) {
		Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
		return true;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent event) {
		Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
		return true;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent event) {
		Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
		return true;
	}
}
