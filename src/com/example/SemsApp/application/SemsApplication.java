package com.example.SemsApp.application;

import android.app.Activity;
import android.app.Application;
import com.example.SemsApp.data.*;
import com.example.SemsApp.data.lab.DataLab;
import com.example.SemsApp.preference.PreferenceKeys;

import java.util.EnumMap;
import java.util.Stack;

/**
 * Created by Administrator on 14. 3. 20.
 * 앱에서 사용되는 공유되는 데이터를 선언한다.
 */
public class SemsApplication extends Application {
	private static final String newSemsDataFileName = "new_sems_data_file.txt";
	private static final String oldSemsDataFileName = "old_sems_data_file.txt";
	private static final String LedDataFileName = "led_sems_data_file.txt";
	private static final String carbonDataFileName = "carbon_data_file.txt";

	private static final String oldSemsName = "Old\nSems";
	private static final String newSemsName = "New\nSems";
	private static final String LedDimmerName = "Led\nDimmer";
	private static final String CarbonHeaterName = "Carbon\nHeater";

	public EnumMap<MachineType, DataLab> dataLabEnumMap;

	public Stack<Activity> activityStack;

	@Override
	public void onCreate() {
		super.onCreate();

		dataLabEnumMap = new EnumMap<MachineType, DataLab>(MachineType.class);
		dataLabEnumMap.put(MachineType.OLD_SEMS, new DataLab<OldSemsData>(MachineType.OLD_SEMS));
		dataLabEnumMap.put(MachineType.NEW_SEMS, new DataLab<NewSemsData>(MachineType.NEW_SEMS));
		dataLabEnumMap.put(MachineType.LED_DIMMER, new DataLab<LedData>(MachineType.LED_DIMMER));
		dataLabEnumMap.put(MachineType.CARBON_HEATER, new DataLab<CarbonData>(MachineType.CARBON_HEATER));

		activityStack = new Stack<Activity>();
	}

	public enum MachineType {
		OLD_SEMS {
			@Override
			public String getDataFileName() {
				return oldSemsDataFileName;
			}

			@Override
			public Class getDataClass() {
				return OldSemsData.class;
			}

			@Override
			public String getMachineName() {
				return oldSemsName;
			}

			@Override
			public void initialArrayList(DataLab<BaseData> arrayList) {
				arrayList.add(new OldSemsData(PreferenceKeys.FIRST_OLD_SEMS_DATA, 0, ""));
				arrayList.add(new OldSemsData(PreferenceKeys.SECOND_OLD_SEMS_DATA, 1, ""));
				arrayList.add(new OldSemsData(PreferenceKeys.THIRD_OLD_SEMS_DATA, 2, ""));
				arrayList.add(new OldSemsData(PreferenceKeys.FORTH_OLD_SEMS_DATA, 3, ""));
			}


		},
		NEW_SEMS {
			@Override
			public String getDataFileName() {
				return newSemsDataFileName;
			}

			@Override
			public Class getDataClass() {
				return NewSemsData.class;
			}

			@Override
			public String getMachineName() {
				return newSemsName;
			}

			@Override
			public void initialArrayList(DataLab<BaseData> arrayList) {

			}
		},
		LED_DIMMER {
			@Override
			public String getDataFileName() {
				return LedDataFileName;
			}

			@Override
			public Class getDataClass() {
				return LedData.class;
			}

			@Override
			public String getMachineName() {
				return LedDimmerName;
			}

			@Override
			public void initialArrayList(DataLab<BaseData> arrayList) {

			}


		},
		CARBON_HEATER {
			@Override
			public String getDataFileName() {
				return carbonDataFileName;
			}

			@Override
			public Class getDataClass() {
				return CarbonData.class;
			}

			@Override
			public String getMachineName() {
				return CarbonHeaterName;
			}

			@Override
			public void initialArrayList(DataLab<BaseData> arrayList) {

			}


		};

		public abstract String getDataFileName();
		public abstract Class getDataClass();
		public abstract String getMachineName();
		public abstract void initialArrayList(DataLab<BaseData> arrayList);
	}
}
