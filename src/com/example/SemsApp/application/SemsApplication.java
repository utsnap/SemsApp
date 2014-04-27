package com.example.SemsApp.application;

import android.app.Activity;
import android.app.Application;
import com.example.SemsApp.data.*;
import com.example.SemsApp.data.lab.DataLab;
import com.example.SemsApp.fragment.viewpager.*;
import com.example.SemsApp.preference.PreferenceKeys;

import java.util.EnumMap;
import java.util.Stack;

/**
 * Created by Administrator on 14. 3. 20.
 * 앱에서 사용되는 공유되는 데이터를 선언한다.
 */
public class SemsApplication extends Application {
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
		OLD_SEMS(0) {
			@Override
			public Class getDataClass() {
				return OldSemsData.class;
			}

			@Override
			public String getMachineName() {
				return oldSemsName;
			}

			@Override
			public void initialArrayList(DataLab<AbsData> arrayList) {
				arrayList.add(OldSemsData.getInstance(PreferenceKeys.FIRST_OLD_SEMS_DATA, 0, ""));
				arrayList.add(OldSemsData.getInstance(PreferenceKeys.SECOND_OLD_SEMS_DATA, 1, ""));
				arrayList.add(OldSemsData.getInstance(PreferenceKeys.THIRD_OLD_SEMS_DATA, 2, ""));
				arrayList.add(OldSemsData.getInstance(PreferenceKeys.FORTH_OLD_SEMS_DATA, 3, ""));
			}

			@Override
			public ViewPagerFragment getViewPagerFragment() {
				return new OldSemsPagerFragment();
			}


		},
		NEW_SEMS(1) {
			@Override
			public Class getDataClass() {
				return NewSemsData.class;
			}

			@Override
			public String getMachineName() {
				return newSemsName;
			}

			@Override
			public void initialArrayList(DataLab<AbsData> arrayList) {

			}

			@Override
			public ViewPagerFragment getViewPagerFragment() {
				return new NewSemsPagerFragment();
			}
		},
		LED_DIMMER(2) {
			@Override
			public Class getDataClass() {
				return LedData.class;
			}

			@Override
			public String getMachineName() {
				return LedDimmerName;
			}

			@Override
			public void initialArrayList(DataLab<AbsData> arrayList) {

			}

			@Override
			public ViewPagerFragment getViewPagerFragment() {
				return new LedPagerFragment();
			}


		},
		CARBON_HEATER(3) {
			@Override
			public Class getDataClass() {
				return CarbonData.class;
			}

			@Override
			public String getMachineName() {
				return CarbonHeaterName;
			}

			@Override
			public void initialArrayList(DataLab<AbsData> arrayList) {

			}

			@Override
			public ViewPagerFragment getViewPagerFragment() {
				return new CarbonPagerFragment();
			}


		};

		public final int index;

		MachineType(int index) {
			this.index = index;
		}

		public abstract Class getDataClass();
		public abstract String getMachineName();
		public abstract void initialArrayList(DataLab<AbsData> arrayList);
		public abstract ViewPagerFragment getViewPagerFragment();
	}
}
