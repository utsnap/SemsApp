package com.example.SemsApp.fragment.machine_setting;

import com.example.SemsApp.R;

/**
 * Created by Administrator on 14. 3. 23.
 */
public class CarbonSettingFragment extends AbsMachineSettingFragment {

	@Override
	protected String getTitle() {
		return "탄소봉 히터 설정";
	}

	@Override
	protected int getXmlResourceId() {
		return R.xml.carbon_preference;
	}
}