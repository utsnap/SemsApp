package com.example.SemsApp.fragment.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import com.example.SemsApp.R;
import com.example.SemsApp.activity.MachineSettingActivity;
import com.example.SemsApp.application.SemsApplication;
import com.example.SemsApp.data.NewSemsData;
import com.example.SemsApp.data.lab.DataLab;
import com.example.SemsApp.fragment.pager_adapter.NewSemsPagerAdapter;

import static com.example.SemsApp.application.SemsApplication.MachineType;

/**
 * Created by Administrator on 14. 3. 21.
 */
public class NewSemsPagerFragment extends ViewPagerFragment<NewSemsData> {
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
		viewPager.setAdapter(new NewSemsPagerAdapter(getFragmentManager(), dataLab));
		return view;
	}

	@Override
	protected int getViewPagerId() {
		return R.id.newSemsViewPager;
	}

	@Override
	protected int getMenuResourceId() {
		return R.menu.new_sems_menu;
	}

	@Override
	protected int getSettingMenuItemId() {
		return R.id.newSemsSettingMenuItem;
	}

	@Override
	protected int getFunctionMenuItemId() {
		return R.id.newSemsFunctionMenuItem;
	}

	@Override
	public String getMachineName() {
		return new String(getResources().getString(R.string.new_sems));
	}

	@Override
	public DataLab<NewSemsData> getDataLab() {
		return ((SemsApplication)getActivity().getApplication()).dataLabEnumMap.get(MachineType.NEW_SEMS);
	}

	@Override
	protected void settingMenuItemSelected() {
		Intent intent = new Intent(getActivity(), MachineSettingActivity.class);
		intent.putExtra(MachineSettingActivity.EXTRA_MACHINE_TYPE, MachineType.NEW_SEMS.ordinal());
		startActivityForResult(intent, AbsViewPagerFragment.REQUEST_MACHINE_SETTING);
	}

	@Override
	protected void machineSettingChanged() {
		Log.i("utsnap", "New Sems setting is changed");
	}
}