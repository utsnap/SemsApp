package com.example.SemsApp.receiver;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.example.SemsApp.activity.MainActivity;
import com.example.SemsApp.application.SemsApplication;
import com.example.SemsApp.preference.PreferenceKeys;
import com.google.gson.Gson;

import java.util.Stack;

import static com.example.SemsApp.application.SemsApplication.MachineType;


/**
 * Created by Administrator on 2014-04-08.
 */
public class OutGoingCallReceiver extends BroadcastReceiver {
	private final static Gson GSON = new Gson();

	private Stack<Activity> activityStack;

	public void onReceive(Context context, Intent intent) {
		activityStack = ((SemsApplication)context.getApplicationContext()).activityStack;

		String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		String oldSemsPhoneNumber = preferences.getString(PreferenceKeys.OLD_SEMS_PHONE_NUMBER, "");
		String newSemsPhoneNumber = preferences.getString(PreferenceKeys.NEW_SEMS_PHONE_NUMBER, "");
		String ledDimmerPhoneNumber = preferences.getString(PreferenceKeys.LED_DIMMER_PHONE_NUMBER, "");
		String carbonHeaterPhoneNumber = preferences.getString(PreferenceKeys.CARBON_HEATER_PHONE_NUMBER, "");

		Intent sedingIntent = new Intent(MainActivity.ACTION_DATA_REQUESTED);
		if ( phoneNumber.equals(oldSemsPhoneNumber) ) {
			sedingIntent.putExtra(MainActivity.EXTRA_MACHINE_TYPE_JSON, GSON.toJson(MachineType.OLD_SEMS));
		}
		else if ( phoneNumber.equals(newSemsPhoneNumber) ) {
			sedingIntent.putExtra(MainActivity.EXTRA_MACHINE_TYPE_JSON, GSON.toJson(MachineType.NEW_SEMS));
		}
		else if ( phoneNumber.equals(ledDimmerPhoneNumber) ) {
			sedingIntent.putExtra(MainActivity.EXTRA_MACHINE_TYPE_JSON, GSON.toJson(MachineType.LED_DIMMER));
		}
		else if ( phoneNumber.equals(carbonHeaterPhoneNumber) ) {
			sedingIntent.putExtra(MainActivity.EXTRA_MACHINE_TYPE_JSON, GSON.toJson(MachineType.CARBON_HEATER));
		}

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
