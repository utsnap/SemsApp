package com.example.SemsApp.data.new_sems;


import com.example.SemsApp.utility.NewSemsSmsSender;

import java.util.EnumMap;

/**
 * Created by vaio-pc on 2014-04-13.
 */
public class NewSemsUseData {
	public final NewSemsSmsSender.MachineNumber machineNumber;
	public EnumMap<NewSemsSmsSender.SensorNumber, NewSemsSmsSender.Availability> sensorAvailabilityEnumMap;

	public static final NewSemsUseData getInstance(String smsBody) {
		return new NewSemsUseData(smsBody);
	}

	private NewSemsUseData(String smsBody) {
		sensorAvailabilityEnumMap = new EnumMap<NewSemsSmsSender.SensorNumber, NewSemsSmsSender.Availability>(NewSemsSmsSender.SensorNumber.class);
		String[] smsLines = smsBody.split("\n");
		//필수 : 헤더를 분석하여 기계 번호를 얻는다.
		int machineNum = Integer.parseInt(smsLines[0].substring(1, 2));
		this.machineNumber = NewSemsSmsSender.MachineNumber.valueOf(machineNum);
		//완료

		//필수 : 나머지를 분석하여 센서 사용여부를 얻는다.
		for (int i = 1; i < smsLines.length; i++) {
			String[] strings = smsLines[i].split(":");
			int sensorAvailabilityNumber = Integer.parseInt(strings[1]);
			sensorAvailabilityEnumMap.put(NewSemsSmsSender.SensorNumber.valueOf(i), NewSemsSmsSender.Availability.valueOf(sensorAvailabilityNumber));
		}
		//완료
	}
}
