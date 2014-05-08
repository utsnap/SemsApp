package com.example.SemsApp.data.new_sems;

import com.example.SemsApp.utility.NewSemsSmsSender;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by vaio-pc on 2014-04-13.
 */
public class NewSemsLimitData extends AbsNewSemsData implements Serializable {
	public final NewSemsSmsSender.MachineNumber machineNumber;
	public final Map<NewSemsSmsSender.SensorNumber, EnumMap<LimitType, Integer>> limitDataEnumMap;

	public static final NewSemsLimitData getInstance(String smsBody) {
		return new NewSemsLimitData(smsBody);
	}

	private NewSemsLimitData(String smsBody) {
		limitDataEnumMap = new EnumMap<NewSemsSmsSender.SensorNumber, EnumMap<LimitType, Integer>>(NewSemsSmsSender.SensorNumber.class);
		String[] smsLines = smsBody.split("\n");
		//필수 : 헤더를 분석하여 기계 번호를 얻는다.
		int machineNum = Integer.parseInt(smsLines[0].substring(1, 2));
		this.machineNumber = NewSemsSmsSender.MachineNumber.valueOf(machineNum);
		//완료

		//필수 : 나머지를 분석하여 센서의 제한범위를 얻는다.
		for (int i = 1; i < smsLines.length; i++) {
			String[] strings = smsLines[i].split(":");
			if (!strings[1].equals("-")) {
				String[] values = strings[1].split(",");
				int upperLimit = Integer.parseInt(values[0]);
				int lowerLimit = Integer.parseInt(values[1]);
				EnumMap<LimitType, Integer> limitEnumMap = new EnumMap<LimitType, Integer>(LimitType.class);
				limitEnumMap.put(LimitType.UPPER_LIMIT, new Integer(upperLimit));
				limitEnumMap.put(LimitType.LOWER_LIMIT, new Integer(lowerLimit));
				limitDataEnumMap.put(NewSemsSmsSender.SensorNumber.valueOf(i), limitEnumMap);
			} else {
				EnumMap<LimitType, Integer> limitEnumMap = new EnumMap<LimitType, Integer>(LimitType.class);
				limitEnumMap.put(LimitType.UPPER_LIMIT, Integer.MAX_VALUE);
				limitEnumMap.put(LimitType.LOWER_LIMIT, Integer.MIN_VALUE);
				limitDataEnumMap.put(NewSemsSmsSender.SensorNumber.valueOf(i), limitEnumMap);
			}
		}
		//완료
	}

	@Override
	public String getDialogTitle() {
		return "New Sems " + machineNumber.getDetailName() + " 제한값";
	}

	public enum LimitType {
		UPPER_LIMIT("상한선"),
		LOWER_LIMIT("하한선");

		private String summary;

		public static final LimitType valueOf(int number) {
			switch (number) {
				case 0:
					return UPPER_LIMIT;
				case 1:
					return LOWER_LIMIT;
			}
			return null;
		}

		LimitType(String summary) {
			this.summary = summary;
		}

		public String getSummary() {
			return summary;
		}
	}
}
