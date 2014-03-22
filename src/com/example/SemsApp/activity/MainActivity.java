package com.example.SemsApp.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import com.example.SemsApp.R;
import com.example.SemsApp.activity.tab.TabHandler;
import com.example.SemsApp.application.SemsApplicationn;
import com.example.SemsApp.data.lab.DataLab;
import com.example.SemsApp.fragment.viewpager.*;
import com.example.SemsApp.preference.PreferenceKeys;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by Administrator on 14. 3. 20.
 * 셈스앱의 진입액티비티이다.
 */
public class MainActivity extends Activity {
	public static final int REQUEST_APP_SETTING = 1;

	private static final Integer[] machineNames = {
		R.string.old_sems, R.string.new_sems, R.string.led_dimmer, R.string.carbon_machine
	};
	private static final ArrayList<Integer> machineNameList = new ArrayList<Integer>(Arrays.asList(machineNames));

	private ArrayList<DataLab> dataLabs;
	private ActionBar actionBar;
	private ArrayList<ActionBar.Tab> tabList;
	private ArrayList<ViewPagerFragment> viewPagerFragmentList;


	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_fragment_activity);

		//필수 : 프리퍼런스의 변화시에 수행되는 기능을 설정한다.
		PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(
			new PreferenceChangeHandler(this)
		);

		//필수 : SemsApplication에서 공통으로 사용되는 데이터 객체를 받아온다.
		dataLabs = ((SemsApplicationn)getApplication()).dataLabs;
		//완료

		//테스트 : old sems 데이터를 임의로 추가한다.
		/*dataLabs.get(SemsApplicationn.OLD_SEMS).addData(OldSemsData.getInstance("1번째"));
		dataLabs.get(SemsApplicationn.OLD_SEMS).addData(OldSemsData.getInstance("2번째"));
		dataLabs.get(SemsApplicationn.OLD_SEMS).addData(OldSemsData.getInstance("3번째"));
		dataLabs.get(SemsApplicationn.OLD_SEMS).addData(OldSemsData.getInstance("4번째"));
		dataLabs.get(SemsApplicationn.OLD_SEMS).addData(OldSemsData.getInstance("5번째"));
		dataLabs.get(SemsApplicationn.OLD_SEMS).addData(OldSemsData.getInstance("6번째"));*/

		//필수 : 파일로 기계의 상태정보를 읽어서 메모리에 객체로 한다.
		for ( DataLab dataLab : dataLabs ) {
			dataLab.loadFromFile(this);
		}
		//완료

		//필수 : 탭에서 보여질 프래그먼트(ViewPagerFragment)를 생성한다.
		viewPagerFragmentList = new ArrayList<ViewPagerFragment>();
		viewPagerFragmentList.add(new OldSemsPagerFragment());
		viewPagerFragmentList.add(new NewSemsPagerFragment());
		viewPagerFragmentList.add(new LedPagerFragment());
		viewPagerFragmentList.add(new CarbonPagerFragment());

		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
		for ( ViewPagerFragment viewPagerFragment : viewPagerFragmentList ) {
			fragmentTransaction.add(R.id.fragmentContainer, viewPagerFragment).hide(viewPagerFragment);
		}
		fragmentTransaction.commit();
		//완료

		//필수 : 액션바에 탭을 구성해야 한다.
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		tabList = new ArrayList<ActionBar.Tab>();
		int i = 0;
		for ( ViewPagerFragment viewPagerFragment : viewPagerFragmentList ) {
			ActionBar.Tab tab = actionBar.newTab()
				.setText(machineNameList.get(i))
				.setTabListener(new TabHandler(viewPagerFragment));
			tabList.add(tab);
			//actionBar.addTab(tab);
			i++;
		}
		//완료

		//필수 : app_preference.xml의 정보를 읽어서 보여주어야 할 탭을 선택한다.
		updateActionBarTabs();
		//완료

		//선택 : 보여줄 탭이 없으면, 적당한 화면을 보여주도록한다.
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		//필수 : SemsApplication에서 공통으로 사용되는 데이터 객체를 받아온다.
		for ( DataLab dataLab : dataLabs ) {
			dataLab.loadFromFile(this);
		}
		//완료
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		//필수 : 긱 기계의 상태정보를 파일에 저장한다.
		for ( DataLab dataLab : dataLabs ) {
			dataLab.saveToFile(this);
		}
		//완료
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if ( item.getItemId() == R.id.appSettingMenuItem ) {
			//필수 : 앱설정을 하는 액티비티를 호출한다 - startActivityForResult
			Intent intent = new Intent(this, AppSettingActivity.class);
			startActivityForResult(intent, REQUEST_APP_SETTING);
			//완료
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if ( requestCode == REQUEST_APP_SETTING ) {
			//필수 : 기계의 사용여부를 파악하여, 보여주어야할 탭을 갱신산다.
			updateActionBarTabs();
			//완료
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		/*화면이 전환될때 메뉴가 추가되는 것을 막는다.*/
		if ( isChangingConfigurations() ) {
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			for ( ViewPagerFragment viewPagerFragment : viewPagerFragmentList ) {
				ft.remove(viewPagerFragment);
			}
			ft.commit();
		}
		//완료
		super.onSaveInstanceState(outState);
	}

	private void updateActionBarTabs() {
		SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(this);
		boolean[] usedMachines = new boolean[4];
		usedMachines[SemsApplicationn.OLD_SEMS] = sharedPreference.getBoolean(PreferenceKeys.OLD_SEMS_USED, false);
		usedMachines[SemsApplicationn.NEW_SEMS] = sharedPreference.getBoolean(PreferenceKeys.NEW_SEMS_USED, false);
		usedMachines[SemsApplicationn.LED_DIMMER] = sharedPreference.getBoolean(PreferenceKeys.LED_DIMMER_USED, false);
		usedMachines[SemsApplicationn.CARBON_MACHINE] = sharedPreference.getBoolean(PreferenceKeys.CARBON_MACHINE_USED, false);
		actionBar.removeAllTabs();
		for ( int i = 0; i < 4; i++ ) {
			if ( usedMachines[i] ) {
				actionBar.addTab(tabList.get(i));
				//viewPagerFragmentList.get(i).showFirstPage();
			}
		}
	}

	private class PreferenceChangeHandler implements SharedPreferences.OnSharedPreferenceChangeListener {
		private Context context;

		private PreferenceChangeHandler(Context context) {
			this.context = context;
		}

		@Override
		public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
			if ( key.equals(PreferenceKeys.OLD_SEMS_USED) ) {
				if ( !sharedPreferences.getBoolean(key, false) ) {
					PreferenceManager.getDefaultSharedPreferences(context).edit().putString(PreferenceKeys.OLD_SEMS_PHONE_NUMBER, "").commit();
				}
			}
			else if ( key.equals(PreferenceKeys.NEW_SEMS_USED) ) {
				if ( !sharedPreferences.getBoolean(key, false) ) {
					PreferenceManager.getDefaultSharedPreferences(context).edit().putString(PreferenceKeys.NEW_SEMS_PHONE_NUMBER, "").commit();
				}
			}
			else if ( key.equals(PreferenceKeys.LED_DIMMER_USED) ) {
				if ( !sharedPreferences.getBoolean(key, false) ) {
					PreferenceManager.getDefaultSharedPreferences(context).edit().putString(PreferenceKeys.LED_DIMMER_PHONE_NUMBER, "").commit();
				}
			}
			else if ( key.equals(PreferenceKeys.CARBON_MACHINE_USED) ) {
				if ( !sharedPreferences.getBoolean(key, false) ) {
					PreferenceManager.getDefaultSharedPreferences(context).edit().putString(PreferenceKeys.CARBON_MACHINE_PHONE_NUMBER, "").commit();
				}
			}
		}
	}
}
