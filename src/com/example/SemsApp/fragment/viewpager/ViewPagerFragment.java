package com.example.SemsApp.fragment.viewpager;

import android.os.Bundle;
import android.view.*;
import com.example.SemsApp.data.lab.DataLab;

/**
 * Created by Administrator on 14. 3. 20.
 * 뷰페이저(ViewPager)를 담고 있는 프래그먼트.
 * 실제로 보여질 기계의 상태정보를 보여주는 프래그먼트를 페이징한다.
 */
public class ViewPagerFragment<E> extends AbsViewPagerFragment<E> {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	protected int getViewPagerId() {
		return 0;
	}

	@Override
	protected int getMenuResourceId() {
		return 0;
	}

	@Override
	protected int getSettingMenuItemId() {
		return 0;
	}

	@Override
	protected int getFunctionMenuItemId() {
		return 0;
	}

	@Override
	public String getMachineName() {
		return null;
	}

	@Override
	public DataLab<E> getDataLab() {
		return null;
	}

	@Override
	protected void settingMenuItemSelected() {
		;
	}

	@Override
	protected void machineSettingChanged() {
		;
	}
}