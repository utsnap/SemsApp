package com.example.SemsApp.data.new_sems;

import java.io.Serializable;

/**
 * Created by vaio-pc on 2014-04-13.
 * 아직 미완성이다..
 */
public class NewSemsBaseData extends AbsNewSemsData implements Serializable {
	public final String machine;
	public final String sensor;
	public final String smsSendingTime1;
	public final String smsSendingTime2;
	public final String commands;

	public static final NewSemsBaseData getInstance(String smsBody) {
		return new NewSemsBaseData(smsBody);
	}

	private NewSemsBaseData(String smsBody) {
		String[] smsLines = smsBody.split("\n");
		//SET 인 경우
		if (smsLines.length == 1) {
			machine = "";
			sensor = "";
			smsSendingTime1 = "";
			smsSendingTime2 = "";
			commands = smsLines[0];
		}
		//GET 인 경우
		else {
			machine = smsLines[0];
			sensor = smsLines[1];
			smsSendingTime1 = smsLines[2];
			smsSendingTime2 = smsLines[3];
			commands = smsLines[4];
		}
	}

	@Override
	public String getDialogTitle() {
		return "New Sems 기본정보";
	}
}
