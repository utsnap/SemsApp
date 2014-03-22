package com.example.SemsApp.fragment.pager_adapter;

import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import com.example.SemsApp.data.lab.DataLab;

/**
 * Created by Administrator on 14. 3. 21.
 */
public abstract class AbsDataPagerAdapter<E> extends FragmentStatePagerAdapter {
	protected DataLab<E> dataLab;

	public AbsDataPagerAdapter(FragmentManager fm, DataLab<E> dataLab) {
		super(fm);
		this.dataLab = dataLab;
	}

	@Override
	public int getCount() {
		return dataLab.getCount();
	}
}
