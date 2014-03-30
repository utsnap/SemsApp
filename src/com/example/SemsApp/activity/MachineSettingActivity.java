package com.example.SemsApp.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import com.example.SemsApp.application.SemsApplication;
import com.example.SemsApp.fragment.machine_setting.CarbonSettingFragment;
import com.example.SemsApp.fragment.machine_setting.LedSettingFragment;
import com.example.SemsApp.fragment.machine_setting.NewSemsSettingFragment;
import com.example.SemsApp.fragment.machine_setting.OldSemsSettingFragment;

import static com.example.SemsApp.application.SemsApplication.MachineType;

/**
 * Created by Administrator on 14. 3. 20.
 * 특정 기계의 정보를 수정하는 액티비티.
 * 설정파일은 기계이름_preference.xml에 저장된다.
 * */
public class MachineSettingActivity extends SingleFragmentActivity {
	public static final String EXTRA_MACHINE_TYPE = "extra_machine_type";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected Fragment createFragment(Intent intent) {
		if ( intent != null ) {
			Fragment fragment = null;
			int machineOrdinal = intent.getIntExtra(EXTRA_MACHINE_TYPE, 0);
			//Log.i("utsnap", "machine type : " + machineType);
			if ( machineOrdinal == MachineType.OLD_SEMS.ordinal() ) {
				fragment = new OldSemsSettingFragment();
			}
			else if ( machineOrdinal == MachineType.NEW_SEMS.ordinal() ) {
				fragment = new NewSemsSettingFragment();
			}
			else if ( machineOrdinal == MachineType.LED_DIMMER.ordinal() ) {
				fragment = new LedSettingFragment();
			}
			else if ( machineOrdinal == MachineType.CARBON_HEATER.ordinal() ) {
				fragment = new CarbonSettingFragment();
			}
			return fragment;
		}
		return null;
	}
}