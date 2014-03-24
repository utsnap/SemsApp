package com.example.SemsApp.fragment.viewpager;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.view.*;
import android.widget.LinearLayout;
import com.example.SemsApp.R;
import com.example.SemsApp.data.lab.DataLab;
import com.example.SemsApp.fragment.dialog.PasswordConfirmDialogFragment;
import com.example.SemsApp.preference.PreferenceKeys;

/**
 * Created by Administrator on 14. 3. 21.
 * 기계의 상태 정보를 페이저 형식으로 보여주는 프래그먼트.
 * 내부에 ViewPager를 가지고 있다.
 */
public abstract class AbsViewPagerFragment<E> extends Fragment {
	public static final int REQUEST_MACHINE_SETTING = 1;

	protected static String EXTRA_DATA_LAB = "extra_data_lab";
	protected static final String EXTRA_VIEW_PAGER_ID= "extra_view_pager_id";
	protected static final String EXTRA_MENU_RESOURCE_ID = "extra_menu_resource_id";
	protected static final String EXTRA_MACHINE_NAME_RESOURCE_ID = "extra_machine_name_resource_id";

	protected int viewPagerId;
	protected int menuResouceId;
	protected int settingMenuItemId;
	protected int functionMenuItemId;
	protected String machineName;
	protected Menu menu;
	protected DataLab<E> dataLab;

	protected ViewPager viewPager;

	protected LinearLayout viewPagerContainer;

	protected void assignViews(View view) {
		viewPagerContainer = (LinearLayout) view.findViewById(R.id.viewPagerContainer);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		viewPagerId = getViewPagerId();
		menuResouceId = getMenuResourceId();
		settingMenuItemId = getSettingMenuItemId();
		functionMenuItemId = getFunctionMenuItemId();
		machineName = getMachineName();
		dataLab = getDataLab();

		viewPager = new ViewPager(getActivity());
		viewPager.setId(viewPagerId);

		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.viewpager_fragment, null, false);
		assignViews(view);
		viewPagerContainer.addView(viewPager);
		return view;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		viewPagerContainer.removeView(viewPager);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(menuResouceId, menu);
		this.menu = menu;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if ( item.getItemId() == functionMenuItemId ) {
			String password = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(PreferenceKeys.MACHINE_SETTING_PASSWORD, "0000");
			PasswordConfirmDialogFragment.newInstance(password, viewPagerId).show(getFragmentManager(), "");
			return true;
		}
		else if ( item.getItemId() == settingMenuItemId ) {
			settingMenuItemSelected();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if ( requestCode == REQUEST_MACHINE_SETTING ) {
			machineSettingChanged();
		}
	}

	public void updateCurrentPage() {
		viewPager.setCurrentItem(viewPager.getCurrentItem(), false);
	}

	public void showFirstPage() {
		viewPager.setCurrentItem(0, true);
	}

	protected abstract int getViewPagerId();

	protected abstract int getMenuResourceId();

	protected abstract int getSettingMenuItemId();

	protected abstract int getFunctionMenuItemId();

	public abstract String getMachineName();

	public abstract DataLab<E> getDataLab();

	protected abstract void settingMenuItemSelected();

	protected abstract void machineSettingChanged();
}
