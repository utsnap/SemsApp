package com.example.SemsApp.fragment.machine_setting;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Administrator on 14. 3. 23.
 */
public abstract class AbsMachineSettingFragment extends PreferenceFragment {
	private static final String EXTRA_XML_RESOURCE_ID = "extra_xml_resource_id";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle(getTitle());
		addPreferencesFromResource(getXmlResourceId());
	}

	protected abstract String getTitle();

	protected abstract int getXmlResourceId();
}