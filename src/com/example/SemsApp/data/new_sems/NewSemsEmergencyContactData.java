package com.example.SemsApp.data.new_sems;



import com.example.SemsApp.utility.NewSemsSmsSender;

import java.util.EnumMap;

/**
 * Created by vaio-pc on 2014-04-13.
 */
public class NewSemsEmergencyContactData {
	public static final String NONE = "설정안됨";
	public final EnumMap<NewSemsSmsSender.Order, String> emergencyContactEnumMap;

	public static final NewSemsEmergencyContactData getInstance(String smsBody) {
		return new NewSemsEmergencyContactData(smsBody);
	}

	private NewSemsEmergencyContactData(String smsBody) {
		emergencyContactEnumMap = new EnumMap<NewSemsSmsSender.Order, String>(NewSemsSmsSender.Order.class);
		String[] smsLines = smsBody.split("\n");
		for (int i = 0; i < smsLines.length; i++) {
			String[] strings = smsLines[i].split(":");
			if (strings[1].equals("-")) {
				emergencyContactEnumMap.put(NewSemsSmsSender.Order.valueOf(i + 1), NONE);
			} else {
				emergencyContactEnumMap.put(NewSemsSmsSender.Order.valueOf(i + 1), strings[1]);
			}
		}
	}
}
