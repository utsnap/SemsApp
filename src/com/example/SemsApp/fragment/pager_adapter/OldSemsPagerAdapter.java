package com.example.SemsApp.fragment.pager_adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import com.example.SemsApp.data.OldSemsData;
import com.example.SemsApp.data.lab.DataLab;
import com.example.SemsApp.fragment.machine_state.OldSemsStateFragment;

/**
 * Created by Administrator on 14. 3. 20.
 */
public class OldSemsPagerAdapter extends AbsDataPagerAdapter<OldSemsData> {

	public OldSemsPagerAdapter(FragmentManager fm, DataLab<OldSemsData> dataLab) {
		super(fm, dataLab);
	}

	@Override
	public Fragment getItem(int i) {
		return OldSemsStateFragment.newInstance(dataLab.getData(i));
	}
}
