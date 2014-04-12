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
		Log.i("utsnap", smsText);
		SMS_MANAGER.sendTextMessage(phoneNumber, null, smsText, null, null);
	}

	public static final void sendSms(
		String phoneNumber,
		CommandCategory category,
		Order order,
		String value) {

		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append(category).append(" ").append(order).append(":").append(value);
		Log.i("utsnap", stringBuilder.toString());
		SMS_MANAGER.sendTextMessage(phoneNumber, null, stringBuilder.toString(), null, null);
	}

	public static final void sendSms(
		String phoneNumber,
		CommandCategory category,
		CommandType type,
		String value) {

		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append(category).append(" ").append(type).append(":").append(value);
		Log.i("utsnap", stringBuilder.toString());
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
		Log.i("utsnap", stringBuilder.toString());
		SMS_MANAGER.sendTextMessage(phoneNumber, null, stringBuilder.toString(), null, null);
	}

	public static final void sendSms(
		String phoneNumber,
		CommandCategory category,
		CommandType type) {

		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append(category).append(" ").append(type);
		Log.i("utsnap", stringBuilder.toString());
		SMS_MANAGER.sendTextMessage(phoneNumber, null, stringBuilder.toString(), null, null);
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
		Log.i("utsnap", stringBuilder.toString());
		SMS_MANAGER.sendTextMessage(phoneNumber, null, stringBuilder.toString(), null, null);
	}

	public enum CommandCategory {
		SET,
		GET,
		INFO;

		public static final CommandCategory getValueOf(int index) {
			switch ( index ) {
				case 0 : return SET;
				case 1 : return GET;
				case 2 : return INFO;
			}
			return null;
		}

		public String getSummary() {
			switch (this) {
				case SET:
					return "설정하기";
				case GET:
					return "조회하기";
				case INFO:
					return "현재상태";
			}
			return "뭐지";
		}
	}

	public enum CommandType {
		EMPTY,
		NUM,
		TIME,
		LIMT,
		KIND,
		USE,
		SERVER,
		DEV;

		public static final CommandType getValueOf(int index) {
			switch ( index ) {
				case 0 : return EMPTY;
				case 1 : return NUM;
				case 2 : return TIME;
				case 3 : return LIMT;
				case 4 : return KIND;
				case 5 : return USE;
				case 6 : return SERVER;
				case 7 : return DEV;
			}
			return null;
		}

		public String getSummary() {
			switch (this) {
				case EMPTY: return "";
				case NUM: return "비상연락처";
				case TIME: return "정규 문자발송 시간";
				case LIMT: return "센서 경고범위";
				case KIND: return "센서종류";
				case USE: return "센서 사용유무";
				case SERVER: return "서버 전화번호";
				case DEV: return "개발자 전화번호";
			}
			return "뭐지";
		}


		@Override
		public String toString() {
			if ( this == EMPTY ) {
				return "";
			}
			else {
				return super.toString();
			}
		}
	}

	public enum MachineNumber {
		_1,
		_2,
		_3,
		_4;

		public static final MachineNumber valueOf(int number) {
			MachineNumber machineNumber = null;
			switch (number) {
				case 1:
					machineNumber = _1;
					break;
				case 2:
					machineNumber = _2;
					break;
				case 3:
					machineNumber = _3;
					break;
				case 4:
					machineNumber = _4;
					break;
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

		public String getDetailName() {
			return toString().concat("번 장비");
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
			switch (number) {
				case 1:
					sensorNumber = _1;
					break;
				case 2:
					sensorNumber = _2;
					break;
				case 3:
					sensorNumber = _3;
					break;
				case 4:
					sensorNumber = _4;
					break;
				case 5:
					sensorNumber = _5;
					break;
				case 6:
					sensorNumber = _6;
					break;
				case 7:
					sensorNumber = _7;
					break;
				case 8:
					sensorNumber = _8;
					break;
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

		public String getDetailName() {
			return toString().concat("번 센서");
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
			switch (number) {
				case 1:
					order = _1;
					break;
				case 2:
					order = _2;
					break;
				case 3:
					order = _3;
					break;
				case 4:
					order = _4;
					break;
				case 5:
					order = _5;
					break;
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

		public String getEmergencyContact() {
			return toString().concat(" 번째 비상연락처");
		}

		public String getSmsSendingTime() {
			return toString().concat(" 번쩨 발송시간");
		}
	}

	public enum SensorType {
		ETCETERA(0, "기타"),
		TEMPERATURE(1, "온도"),
		HUMIDITY(2, "습도"),
		MINUS_NUMBER(3, "음수");

		private final int number;
		private final String summary;

		SensorType(int number, String summary) {
			this.number = number;
			this.summary = summary;
		}


		@Override
		public String toString() {
			return String.valueOf(number);
		}

		public String getSummary() {
			return summary;
		}
	}

	public enum Availability {
		AVAILABLE(1, "사용"),
		NOT_AVAILABLE(0, "사용안함");

		private final int number;
		private final String summary;

		Availability(int number, String summary) {
			this.number = number;
			this.summary = summary;
		}

		@Override
		public String toString() {
			return String.valueOf(number);
		}

		public String getSummary() {
			return summary;
		}
	}
}
