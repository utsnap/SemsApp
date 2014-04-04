package com.example.SemsApp.utility;

import android.telephony.SmsManager;
import android.util.Log;

/**
 * Created by Administrator on 14. 3. 23.
 * 기계에 문자를 보내는 기능을 구현한다.
 */
public final class SmsSender {
	private static final SmsManager SMS_MANAGER = SmsManager.getDefault();

	public static final void sendSms(
		String phoneNumber,
		CommandCategory category) {

		String smsText = category.toString();
		//Log.i("utsnap", smsText);
		SMS_MANAGER.sendTextMessage(phoneNumber, null, smsText, null, null);
	}

	public static final void sendSms(
		String phoneNumber,
		CommandCategory category,
		Order order,
		String value) {

		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append(category).append(" ").append(order).append(":").append(value);
		//Log.i("utsnap", stringBuilder.toString());
		SMS_MANAGER.sendTextMessage(phoneNumber, null, stringBuilder.toString(), null, null);
	}

	public static final void sendSms(
		String phoneNumber,
		CommandCategory category,
		CommandType type,
		String value) {

		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append(category).append(" ").append(type).append(":").append(value);
		//Log.i("utsnap", stringBuilder.toString());
		SMS_MANAGER.sendTextMessage(phoneNumber, null, stringBuilder.toString(), null, null);
	}

	public static final void sendSms(
		String phoneNumber,
		CommandCategory category,
		CommandType type,
		MachineNumber machineNumber,
		SensorNumber sensorNumber,
		String value) {

		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append(category).append(" ").append(type).append(machineNumber).append("-").append(sensorNumber).append(":").append(value);
		//Log.i("utsnap", stringBuilder.toString());
		SMS_MANAGER.sendTextMessage(phoneNumber, null, stringBuilder.toString(), null, null);
	}

	public static final void sendSms(
		String phoneNumber,
		CommandCategory category,
		CommandType type) {

		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append(category).append(" ").append(type);
		Log.i("utsnap", stringBuilder.toString());
		//SMS_MANAGER.sendTextMessage(phoneNumber, null, stringBuilder.toString(), null, null);
	}

	public static final void sendSms(
		String phoneNumber,
		CommandCategory category,
		CommandType type,
		MachineNumber machineNumber) {

		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append(category).append(" ").append(type).append(machineNumber);
		//Log.i("utsnap", stringBuilder.toString());
		SMS_MANAGER.sendTextMessage(phoneNumber, null, stringBuilder.toString(), null, null);
	}

	public static final void sendSms(
		String phoneNumber,
		CommandCategory category,
		CommandType type,
		Order order,
		String value) {
		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append(category).append(" ").append(type).append(order).append(":").append(value);
		//Log.i("utsnap", stringBuilder.toString());
		SMS_MANAGER.sendTextMessage(phoneNumber, null, stringBuilder.toString(), null, null);
	}

	public enum CommandCategory {
		SET,
		GET,
		INFO;
	}

	public enum CommandType {
		NUM,
		TIME,
		LIMT,
		KIND,
		USE,
		SERVER,
		DEV;
	}

	public enum MachineNumber {
		_1,
		_2,
		_3,
		_4;

		public static final MachineNumber valueOf(int number) {
			MachineNumber machineNumber = null;
			switch ( number ) {
				case 1 : machineNumber = _1;break;
				case 2 : machineNumber = _2;break;
				case 3 : machineNumber = _3;break;
				case 4 : machineNumber = _4;break;
			}
			return machineNumber;
		}

		public int getInteger() {
			return Integer.parseInt(this.toString());
		}

		@Override
		public String toString() {
			return super.toString().replace("_", "");
		}
	}

	public enum SensorNumber {
		_1,
		_2,
		_3,
		_4,
		_5,
		_6,
		_7,
		_8;

		public static final SensorNumber valueOf(int number) {
			SensorNumber sensorNumber = null;
			switch ( number ) {
				case 1 : sensorNumber = _1;break;
				case 2 : sensorNumber = _2;break;
				case 3 : sensorNumber = _3;break;
				case 4 : sensorNumber = _4;break;
				case 5 : sensorNumber = _5;break;
				case 6 : sensorNumber = _6;break;
				case 7 : sensorNumber = _7;break;
				case 8 : sensorNumber = _8;break;
			}
			return sensorNumber;
		}

		public int getInteger() {
			return Integer.parseInt(this.toString());
		}

		@Override
		public String toString() {
			return super.toString().replace("_", "");
		}
	}

	public enum Order {
		_1,
		_2,
		_3,
		_4,
		_5;

		public static final Order valueOf(int number) {
			Order order = null;
			switch ( number ) {
				case 1 : order = _1;break;
				case 2 : order = _2;break;
				case 3 : order = _3;break;
				case 4 : order = _4;break;
				case 5 : order = _5;break;
			}
			return order;
		}

		@Override
		public String toString() {
			return super.toString().replace("_", "");
		}

		public int getInteger() {
			return Integer.parseInt(this.toString());
		}
	}
}
