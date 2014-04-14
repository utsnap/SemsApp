package com.example.SemsApp.receiver;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsMessage;
import android.util.Log;
import com.example.SemsApp.activity.MainActivity;
import com.example.SemsApp.application.SemsApplication;
import com.example.SemsApp.preference.PreferenceKeys;
import com.example.SemsApp.utility.NewSemsSmsSender;
import com.google.gson.Gson;

import java.util.Stack;

import static com.example.SemsApp.application.SemsApplication.MachineType.OLD_SEMS;

/**
 * Created by vaio-pc on 2014-04-13.
 */
public class NewSemsSmsReceiver extends BroadcastReceiver {
	public static final int START_SERVICE = 1;
	public static final int STOP_SERVICE = 2;
	private Stack<Activity> activityStack;

	private SemsApplication semsApplication;

	public static final String ACTION_SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

	public void onReceive(Context context, Intent intent) {
		semsApplication = (SemsApplication)context.getApplicationContext();

		activityStack = semsApplication.activityStack;

		String newSemsPhoneNumber = PreferenceManager.getDefaultSharedPreferences(context).getString(PreferenceKeys.NEW_SEMS_PHONE_NUMBER, "");

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

			for ( int i = 0; i < smsMessages.length; i++ ) {
				smsMessages[i] = SmsMessage.createFromPdu((byte[])pdusObjects[i]);
				Log.i("utsnap", "발신자 : " + smsMessages[i].getOriginatingAddress());
				Log.i("utsnap", "내용 : " + smsMessages[i].getMessageBody());

				//필수 : 프리퍼런스에 저장된 전화번호와 수신 전화번호를 비교한다.
				if ( smsMessages[i].getOriginatingAddress().equals(newSemsPhoneNumber) ) {
					//필수 : sms의 내용을 분석하여 command category와 command type을 선별한다.
					String[] smsBodyLines = smsMessages[i].getMessageBody().split("\n");
					if ( smsBodyLines[0] == null ) {
						break;
					}

					Object categoryOrType = NewSemsSmsSender.CommandType.findCommandType(smsBodyLines[0]);
					if ( categoryOrType == null ) {
						break;
					}
					/*command type인 경우*/
					if ( categoryOrType instanceof NewSemsSmsSender.CommandType ) {
						//필수 : 메인 액티비티에 인텐트를 날려야 한다.
						NewSemsSmsSender.CommandCategory commandCategory = (NewSemsSmsSender.CommandCategory) categoryOrType;
						Intent sendingIntent = new Intent(context, MainActivity.class);
						sendingIntent.setAction(MainActivity.ACTION_NEW_SEMS_DATA_RECEIVED);
						sendingIntent.setType(MainActivity.TYPE_COMMAND_TYPE_DATA);
						Object newSemsData = commandCategory.getNewSemsData(smsMessages[0].getMessageBody());
						sendingIntent.putExtra(MainActivity.EXTRA_NEW_SEMS_DATA, (java.io.Serializable) newSemsData);
						Class newSemsDataClass = commandCategory.getNewSemsDataClass();

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
					/*command category인 경우*/
					else {
						;
					}
				}
				else {
					break;
				}
				//완료
			}
		}
	}
}
