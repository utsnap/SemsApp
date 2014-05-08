package com.example.SemsApp.data.new_sems;

import com.example.SemsApp.utility.NewSemsSmsSender;

import java.io.Serializable;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * Created by vaio-pc on 2014-04-13.
 */
public class NewSemsSmsSendingTimeData extends AbsNewSemsData implements Serializable {
	public static final String NONE = "설정안됨";

	public final Map<NewSemsSmsSender.Order, String> smsSendingTimeStringEnumMap;
	public final Map<NewSemsSmsSender.Order, GregorianCalendar> smsSendingTimeDataEnumMap;

	public static final NewSemsSmsSendingTimeData getInstance(String smsBody) {
		return new NewSemsSmsSendingTimeData(smsBody);
	}

	private NewSemsSmsSendingTimeData(String smsBody) {
		smsSendingTimeStringEnumMap = new EnumMap<NewSemsSmsSender.Order, String>(NewSemsSmsSender.Order.class);
		smsSendingTimeDataEnumMap = new EnumMap<NewSemsSmsSender.Order, GregorianCalendar>(NewSemsSmsSender.Order.class);

		String[] smsLines = smsBody.split("\n");
		for (int i = 0; i < smsLines.length; i++) {
			String[] strings = smsLines[i].split(":");
			smsSendingTimeStringEnumMap.put(NewSemsSmsSender.Order.valueOf(i + 1), strings[1]);
			//Log.i("utsnap", NewSemsSmsSender.Order.valueOf(i + 1).getEmergencyContact());
			if ( !strings[1].equals("-") ) {
				GregorianCalendar gregorianCalendar = new GregorianCalendar();
				String[] times = strings[1].split(",");
				int hour = Integer.parseInt(times[0]);
				int minute = Integer.parseInt(times[1]);
				gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
				gregorianCalendar.set(Calendar.HOUR_OF_DAY, hour);
				gregorianCalendar.set(Calendar.MINUTE, minute);
				smsSendingTimeDataEnumMap.put(NewSemsSmsSender.Order.valueOf(i + 1), gregorianCalendar);
			}
			else {
				;
			}
		}
	}

	@Override
	public String getDialogTitle() {
		return "New Sems 문자발송 시간";
	}
}
