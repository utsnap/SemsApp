package com.example.SemsApp.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Administrator on 14. 3. 20.
 * xml 리소스 id(preference.xml)을 번들로 넘겨받아, 화면을 구성하는 프래그먼트
 */
public class SettingFragment extends PreferenceFragment {
	private static final String EXTRA_XML_RESOURCE_ID = "extra_xml_resource_id";

	public static SettingFragment newInstance(int xmlResourceId) {
		Bundle bundle = new Bundle();
		bundle.putInt(EXTRA_XML_RESOURCE_ID, xmlResourceId);

		SettingFragment settingFragment = new SettingFragment();
		settingFragment.setArguments(bundle);
		return settingFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int xmlResourceId = getArguments().getInt(EXTRA_XML_RESOURCE_ID);
		addPreferencesFromResource(xmlResourceId);
	}
}