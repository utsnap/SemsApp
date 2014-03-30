package com.example.SemsApp.data.lab;

import android.content.Context;
import com.example.SemsApp.application.SemsApplication;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Administrator on 14. 3. 20.
 */
public class DataLab<E> implements Serializable {
	protected ArrayList<E> dataList;
	protected String dataFileName;
	protected Class<E> aClass;

	public DataLab(String dataFileName, Class<E> aClass) {
		this.dataFileName = dataFileName;
		dataList = new ArrayList<E>();
		this.aClass = aClass;
	}

	public DataLab(SemsApplication.MachineType machineType) {
		this.dataFileName = machineType.getDataFileName();
		dataList = new ArrayList<E>();
		this.aClass = machineType.getDataClass();
	}

	/**
	 * 데이터 리스트를 파일에 저장한다.
	 * */
	public void saveToFile(Context context) {
		Gson gson = new Gson();
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(dataFileName, Context.MODE_PRIVATE)));
			for ( E e : dataList ) {
				//Log.i("utsnap", "save - " + e.getClass().getSimpleName() + " : " + aClass.getSimpleName());
				String jsonString = gson.toJson(e);
				bufferedWriter.write(jsonString);
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return;
		}
	}

	/**
	 * 파일로부터 데이터 리스트를 구성한다.
	 * */
	public void loadFromFile(Context context) {
		Gson gson = new Gson();
		dataList.clear();
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.openFileInput(dataFileName)));
			while ( bufferedReader.ready() ) {
				String jsonString = bufferedReader.readLine();
				E e = (E) gson.fromJson(jsonString, aClass);
				//Log.i("utsnap", "load - " + e.getClass().getSimpleName() + " : " + aClass.getSimpleName());
				dataList.add(e);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addData(E data) {
		dataList.add(data);
	}

	public void addAllData(Collection<? extends E> dataCollection) {
		dataList.addAll(dataCollection);
	}

	public E getData(int index) {
		return dataList.get(index);
	}

	public int getCount() {
		return dataList.size();
	}
}
