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
import android.view.WindowManager;
import com.example.SemsApp.R;
import com.example.SemsApp.activity.tab.TabHandler;
import com.example.SemsApp.application.SemsApplication;
import com.example.SemsApp.data.lab.DataLab;
import com.example.SemsApp.fragment.dialog.OldSemsFunctionDialogFragment;
import com.example.SemsApp.fragment.viewpager.*;
import com.example.SemsApp.preference.PreferenceKeys;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Stack;

import static com.example.SemsApp.application.SemsApplication.MachineType;
import static com.example.SemsApp.application.SemsApplication.MachineType.OLD_SEMS;


/**
 * Created by Administrator on 14. 3. 20.
 * 셈스앱의 진입액티비티이다.
 */
public class MainActivity extends Activity implements OldSemsFunctionDialogFragment.Callbacks {
	public static final int REQUEST_APP_SETTING = 1;
	public static final String ACTION_DATA_RECEIVED = "ACTION_DATA_RECEIVED";
	public static final String ACTION_NEW_SEMS_DATA_RECEIVED = "ACTION_NEW_SEMS_DATA_RECEIVED";
	public static final String ACTION_DATA_REQUESTED = "ACTION_DATA_REQUESTED";
	public static final String TYPE_COMMAND_CATEGORY_DATA = "TYPE_COMMAND_CATEGORY_DATA";
	public static final String TYPE_COMMAND_TYPE_DATA = "TYPE_COMMAND_TYPE_DATA";

	public static final String EXTRA_MACHINE_TYPE_JSON = "EXTRA_MACHINE_TYPE_JSON";
	public static final String EXTRA_NEW_SEMS_DATA = "EXTRA_NEW_SEMS_DATA";
	public static final String EXTRA_DATA_JSON = "EXTRA_DATA_JSON";
	public static final String EXTRA_DATA_CLASS = "EXTRA_DATA_CLASS";
	public static final String EXTRA_DATA_LAB_INDEX = "EXTRA_DATA_LAB_INDEX";
	private static final Gson GSON = new Gson();

	private EnumMap<SemsApplication.MachineType, DataLab> dataLabEnumMap;
	private ActionBar actionBar;
	private EnumMap<SemsApplication.MachineType, ActionBar.Tab> tabEnumMap;
	private EnumMap<SemsApplication.MachineType, ViewPagerFragment> viewPagerFragmentEnumMap;

	private Stack<Activity> activityStack;

	private boolean bModified = false;
	private MachineType modifiedMachine = null;
	private int modifiedDataIndex = -1;
	private Object addedData = null;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_fragment_activity);

		//필수 : 화면을 활성화시킨다.
		getWindow().addFlags(
			WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
			| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
			| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
			| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
		);
		//완료

		//필수 : 프리퍼런스의 변화시에 수행되는 기능을 설정한다.
		PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(
			new PreferenceChangeHandler(this)
		);

		//테스트 : old sems 데이터를 임의로 추가한다.
		/*dataLabEnumMap.get(OLD_SEMS).add(OldSemsData.getInstance("1번째"));
		dataLabEnumMap.get(OLD_SEMS).add(OldSemsData.getInstance("2번째"));
		dataLabEnumMap.get(OLD_SEMS).add(OldSemsData.getInstance("3번째"));
		dataLabEnumMap.get(OLD_SEMS).add(OldSemsData.getInstance("4번째"));*/

		//필수 : 액션바에 탭을 구성해야 한다.
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		tabEnumMap = new EnumMap<MachineType, ActionBar.Tab>(MachineType.class);
		viewPagerFragmentEnumMap = new EnumMap<MachineType, ViewPagerFragment>(MachineType.class);
		for ( MachineType machineType : MachineType.values() ) {
			ViewPagerFragment viewPagerFragment = null;
			viewPagerFragment = machineType.getViewPagerFragment();
			viewPagerFragmentEnumMap.put(machineType, viewPagerFragment);
			ActionBar.Tab tab = actionBar.newTab()
				.setText(machineType.getMachineName())
				.setTag(machineType)
				.setTabListener(new TabHandler(viewPagerFragment));
			tabEnumMap.put(machineType, tab);
		}
		//완료

		//필수 : 탭에서 보여질 프래그먼트(ViewPagerFragment)를 생성한다.
		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
		for ( ViewPagerFragment viewPagerFragment : viewPagerFragmentEnumMap.values() ) {
			fragmentTransaction.add(R.id.fragmentContainer, viewPagerFragment).hide(viewPagerFragment);
		}
		fragmentTransaction.commit();
		//완료

		//필수 : app_preference.xml의 정보를 읽어서 보여주어야 할 탭을 선택한다.
		updateActionBarTabs();
		//완료

		//선택 : 보여줄 탭이 없으면, 적당한 화면을 보여주도록한다.

		//필수 : 액티비티 스택을 받아옴.
		activityStack = ((SemsApplication)getApplicationContext()).activityStack;
		activityStack.push(this);
		//완료

		//필수 : SemsApplication에서 공통으로 사용되는 데이터 객체를 받아온다.
		dataLabEnumMap = ((SemsApplication)getApplicationContext()).dataLabEnumMap;
		//완료

		//필수 : 데이터를 받았는지 확인하고 적절한 처리를 한다.
		Intent intent = getIntent();
		if ( intent.getAction().equals(ACTION_DATA_RECEIVED) ) {
			dataReceived(intent);
		}

		//필수 : 파일로 기계의 상태정보를 읽어서 메모리에 객체로 한다.
		for ( MachineType machineType : MachineType.values() ) {
			if ( !machineType.equals(modifiedMachine) ) {
				dataLabEnumMap.get(machineType).loadFromFile(this);
			}
		}
		//완료
	}

	@Override
	protected void onNewIntent(Intent intent) {
		//필수 : 화면을 활성화시킨다.
		getWindow().addFlags(
			WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
				| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
				| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
				| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
		);
		//완료

		//데이터를 받았을 경우에는 적당한 처리를 한다.
		if ( intent.getAction().equals(ACTION_DATA_RECEIVED) ) {
			dataReceived(intent);
		}
		else if ( intent.getAction().equals(ACTION_NEW_SEMS_DATA_RECEIVED) ) {
			// 필수 : new sems의 GET, SET 요청에대한 응답을 한다.
			// 단순한 다이얼로그를 띄워야 한다.
		}
	}

	@Override
	protected void onRestart() {
		super.onRestart();//Log.i("utsnap", "onRestart");
		//필수 : SemsApplication에서 공통으로 사용되는 데이터 객체를 받아온다.
		for ( MachineType machineType : MachineType.values() ) {
			if ( !machineType.equals(modifiedMachine) ) {
				dataLabEnumMap.get(machineType).loadFromFile(this);
			}
		}
		//완료
		//Log.i("utsnap", String.valueOf(activityStack.size()));
	}

	@Override
	protected void onStart() {
		super.onStart();
		if ( bModified ) {
			receivedDataProcessed();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if ( bModified ) {
			receivedDataProcessed();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
		//필수 : 긱 기계의 상태정보를 파일에 저장한다.
		for ( DataLab dataLab : dataLabEnumMap.values() ) {
			dataLab.saveToFile(this);
		}
		//완료
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		activityStack.clear();
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
			for ( ViewPagerFragment viewPagerFragment : viewPagerFragmentEnumMap.values() ) {
				ft.remove(viewPagerFragment);
			}
			ft.commit();
		}
		//완료
		super.onSaveInstanceState(outState);
	}

	private void updateActionBarTabs() {
		SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(this);

		EnumMap<MachineType, Boolean> usedMachineEnumMap = new EnumMap<MachineType, Boolean>(MachineType.class);
		usedMachineEnumMap.put(MachineType.OLD_SEMS, sharedPreference.getBoolean(PreferenceKeys.OLD_SEMS_USED, false));
		usedMachineEnumMap.put(MachineType.NEW_SEMS, sharedPreference.getBoolean(PreferenceKeys.NEW_SEMS_USED, false));
		usedMachineEnumMap.put(MachineType.LED_DIMMER, sharedPreference.getBoolean(PreferenceKeys.LED_DIMMER_USED, false));
		usedMachineEnumMap.put(MachineType.CARBON_HEATER, sharedPreference.getBoolean(PreferenceKeys.CARBON_HEATER_USED, false));

		actionBar.removeAllTabs();
		for (MachineType machineType : MachineType.values()) {
			if ( usedMachineEnumMap.get(machineType) ) {
				actionBar.addTab(tabEnumMap.get(machineType));
				//viewPagerFragmentList.get(i).showFirstPage();
			}
		}
	}

	@Override
	public void dataLabChanged() {
		viewPagerFragmentEnumMap.get(OLD_SEMS).getViewPager().getAdapter().notifyDataSetChanged();
		viewPagerFragmentEnumMap.get(OLD_SEMS).getViewPager().setAdapter(viewPagerFragmentEnumMap.get(OLD_SEMS).getViewPager().getAdapter());
		if ( !dataLabEnumMap.get(OLD_SEMS).isEmpty() ) {
			viewPagerFragmentEnumMap.get(OLD_SEMS).getViewPager().setCurrentItem(dataLabEnumMap.get(OLD_SEMS).size() - 1);
			//Log.i("utsnap", "dataLab size : " + dataLabEnumMap.get(OLD_SEMS).size());
		}
	}

	public void dataLabChanged(MachineType machineType, Object addedData) {
		viewPagerFragmentEnumMap.get(machineType).getViewPager().getAdapter().notifyDataSetChanged();
		viewPagerFragmentEnumMap.get(machineType).getViewPager().setAdapter(viewPagerFragmentEnumMap.get(machineType).getViewPager().getAdapter());
		int targetIndex = dataLabEnumMap.get(machineType).indexOf(addedData);
		viewPagerFragmentEnumMap.get(machineType).getViewPager().setCurrentItem(targetIndex, true);
	}

	public void dataLabChanged(MachineType machineType, int selectedIndex) {
		viewPagerFragmentEnumMap.get(machineType).getViewPager().getAdapter().notifyDataSetChanged();
		viewPagerFragmentEnumMap.get(machineType).getViewPager().setAdapter(viewPagerFragmentEnumMap.get(machineType).getViewPager().getAdapter());
		viewPagerFragmentEnumMap.get(machineType).getViewPager().setCurrentItem(selectedIndex, true);
	}

	/**
	 * 데이터를 받았을 경우에 수행하는 메소드
	 * 데이터 객체를 얻어내서, 해당 data lab에 저장하고,
	 * 해당 기계의 탭을 선택하고,
	 * 뷰페이저를 업데이트한다.
	 * */
	private void dataReceived(Intent intent) {
		bModified = true;
		String machintTypeJson = intent.getStringExtra(EXTRA_MACHINE_TYPE_JSON);
		modifiedMachine = GSON.fromJson(machintTypeJson, MachineType.class);
		Class dataClass = (Class) intent.getSerializableExtra(EXTRA_DATA_CLASS);
		String dataJson = intent.getStringExtra(EXTRA_DATA_JSON);
		modifiedDataIndex = intent.getIntExtra(EXTRA_DATA_LAB_INDEX, -1);

		//필수 : 받은 데이터의 탭을 활성화시킨다.
		actionBar.setSelectedNavigationItem(modifiedMachine.index);
		//완료

		//필수 : 데이터를 메모리에 저장하고, 뷰페이저를 업데이트 한다.
		dataLabEnumMap.get(modifiedMachine).set(modifiedDataIndex, GSON.fromJson(dataJson, dataClass));
		//완료
	}

	/**
	 * 데이터를 받은 다음에 수행되는 메소드.
	 * 데이터를 받았을때 사용한 변수들을 원래대로 초기화한다.
	 * */
	private void receivedDataProcessed() {
		dataLabChanged(modifiedMachine, modifiedDataIndex);
		bModified = false;
		modifiedMachine = null;
		modifiedDataIndex = -1;
		addedData = null;
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
			else if ( key.equals(PreferenceKeys.CARBON_HEATER_USED) ) {
				if ( !sharedPreferences.getBoolean(key, false) ) {
					PreferenceManager.getDefaultSharedPreferences(context).edit().putString(PreferenceKeys.CARBON_HEATER_PHONE_NUMBER, "").commit();
				}
			}
			else if ( key.equals(PreferenceKeys.FIRST_PHONE_NUMBER) ) {
				//Log.i("utsnap", "전화번호 변경됨..");
				String firstPhoneNumber = sharedPreferences.getString(PreferenceKeys.FIRST_PHONE_NUMBER, "");
				if ( !firstPhoneNumber.equals("") ) {
					//필수 : 기계에 비상시에 연락하는 번호를 등록해야 한다.
				}
			}
			else if ( key.equals(PreferenceKeys.SECOND_PHONE_NUMBER) ) {
				//Log.i("utsnap", "전화번호 변경됨..");
				String secondPhoneNumber = sharedPreferences.getString(PreferenceKeys.SECOND_PHONE_NUMBER, "");
				if ( !secondPhoneNumber.equals("") ) {
					//필수 : 기계에 비상시에 연락하는 번호를 등록해야 한다.
				}
			}
			else if ( key.equals(PreferenceKeys.THIRD_PHONE_NUMBER) ) {
				//Log.i("utsnap", "전화번호 변경됨..");
				String thirdPhoneNumber = sharedPreferences.getString(PreferenceKeys.THIRD_PHONE_NUMBER, "");
				if ( !thirdPhoneNumber.equals("") ) {
					//필수 : 기계에 비상시에 연락하는 번호를 등록해야 한다.
				}
			}
		}
	}
}
