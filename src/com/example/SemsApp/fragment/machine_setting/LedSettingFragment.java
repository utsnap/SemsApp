package com.example.SemsApp.fragment.machine_setting;

import com.example.SemsApp.R;

/**
 * Created by Administrator on 14. 3. 23.
 */
public class LedSettingFragment extends AbsMachineSettingFragment {

	@Override
	protected String getTitle() {
		return "LED조광기 상세설정";
	}

	@Override
	protected int getXmlResourceId() {
		return R.xml.led_preference;
	}
}