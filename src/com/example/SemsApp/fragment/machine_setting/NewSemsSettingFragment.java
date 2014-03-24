package com.example.SemsApp.fragment.machine_setting;

import com.example.SemsApp.R;

/**
 * Created by Administrator on 14. 3. 23.
 */
public class NewSemsSettingFragment extends AbsMachineSettingFragment {

	@Override
	protected String getTitle() {
		return "New Sems 설정";
	}

	@Override
	protected int getXmlResourceId() {
		return R.xml.new_sems_preference;
	}
}