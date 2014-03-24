package com.example.SemsApp.fragment.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import com.example.SemsApp.R;
import com.example.SemsApp.activity.MachineSettingActivity;
import com.example.SemsApp.application.SemsApplication;
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
		return ((SemsApplication)getActivity().getApplication()).dataLabs.get(SemsApplication.CARBON_MACHINE);
	}

	@Override
	protected void settingMenuItemSelected() {
		Intent intent = new Intent(getActivity(), MachineSettingActivity.class);
		intent.putExtra(MachineSettingActivity.EXTRA_MACHINE_TYPE, SemsApplication.CARBON_MACHINE);
		startActivityForResult(intent, AbsViewPagerFragment.REQUEST_MACHINE_SETTING);
	}

	@Override
	protected void machineSettingChanged() {
		Log.i("utsnap", "Carbon Machine setting is changed");
	}
}