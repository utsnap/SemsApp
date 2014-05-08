package com.example.SemsApp.fragment.pager_adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import com.example.SemsApp.data.NewSemsData;
import com.example.SemsApp.data.lab.DataLab;
import com.example.SemsApp.fragment.machine_state.NewSemsStateFragment;

/**
 * Created by Administrator on 14. 3. 20.
 */
public class NewSemsPagerAdapter extends AbsDataPagerAdapter<NewSemsData> {

	public NewSemsPagerAdapter(FragmentManager fm, DataLab<NewSemsData> dataLab) {
		super(fm, dataLab);
	}

	@Override
	public Fragment getItem(int i) {
		return NewSemsStateFragment.newInstance(dataLab.get(i));
	}
}