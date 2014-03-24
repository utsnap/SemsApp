package com.example.SemsApp.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import com.example.SemsApp.application.SemsApplication;
import com.example.SemsApp.fragment.machine_setting.CarbonSettingFragment;
import com.example.SemsApp.fragment.machine_setting.LedSettingFragment;
import com.example.SemsApp.fragment.machine_setting.NewSemsSettingFragment;
import com.example.SemsApp.fragment.machine_setting.OldSemsSettingFragment;

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
			int machineType = intent.getIntExtra(EXTRA_MACHINE_TYPE, 0);
			//Log.i("utsnap", "machine type : " + machineType);
			switch ( machineType ) {
				case SemsApplication.OLD_SEMS :
					fragment = new OldSemsSettingFragment(); break;
				case SemsApplication.NEW_SEMS :
					fragment = new NewSemsSettingFragment(); break;
				case SemsApplication.LED_DIMMER :
					fragment = new LedSettingFragment(); break;
				case SemsApplication.CARBON_MACHINE :
					fragment = new CarbonSettingFragment(); break;
			}
			return fragment;
		}
		return null;
	}
}