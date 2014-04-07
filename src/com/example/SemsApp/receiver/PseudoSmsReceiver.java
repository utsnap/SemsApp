package com.example.SemsApp.receiver;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import com.example.SemsApp.activity.MainActivity;
import com.example.SemsApp.application.SemsApplication;
import com.example.SemsApp.data.OldSemsData;
import com.example.SemsApp.preference.PreferenceKeys;
import com.google.gson.Gson;

import java.util.Stack;

/**
 * Created by Administrator on 2014-04-06.
 */
public class PseudoSmsReceiver extends BroadcastReceiver {
	public static final String EXTRA_ACTON = "extra_action";
	public static final Gson GSON = new Gson();
	public static final int START_SERVICE = 1;
	public static final int STOP_SERVICE = 2;
	private static final long[] vibrationPattern = {500, 200, 500, 200, 500, 200};

	private Stack<Activity> activityStack;

	private SemsApplication semsApplication;

	public static final String ACTION_PSEUDO_SMS_RECEIVED = "android.provider.Telephony.PSEUDO_SMS_RECEIVED";

	public void onReceive(Context context, Intent intent) {
		semsApplication = (SemsApplication)context.getApplicationContext();

		activityStack = semsApplication.activityStack;

		Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		/*이 부분만 확인한다.*/
		if ( intent.getAction().equals(ACTION_PSEUDO_SMS_RECEIVED) ) {
			//Log.i("utsnap", "유사문자 왔어요~~");

			String sms = intent.getStringExtra("sms");
			String phone = intent.getStringExtra("phone");
			if ( preferences.getString(PreferenceKeys.OLD_SEMS_PHONE_NUMBER, "").equals(phone) ) {
				//선택 : 진동을 울린다.
				vibrator.vibrate(vibrationPattern, -1);
				//완료

				int dataLabIndex = Integer.parseInt(sms.substring(1, 2)) - 1;
				//Log.i("utsnap", String.valueOf(dataLabIndex));

				Intent sedingIntent = new Intent(context, MainActivity.class);
				sedingIntent.setAction(MainActivity.ACTION_DATA_RECEIVED);
				String machineTypeJson = GSON.toJson(SemsApplication.MachineType.OLD_SEMS);
				sedingIntent.putExtra(MainActivity.EXTRA_MACHINE_TYPE_JSON, machineTypeJson);
				sedingIntent.putExtra(MainActivity.EXTRA_DATA_LAB_INDEX, dataLabIndex);
				sedingIntent.putExtra(MainActivity.EXTRA_DATA_CLASS, OldSemsData.class);
				OldSemsData oldSemsData = OldSemsData.getInstance(Integer.parseInt(sms.substring(1, 2)), sms);
				String dataJson = GSON.toJson(oldSemsData);
				sedingIntent.putExtra(MainActivity.EXTRA_DATA_JSON, dataJson);

				PendingIntent pendingIntent;
				if ( activityStack.size() == 0 ) {
					sedingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					pendingIntent = PendingIntent.getActivity(context, 0, sedingIntent, PendingIntent.FLAG_ONE_SHOT);
				}
				else {
					sedingIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
					pendingIntent = PendingIntent.getActivity(context, 0, sedingIntent, PendingIntent.FLAG_ONE_SHOT);
				}
				try {
					pendingIntent.send();
				} catch (PendingIntent.CanceledException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
