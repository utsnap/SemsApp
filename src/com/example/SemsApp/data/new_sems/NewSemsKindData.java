package com.example.SemsApp.data.new_sems;


import com.example.SemsApp.utility.NewSemsSmsSender;

import java.util.EnumMap;

/**
 * Created by vaio-pc on 2014-04-13.
 */
public class NewSemsKindData {
	public final NewSemsSmsSender.MachineNumber machineNumber;
	public EnumMap<NewSemsSmsSender.SensorNumber, NewSemsSmsSender.SensorType> sensorTypeEnumMap;

	public static final NewSemsKindData getInstance(String smsBody) {
		return new NewSemsKindData(smsBody);
	}

	private NewSemsKindData(String smsBody) {
		sensorTypeEnumMap = new EnumMap<NewSemsSmsSender.SensorNumber, NewSemsSmsSender.SensorType>(NewSemsSmsSender.SensorNumber.class);
		String[] smsLines = smsBody.split("\n");
		//필수 : 헤더를 분석하여 기계 번호를 얻는다.
		int machineNum = Integer.parseInt(smsLines[0].substring(1, 2));
		this.machineNumber = NewSemsSmsSender.MachineNumber.valueOf(machineNum);
		//완료

		//필수 : 나머지를 분석하여 센서의 종류를 얻는다.
		for (int i = 1; i < smsLines.length; i++) {
			String[] strings = smsLines[i].split(":");
			int sensorTypeNumber = Integer.parseInt(strings[1]);
			sensorTypeEnumMap.put(NewSemsSmsSender.SensorNumber.valueOf(i), NewSemsSmsSender.SensorType.valueOf(sensorTypeNumber));
		}
		//완료
	}
}
