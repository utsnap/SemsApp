package com.example.SemsApp.utility;

import android.telephony.SmsManager;
import android.util.Log;
import com.example.SemsApp.data.new_sems.*;

/**
 * Created by Administrator on 14. 3. 23.
 * 기계에 문자를 보내는 기능을 구현한다.
 */
public final class NewSemsSmsSender {
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
		Log.i("utsnap", stringBuilder.toString());
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
		SET {
			@Override
			public Object getNewSemsData(String SmsBodyf) {
				return null;
			}

			@Override
			public Class getNewSemsDataClass() {
				return null;
			}
		},
		GET {
			@Override
			public Object getNewSemsData(String SmsBodyf) {
				return null;
			}

			@Override
			public Class getNewSemsDataClass() {
				return null;
			}
		},
		INFO {
			@Override
			public Object getNewSemsData(String SmsBody) {
				return NewSemsInfoData.getInstance(SmsBody);
			}

			@Override
			public Class getNewSemsDataClass() {
				return NewSemsInfoData.class;
			}
		};

		/**
		 * 문자를 받았을때, 카데고리에 맞는 데이터를 생성한다.
		 * */
		public abstract Object getNewSemsData(String SmsBody);

		/**
		 * 생성되는 객체의 클래스를 반환한다.
		 * */
		public abstract Class getNewSemsDataClass();

		public static final CommandCategory getValueOf(int index) {
			switch ( index ) {
				case 0 : return SET;
				case 1 : return GET;
				case 2 : return INFO;
			}
			return null;
		}

		public static final CommandCategory findCommandCategory(String firstLine) {
			if ( firstLine.contains("외부") ) {
				return INFO;
			}
			else {
				return null;
			}
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
		EMPTY {
			@Override
			public Object getNewSemsData(String smsBody) {
				return NewSemsBaseData.getInstance(smsBody);
			}

			@Override
			public Class getNewSemsDataClass() {
				return NewSemsBaseData.class;
			}
		},
		NUM {
			@Override
			public Object getNewSemsData(String smsBody) {
				return NewSemsEmergencyContactData.getInstance(smsBody);
			}

			@Override
			public Class getNewSemsDataClass() {
				return NewSemsEmergencyContactData.class;
			}
		},
		TIME {
			@Override
			public Object getNewSemsData(String smsBody) {
				return NewSemsSmsSendingTimeData.getInstance(smsBody);
			}

			@Override
			public Class getNewSemsDataClass() {
				return NewSemsSmsSendingTimeData.class;
			}
		},
		LIMT {
			@Override
			public Object getNewSemsData(String smsBody) {
				return NewSemsLimitData.getInstance(smsBody);
			}

			@Override
			public Class getNewSemsDataClass() {
				return NewSemsLimitData.class;
			}
		},
		KIND {
			@Override
			public Object getNewSemsData(String smsBody) {
				return NewSemsKindData.getInstance(smsBody);
			}

			@Override
			public Class getNewSemsDataClass() {
				return NewSemsKindData.class;
			}
		},
		USE {
			@Override
			public Object getNewSemsData(String smsBody) {
				return NewSemsUseData.getInstance(smsBody);
			}

			@Override
			public Class getNewSemsDataClass() {
				return NewSemsUseData.class;
			}
		},
		SERVER {
			@Override
			public Object getNewSemsData(String smsBody) {
				return null;
			}

			@Override
			public Class getNewSemsDataClass() {
				return null;
			}
		},
		DEV {
			@Override
			public Object getNewSemsData(String smsBody) {
				return null;
			}

			@Override
			public Class getNewSemsDataClass() {
				return null;
			}
		};

		/**
		 * 문자를 받았을때, 타입에 맞는 데이터를 생성한다.
		 * */
		public abstract Object getNewSemsData(String smsBody);

		/**
		 * 생성되는 객체의 클래스를 반환한다.
		 * */
		public abstract Class getNewSemsDataClass();

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

		/**
		 * 기계로 받은 문자에서 첫번째 라인을 추출하여,
		 * 그 문자가 어떤 타입인지를 알아낸다.
		 * */
		public static final CommandType findCommandType(String firstLine) {
			CommandType commandType = null;
			if ( firstLine.startsWith("장비") || firstLine.startsWith("명령어") ) {
				commandType = EMPTY;
			}
			else if ( firstLine.startsWith("1:") ) {
				commandType = NUM;
			}
			else if ( firstLine.startsWith("TIME") ) {
				commandType = TIME;
			}
			else if ( firstLine.startsWith("S") ) {
				if ( firstLine.endsWith("LIMT") ) {
					commandType = LIMT;
				}
				else if ( firstLine.endsWith("KIND") ) {
					commandType = KIND;
				}
				else if ( firstLine.endsWith("USE") ) {
					commandType = USE;
				}
			}
			return commandType;
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
		ETCETERA(0, "기타", "-"),
		TEMPERATURE(1, "온도", "T"),
		HUMIDITY(2, "습도", "H"),
		MINUS_NUMBER(3, "음수", "W");

		private final int number;
		private final String summary;
		private final String simbol;

		SensorType(int number, String summary, String simbol) {
			this.number = number;
			this.summary = summary;
			this.simbol = simbol;
		}

		public static final SensorType valueOf(int number) {
			switch (number) {
				case 0:
					return ETCETERA;
				case 1:
					return TEMPERATURE;
				case 2:
					return HUMIDITY;
				case 3:
					return MINUS_NUMBER;
			}
			return null;
		}

		public static final SensorType valueOfSimbol(String simbol) {
			if (simbol.equals("-")) {
				return ETCETERA;
			} else if (simbol.equals("T")) {
				return TEMPERATURE;
			} else if (simbol.equals("H")) {
				return HUMIDITY;
			} else if (simbol.equals("W")) {
				return MINUS_NUMBER;
			}
			return null;
		}

		@Override
		public String toString() {
			return String.valueOf(number);
		}

		public String getSummary() {
			return summary;
		}

		public String getSimbol() {
			return simbol;
		}
	}

	public enum Availability {
		AVAILABLE(1, "사용"),
		NOT_AVAILABLE(0, "사용안함");

		private final int number;
		private final String summary;

		public static final Availability valueOf(int number) {
			switch ( number ) {
				case 0 : return NOT_AVAILABLE;
				case 1 : return AVAILABLE;
			}
			return null;
		}

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

	public enum Where {
		INDOOR("내부"),
		OUTDOOR("외부");

		private final String summary;

		Where(String summary) {
			this.summary = summary;
		}

		public static final Where valueOfSummary(String summary) {
			if ( summary.equals(INDOOR.getSummary()) ) {
				return INDOOR;
			}
			else if ( summary.equals(OUTDOOR.getSummary()) ) {
				return OUTDOOR;
			}
			else {
				return null;
			}
		}

		public String getSummary() {
			return summary;
		}
	}
}
