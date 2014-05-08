package com.example.SemsApp.receiver;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import com.example.SemsApp.activity.MainActivity;
import com.example.SemsApp.application.SemsApplication;
import com.example.SemsApp.data.AbsData;
import com.example.SemsApp.data.OldSemsData;
import com.example.SemsApp.data.new_sems.*;
import com.example.SemsApp.preference.PreferenceKeys;
import com.example.SemsApp.utility.NewSemsSmsSender;
import com.google.gson.Gson;

import java.util.Calendar;
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

			String smsBody = intent.getStringExtra("sms");
			String phone = intent.getStringExtra("phone");
			if ( preferences.getString(PreferenceKeys.NEW_SEMS_PHONE_NUMBER, "").equals(phone) ) {
				String[] smsBodyLines = smsBody.split("\n");
				if (smsBodyLines[0] == null) {
					return;
				}

				//선택 : 진동을 울린다.
				//vibrator.vibrate(vibrationPattern, -1);
				//완료

				Intent sendingIntent = new Intent(context, MainActivity.class);
				sendingIntent.setAction(MainActivity.ACTION_NEW_SEMS_DATA_RECEIVED);

				Object categoryOrType = NewSemsSmsSender.CommandCategory.findCommandCategory(smsBodyLines[0]);
				Log.i("utsnap", "메세지 수신");
					/*command category인 경우*/
				if ( categoryOrType instanceof NewSemsSmsSender.CommandCategory ) {
					NewSemsSmsSender.CommandCategory commandCategory = (NewSemsSmsSender.CommandCategory) categoryOrType;
					if ( commandCategory.equals(NewSemsSmsSender.CommandCategory.INFO) ) {
						Object newSemsData = commandCategory.getNewSemsData(smsBody);
						NewSemsInfoData newSemsInfoData = (NewSemsInfoData) newSemsData;
						sendingIntent.putExtra(MainActivity.EXTRA_DATA_JSON, GSON.toJson(newSemsData));
						sendingIntent.putExtra(MainActivity.EXTRA_DATA_CLASS, commandCategory.getNewSemsDataClass());

						Log.i("utsnap", newSemsInfoData.machineNumber.getDetailName());
						Log.i("utsnap", newSemsInfoData.where.getSummary());
						Log.i("utsnap", String.valueOf(newSemsInfoData.temparature));
						for (NewSemsSmsSender.SensorNumber sensorNumber : NewSemsSmsSender.SensorNumber.values()) {
							Log.i("utsnap", sensorNumber.getDetailName() + " : "
									+ newSemsInfoData.sensorTypeEnumMap.get(sensorNumber).getSummary() + " : "
									+ newSemsInfoData.stringValueEnumMap.get(sensorNumber) );
						}

						PendingIntent pendingIntent;
						if ( activityStack.size() == 0 ) {
							sendingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							pendingIntent = PendingIntent.getActivity(context, 0, sendingIntent, PendingIntent.FLAG_ONE_SHOT);
						}
						else {
							sendingIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
							pendingIntent = PendingIntent.getActivity(context, 0, sendingIntent, PendingIntent.FLAG_ONE_SHOT);
						}
						try {
							pendingIntent.send();
						} catch (PendingIntent.CanceledException e) {
							e.printStackTrace();
						}
						return;
					}
				}

				categoryOrType = NewSemsSmsSender.CommandType.findCommandType(smsBodyLines[0]);

		            /*command type인 경우*/
				if (categoryOrType instanceof NewSemsSmsSender.CommandType) {
					NewSemsSmsSender.CommandType commandType = (NewSemsSmsSender.CommandType) categoryOrType;
					AbsNewSemsData newSemsData = (AbsNewSemsData) commandType.getNewSemsData(smsBody);

					sendingIntent.putExtra(MainActivity.EXTRA_DATA_JSON, GSON.toJson(newSemsData));
					sendingIntent.putExtra(MainActivity.EXTRA_DATA_CLASS, commandType.getNewSemsDataClass());

					if ( newSemsData instanceof NewSemsBaseData) {
						NewSemsBaseData newSemsBaseData = (NewSemsBaseData) newSemsData;
						Log.i("utsnap", newSemsBaseData.commands);
						Log.i("utsnap", newSemsBaseData.machine);
						Log.i("utsnap", newSemsBaseData.sensor);
						Log.i("utsnap", newSemsBaseData.smsSendingTime1);
						Log.i("utsnap", newSemsBaseData.smsSendingTime2);
					}
					else if ( newSemsData instanceof NewSemsEmergencyContactData) {
						NewSemsEmergencyContactData newSemsEmergencyContactData = (NewSemsEmergencyContactData) newSemsData;
						for ( NewSemsSmsSender.Order order : newSemsEmergencyContactData.emergencyContactEnumMap.keySet() ) {
							Log.i("utsnap", order.getEmergencyContact() + " : " + newSemsEmergencyContactData.emergencyContactEnumMap.get(order));
						}
					}
					else if ( newSemsData instanceof NewSemsKindData) {
						NewSemsKindData newSemsKindData = (NewSemsKindData) newSemsData;
						for (NewSemsSmsSender.SensorNumber sensorNumber : newSemsKindData.sensorTypeEnumMap.keySet()) {
							Log.i("utsnap", sensorNumber.getDetailName() + " : " + newSemsKindData.sensorTypeEnumMap.get(sensorNumber).getSummary());
						}
					}
					else if ( newSemsData instanceof NewSemsLimitData) {
						NewSemsLimitData newSemsLimitData = (NewSemsLimitData) newSemsData;
						for (NewSemsSmsSender.SensorNumber sensorNumber : newSemsLimitData.limitDataEnumMap.keySet()) {
							Log.i("utsnap", sensorNumber.getDetailName()
									+ " : " + newSemsLimitData.limitDataEnumMap.get(sensorNumber).get(NewSemsLimitData.LimitType.UPPER_LIMIT)
									+ ", " + newSemsLimitData.limitDataEnumMap.get(sensorNumber).get(NewSemsLimitData.LimitType.LOWER_LIMIT));
						}
					}
					else if ( newSemsData instanceof NewSemsSmsSendingTimeData) {
						NewSemsSmsSendingTimeData newSemsSmsSendingTimeData = (NewSemsSmsSendingTimeData) newSemsData;
						//Log.i("utsnap", "size : " + newSemsSmsSendingTimeData.smsSendingTimeStringEnumMap.keySet().size());
						for (NewSemsSmsSender.Order order : newSemsSmsSendingTimeData.smsSendingTimeStringEnumMap.keySet()) {
							if ( newSemsSmsSendingTimeData.smsSendingTimeStringEnumMap.get(order).equals("-") ) {
								Log.i("utsnap", order.getSmsSendingTime() + " : 지정안됨");
							}
							else {
								Log.i("utsnap", order.getSmsSendingTime()
										+ " : " + newSemsSmsSendingTimeData.smsSendingTimeDataEnumMap.get(order).get(Calendar.HOUR)
										+ ", " + newSemsSmsSendingTimeData.smsSendingTimeDataEnumMap.get(order).get(Calendar.MINUTE));
							}
							//Log.i("utsnap", newSemsSmsSendingTimeData.smsSendingTimeStringEnumMap.get(order));
						}
					}
					else if ( newSemsData instanceof NewSemsUseData ) {
						NewSemsUseData newSemsUseData = (NewSemsUseData) newSemsData;
						for (NewSemsSmsSender.SensorNumber sensorNumber : newSemsUseData.sensorAvailabilityEnumMap.keySet()) {
							Log.i("utsnap", sensorNumber.getDetailName() + " : " + newSemsUseData.sensorAvailabilityEnumMap.get(sensorNumber).getSummary());
						}
					}

					PendingIntent pendingIntent;
					if ( activityStack.size() == 0 ) {
						sendingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						pendingIntent = PendingIntent.getActivity(context, 0, sendingIntent, PendingIntent.FLAG_ONE_SHOT);
					}
					else {
						sendingIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
						pendingIntent = PendingIntent.getActivity(context, 0, sendingIntent, PendingIntent.FLAG_ONE_SHOT);
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
}
