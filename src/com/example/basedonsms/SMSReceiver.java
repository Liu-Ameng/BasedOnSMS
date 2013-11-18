package com.example.basedonsms;

import java.sql.Date;
import java.text.DateFormat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {
	private final static int requestCode = 0;

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		String msg_bodyString = "";
		String send_addrString = "";
		Date sendDate = new Date(0);

		if (bundle != null) {
			Object[] pdusObjects = (Object[]) bundle.get("pdus");
			SmsMessage[] smsMessages = new SmsMessage[pdusObjects.length];
			for (int i = 0; i < pdusObjects.length; ++i) {
				smsMessages[i] = SmsMessage
						.createFromPdu((byte[]) pdusObjects[i]);
				msg_bodyString += smsMessages[i].getDisplayMessageBody();
			}
			send_addrString = smsMessages[0].getDisplayOriginatingAddress();
			sendDate = new Date(smsMessages[0].getTimestampMillis());
		}
		Intent intent2 = new Intent(context, SMS_Test_Activity.class);
		Bundle bundle2 = new Bundle();
		bundle2.putInt("requestCode", requestCode);
		bundle2.putString("sendAddr", send_addrString);
		DateFormat format = DateFormat.getDateTimeInstance();
		bundle2.putString("time", format.format(sendDate));
		bundle2.putString("msgBody", msg_bodyString);
		intent2.putExtras(bundle2);
		intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent2);
	}
}
