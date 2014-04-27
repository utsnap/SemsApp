package com.example.SemsApp.receiver;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import com.example.SemsApp.activity.MainActivity;
import com.example.SemsApp.application.SemsApplication;
import com.google.gson.Gson;

import java.util.Stack;

import static com.example.SemsApp.application.SemsApplication.MachineType.OLD_SEMS;

/**
 * Created by Administrator on 14. 3. 20.
 * 문자수신을 처리하는 브로드캐스트 리시버.
 */
public class SmsReceiver extends BroadcastReceiver {
	public static final String EXTRA_ACTON = "extra_action";
	public static final int START_SERVICE = 1;
	public static final int STOP_SERVICE = 2;
	private Stack<Activity> activityStack;

	private SemsApplication semsApplication;

	public static final String ACTION_SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

	public void onReceive(Context context, Intent intent) {
		semsApplication = (SemsApplication)context.getApplicationContext();

		activityStack = semsApplication.activityStack;

		/*이 부분만 확인한다.*/
		if ( intent.getAction().equals(ACTION_SMS_RECEIVED) ) {
			//Log.i("utsnap", "문자 왔어요~~");
			Bundle bundle = intent.getExtras();
			if ( bundle == null ) {
				return;
			}

			Object[] pdusObjects = (Object[]) bundle.get("pdus");
			if ( pdusObjects == null ) {
				return;
			}
			Log.i("utsnap", "pdus size : " + pdusObjects.length);
			SmsMessage[] smsMessages = new SmsMessage[pdusObjects.length];
			String[] strings = new String[2];
			for ( int i = 0; i < smsMessages.length; i++ ) {
				smsMessages[i] = SmsMessage.createFromPdu((byte[])pdusObjects[i]);
				Log.i("utsnap", "발신자 : " + smsMessages[i].getOriginatingAddress());
				Log.i("utsnap", "내용 : " + smsMessages[i].getMessageBody());
				strings[0] = smsMessages[i].getOriginatingAddress();
				strings[1] = smsMessages[i].getMessageBody();
			}

			Intent intent1 = new Intent(context, MainActivity.class);
			intent1.setAction(MainActivity.ACTION_INFO_DATA_RECEIVED);
			Gson gson = new Gson();
			String machineTypeJson = gson.toJson(OLD_SEMS);
			intent1.putExtra(MainActivity.EXTRA_MACHINE_TYPE_JSON, machineTypeJson);

			PendingIntent pendingIntent;
			intent1.putExtra("sms_data", strings);
			if ( activityStack.size() == 0 ) {
				intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				pendingIntent = PendingIntent.getActivity(context, 0, intent1, PendingIntent.FLAG_ONE_SHOT);
			}
			else {
				intent1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				pendingIntent = PendingIntent.getActivity(context, 0, intent1, PendingIntent.FLAG_ONE_SHOT);
			}
			try {
				pendingIntent.send();
			} catch (PendingIntent.CanceledException e) {
				e.printStackTrace();
			}
		}
	}
}
