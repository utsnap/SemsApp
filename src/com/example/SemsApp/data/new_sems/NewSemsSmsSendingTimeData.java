package com.example.SemsApp.data.new_sems;

import com.example.SemsApp.utility.NewSemsSmsSender;

import java.util.Calendar;
import java.util.EnumMap;
import java.util.GregorianCalendar;

/**
 * Created by vaio-pc on 2014-04-13.
 */
public class NewSemsSmsSendingTimeData {
	public final EnumMap<NewSemsSmsSender.Order, GregorianCalendar> smsSedingTimeEnumMap;

	public static final NewSemsSmsSendingTimeData getInstance(String smsBody) {
		return new NewSemsSmsSendingTimeData(smsBody);
	}

	private NewSemsSmsSendingTimeData(String smsBody) {
		smsSedingTimeEnumMap = new EnumMap<NewSemsSmsSender.Order, GregorianCalendar>(NewSemsSmsSender.Order.class);

		String[] smsLines = smsBody.split("\n");
		for (int i = 0; i < smsLines.length; i++) {
			String[] strings = smsLines[i].split(":");
			if ( !strings[1].equals("-") ) {
				GregorianCalendar gregorianCalendar = new GregorianCalendar();
				String[] times = strings[1].split(",");
				int hour = Integer.parseInt(times[0]);
				int minute = Integer.parseInt(times[1]);
				gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
				gregorianCalendar.set(Calendar.HOUR_OF_DAY, hour);
				gregorianCalendar.set(Calendar.MINUTE, minute);
				smsSedingTimeEnumMap.put(NewSemsSmsSender.Order.valueOf(i + 1), gregorianCalendar);
			}
			else {
				GregorianCalendar gregorianCalendar = new GregorianCalendar();
				gregorianCalendar.set(Calendar.DAY_OF_MONTH, 2);
				smsSedingTimeEnumMap.put(NewSemsSmsSender.Order.valueOf(i + 1), gregorianCalendar);
			}
		}
	}
}
