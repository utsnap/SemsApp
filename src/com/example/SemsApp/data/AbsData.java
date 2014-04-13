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
	public int order;

	@Override
	public int compareTo(Object another) {
		AbsData anotherData = (AbsData)another;
		if ( this.order < anotherData.order ) {
			return -1;
		}
		else if ( this.order == anotherData.order ) {
			return 0;
		}
		else {
			return 1;
		}
	}

	@Override
	public void saveToPreference(SharedPreferences preferences) {
		preferences.edit().putString(preferenceKey, GSON.toJson(getObject())).commit();
	}

	protected abstract Object getObject();

	@Override
	public void loadFromPreference(SharedPreferences preferences) {
		String dataJson = preferences.getString(preferenceKey, "");
		Log.i("utsnap", "dataJson : "  + dataJson);
		if ( !dataJson.equals("") ) {
			Object data = GSON.fromJson(dataJson, getDataClass());
			this.copyFrom(data);
			Log.i("utsnap", data.getClass().getSimpleName());
		}
	}

	protected abstract Class<?> getDataClass();

	protected abstract void copyFrom(Object data);
}
