package com.example.SemsApp.data;

import android.content.SharedPreferences;
import android.util.Log;
import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by Administrator on 2014-04-10.
 */
public abstract class AbsData implements Comparable, DataManageable, Serializable {
	protected static final Gson GSON = new Gson();
	protected String preferenceKey;
	public final int index;

	protected AbsData(int index) {
		this.index = index;
	}

	@Override
	public int compareTo(Object another) {
		AbsData anotherData = (AbsData)another;
		if ( this.index < anotherData.index ) {
			return -1;
		}
		else if ( this.index == anotherData.index ) {
			return 0;
		}
		else {
			return 1;
		}
	}

	@Override
	public void saveToPreference(SharedPreferences preferences) {
		//Log.i("utsnap", preferences == null ? "null" : preferences.toString());
		preferences.edit().putString(preferenceKey, GSON.toJson(getSerializingObject())).commit();
	}

	@Override
	public void loadFromPreference(SharedPreferences preferences) {
		String dataJson = preferences.getString(preferenceKey, "");
		//Log.i("utsnap", "dataJson : "  + dataJson);
		if ( !dataJson.equals("") ) {
			Object data = GSON.fromJson(dataJson, getSerializingDataClass());
			this.copyFrom(data);
			//Log.i("utsnap", data.getClass().getSimpleName());
		}
		else {
			//Log.i("utsnap", "데이터가 비어있음");
			Object data = loadDefaultInfoData(preferences);
			if ( data != null ) {
				this.copyFrom(data);
			}
		}
	}

	public String getPreferenceKey() {
		return preferenceKey;
	}

	protected abstract Object getSerializingObject();

	protected abstract Class<?> getSerializingDataClass();

	protected abstract void copyFrom(Object infoData);

	protected abstract Object loadDefaultInfoData(SharedPreferences preferences);
}
