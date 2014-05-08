package com.example.SemsApp.data.new_sems;

import com.example.SemsApp.utility.NewSemsSmsSender;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by vaio-pc on 2014-04-13.
 */
public class NewSemsEmergencyContactData extends AbsNewSemsData implements Serializable {
	public static final String NONE = "설정안됨";
	public final Map<NewSemsSmsSender.Order, String> emergencyContactEnumMap;

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

	@Override
	public String getDialogTitle() {
		return "New Sems 비상연락처";
	}
}
