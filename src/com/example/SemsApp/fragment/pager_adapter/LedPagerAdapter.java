package com.example.SemsApp.fragment.pager_adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import com.example.SemsApp.data.LedData;
import com.example.SemsApp.data.lab.DataLab;

/**
 * Created by Administrator on 14. 3. 20.
 */
public class LedPagerAdapter extends AbsDataPagerAdapter<LedData> {

	public LedPagerAdapter(FragmentManager fm, DataLab<LedData> dataLab) {
		super(fm, dataLab);
	}

	@Override
	public Fragment getItem(int i) {
		return null;
	}
}
