package com.example.SemsApp.data.lab;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.example.SemsApp.application.SemsApplication;
import com.example.SemsApp.data.AbsData;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Administrator on 14. 3. 20.
 */
public class DataLab<E> extends ArrayList<E> {
	protected static final Gson GSON = new Gson();
	//protected ArrayList<E> dataList;
	protected SemsApplication.MachineType machineType;

	public DataLab(SemsApplication.MachineType machineType) {
		super();
		this.machineType = machineType;
		machineType.initialArrayList((DataLab<AbsData>) this);
	}

	/**
	 * 데이터 리스트를 파일에 저장한다.
	 * */
	public void saveToFile(Context context) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		for ( E e : this ) {
			AbsData absData = (AbsData) e;
			absData.saveToPreference(preferences);
		}
	}

	/**
	 * 파일로부터 데이터 리스트를 구성한다.
	 * */
	public void loadFromFile(Context context) {
		Gson gson = new Gson();
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		for ( E e : this ) {
			AbsData absData = (AbsData) e;
			absData.loadFromPreference(preferences);
		}
	}
}
