package com.example.SemsApp.data.new_sems;


import com.example.SemsApp.utility.NewSemsSmsSender;

import java.io.Serializable;
import java.util.EnumMap;

/**
 * Created by vaio-pc on 2014-04-13.
 */
public class NewSemsInfoData extends AbsNewSemsData implements Serializable {
	public final NewSemsSmsSender.MachineNumber machineNumber;
	public final int temparature;
	public NewSemsSmsSender.Where where;
	public final EnumMap<NewSemsSmsSender.SensorNumber, NewSemsSmsSender.SensorType> sensorTypeEnumMap;
	public final EnumMap<NewSemsSmsSender.SensorNumber, Integer> integerValueEnumMap;
	public final EnumMap<NewSemsSmsSender.SensorNumber, String> stringValueEnumMap;

	private NewSemsInfoData(String smsBody) {
		sensorTypeEnumMap = new EnumMap<NewSemsSmsSender.SensorNumber, NewSemsSmsSender.SensorType>(NewSemsSmsSender.SensorNumber.class);
		integerValueEnumMap = new EnumMap<NewSemsSmsSender.SensorNumber, Integer>(NewSemsSmsSender.SensorNumber.class);
		stringValueEnumMap = new EnumMap<NewSemsSmsSender.SensorNumber, String>(NewSemsSmsSender.SensorNumber.class);

		String[] smsLines = smsBody.split("\n");
		//필수 : 헤더를 분석하여 기계 번호를 얻는다.
		int machineNum = Integer.parseInt(smsLines[0].substring(1, 2));
		this.machineNumber = NewSemsSmsSender.MachineNumber.valueOf(machineNum);

		for (NewSemsSmsSender.Where w : NewSemsSmsSender.Where.values()) {
			if ( smsLines[0].contains(w.getSummary()) ) {
				this.where = w;
			}
		}

		temparature = Integer.parseInt(smsLines[0].split(":")[1].substring(2, 4));
		//완료

		//필수 : 나머지를 분석하여 센서의 종류를 얻는다.
		for (int i = 1; i < smsLines.length; i++) {
			String[] strings = smsLines[i].split(":");
			int sensorTypeNumber = Integer.parseInt(strings[0].substring(0, 1));
			String sensorTypeSimbol = strings[0].substring(1, 2);
			String stringValue = strings[1];

			sensorTypeEnumMap.put(NewSemsSmsSender.SensorNumber.valueOf(i), NewSemsSmsSender.SensorType.valueOfSimbol(sensorTypeSimbol));
			stringValueEnumMap.put(NewSemsSmsSender.SensorNumber.valueOf(i), stringValue);
			if ( !stringValue.equals("-") ) {
				integerValueEnumMap.put(NewSemsSmsSender.SensorNumber.valueOf(i), Integer.parseInt(stringValue));
			}
		}
		//완료
	}

	public static final NewSemsInfoData getInstance(String smsBody) {
		return new NewSemsInfoData(smsBody);
	}

	@Override
	public String getDialogTitle() {
		return "";
	}
}
