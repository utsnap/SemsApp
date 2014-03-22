package com.example.SemsApp.application;

import android.app.Application;
import com.example.SemsApp.data.CarbonData;
import com.example.SemsApp.data.LedData;
import com.example.SemsApp.data.NewSemsData;
import com.example.SemsApp.data.OldSemsData;
import com.example.SemsApp.data.lab.*;

import java.util.ArrayList;

/**
 * Created by Administrator on 14. 3. 20.
 * 앱에서 사용되는 공유되는 데이터를 선언한다.
 */
public class SemsApplicationn extends Application {
	public static final String newSemsDataFileName = "new_sems_data_file.txt";
	public static final String oldSemsDataFileName = "old_sems_data_file.txt";
	public static final String LedDataFileName = "led_sems_data_file.txt";
	public static final String carbonDataFileName = "carbon_data_file.txt";

	public static final int OLD_SEMS = 0;
	public static final int NEW_SEMS = 1;
	public static final int LED_DIMMER = 2;
	public static final int CARBON_MACHINE = 3;

	public ArrayList<DataLab> dataLabs;

	@Override
	public void onCreate() {
		super.onCreate();
		dataLabs = new ArrayList<DataLab>();
		dataLabs.add(new DataLab<OldSemsData>(oldSemsDataFileName, OldSemsData.class));
		dataLabs.add(new DataLab<NewSemsData>(newSemsDataFileName, NewSemsData.class));
		dataLabs.add(new DataLab<LedData>(LedDataFileName, LedData.class));
		dataLabs.add(new DataLab<CarbonData>(carbonDataFileName, CarbonData.class));
	}
}
