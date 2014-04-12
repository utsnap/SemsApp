package com.example.SemsApp.data;

import android.content.SharedPreferences;

/**
 * Created by Administrator on 2014-04-10.
 */
public interface DataManageable {
	void saveToPreference(SharedPreferences preferences);
	void loadFromPreference(SharedPreferences preferences);
}
