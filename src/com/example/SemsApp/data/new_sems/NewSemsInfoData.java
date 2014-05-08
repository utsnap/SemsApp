package com.example.SemsApp.data.new_sems;

import com.example.SemsApp.utility.NewSemsSmsSender;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by vaio-pc on 2014-04-13.
 */
public class NewSemsInfoData extends AbsNewSemsData implements Serializable {
	public final NewSemsSmsSender.MachineNumber machineNumber;
	public final int temparature;
	public final NewSemsSmsSender.Where where;
	public final Map<NewSemsSmsSender.SensorNumber, NewSemsSmsSender.SensorType> sensorTypeEnumMap;
	public final Map<NewSemsSmsSender.SensorNumber, Integer> integerValueEnumMap;
	public final Map<NewSemsSmsSender.SensorNumber, String> stringValueEnumMap;

	public static final NewSemsInfoData getInstance(String smsBody) {
		return new NewSemsInfoData(smsBody);
	}

	public static final NewSemsInfoData getDefaultInstance(int dataLabIndex) {
		Map<NewSemsSmsSender.SensorNumber, NewSemsSmsSender.SensorType> sensorTypeEnumMap = new EnumMap<NewSemsSmsSender.SensorNumber, NewSemsSmsSender.SensorType>(NewSemsSmsSender.SensorNumber.class);
		for (NewSemsSmsSender.SensorNumber sensorNumber : NewSemsSmsSender.SensorNumber.values()) {
			sensorTypeEnumMap.put(sensorNumber, NewSemsSmsSender.SensorType.ETCETERA);
		}

		Map<NewSemsSmsSender.SensorNumber, String> stringValueEnumMap = new EnumMap<NewSemsSmsSender.SensorNumber, String>(NewSemsSmsSender.SensorNumber.class);
		for (NewSemsSmsSender.SensorNumber sensorNumber : NewSemsSmsSender.SensorNumber.values()) {
			stringValueEnumMap.put(sensorNumber, "-");
		}

		Map<NewSemsSmsSender.SensorNumber, Integer> integerValueEnumMap = new EnumMap<NewSemsSmsSender.SensorNumber, Integer>(NewSemsSmsSender.SensorNumber.class);

		return new NewSemsInfoData(NewSemsSmsSender.MachineNumber.valueOf(dataLabIndex + 1),
				0, NewSemsSmsSender.Where.OUTDOOR,
				sensorTypeEnumMap,
				integerValueEnumMap,
				stringValueEnumMap);
	}

	private NewSemsInfoData(
			NewSemsSmsSender.MachineNumber machineNumber,
			int temparature,
			NewSemsSmsSender.Where where,
			Map<NewSemsSmsSender.SensorNumber, NewSemsSmsSender.SensorType> sensorTypeEnumMap,
			Map<NewSemsSmsSender.SensorNumber, Integer> integerValueEnumMap,
			Map<NewSemsSmsSender.SensorNumber, String> stringValueEnumMap) {
		this.machineNumber = machineNumber;
		this.temparature = temparature;
		this.where = where;
		this.sensorTypeEnumMap = sensorTypeEnumMap;
		this.integerValueEnumMap = integerValueEnumMap;
		this.stringValueEnumMap = stringValueEnumMap;
	}

	private NewSemsInfoData() {
		machineNumber = null;
		temparature = 0;
		sensorTypeEnumMap = null;
		integerValueEnumMap = null;
		stringValueEnumMap = null;
		where = null;
	};

	private NewSemsInfoData(String smsBody) {
		sensorTypeEnumMap = new EnumMap<NewSemsSmsSender.SensorNumber, NewSemsSmsSender.SensorType>(NewSemsSmsSender.SensorNumber.class);
		integerValueEnumMap = new EnumMap<NewSemsSmsSender.SensorNumber, Integer>(NewSemsSmsSender.SensorNumber.class);
		stringValueEnumMap = new EnumMap<NewSemsSmsSender.SensorNumber, String>(NewSemsSmsSender.SensorNumber.class);

		String[] smsLines = smsBody.split("\n");
		//필수 : 헤더를 분석하여 기계 번호를 얻는다.
		int machineNum = Integer.parseInt(smsLines[0].substring(1, 2));
		this.machineNumber = NewSemsSmsSender.MachineNumber.valueOf(machineNum);

		String whereString = smsLines[0].split(":")[1].substring(0, 2);
		//Log.i("utsnap", whereString);
		this.where = NewSemsSmsSender.Where.valueOfSummary(whereString);
		/*for (NewSemsSmsSender.Where w : NewSemsSmsSender.Where.values()) {
			if ( smsLines[0].contains(w.getSummary()) ) {
				this.where = w;
			}
		}*/

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
			//Log.i("utsnap", stringValue);
			if ( !stringValue.equals("-") && !stringValue.equals("OFF") ) {
				integerValueEnumMap.put(NewSemsSmsSender.SensorNumber.valueOf(i), Integer.parseInt(stringValue));
			}
		}
		//완료
	}

	@Override
	public String getDialogTitle() {
		return machineNumber.getDetailName().concat(" 상태 정보");
	}
}
