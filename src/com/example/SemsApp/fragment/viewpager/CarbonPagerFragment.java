package com.example.SemsApp.fragment.viewpager;

import android.os.Bundle;
import android.view.*;
import com.example.SemsApp.R;
import com.example.SemsApp.application.SemsApplicationn;
import com.example.SemsApp.data.CarbonData;
import com.example.SemsApp.data.lab.DataLab;
import com.example.SemsApp.fragment.pager_adapter.CarbonPagerAdapter;

/**
 * Created by Administrator on 14. 3. 21.
 */
public class CarbonPagerFragment extends ViewPagerFragment<CarbonData> {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);
		viewPager.setAdapter(new CarbonPagerAdapter(getFragmentManager(), dataLab));
		return view;
	}

	@Override
	protected int getViewPagerId() {
		return R.id.carbonViewPager;
	}

	@Override
	protected int getMenuResourceId() {
		return R.menu.carbon_menu;
	}

	@Override
	protected int getSettingMenuItemId() {
		return R.id.carbonSettingMenuItem;
	}

	@Override
	protected int getFunctionMenuItemId() {
		return R.id.carbonFunctionMenuItem;
	}

	@Override
	public String getMachineName() {
		return new String(getResources().getString(R.string.carbon_machine));
	}

	@Override
	public DataLab getDataLab() {
		return ((SemsApplicationn)getActivity().getApplication()).dataLabs.get(SemsApplicationn.CARBON_MACHINE);
	}
}