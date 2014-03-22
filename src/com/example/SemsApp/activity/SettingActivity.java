package com.example.SemsApp.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import com.example.SemsApp.R;
import com.example.SemsApp.fragment.SettingFragment;

/**
 * Created by Administrator on 14. 3. 20.
 */
public class SettingActivity extends Activity {
	public static final String EXTRA_XML_RESOURCE_ID = "extra_xml_resource_id";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_fragment_activity);

		FragmentManager fragmentManager = getFragmentManager();
		Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
		if ( fragment == null ) {
			if ( getIntent() != null ) {
				int xmlResourceId = getIntent().getIntExtra(EXTRA_XML_RESOURCE_ID, 0);
				fragment = SettingFragment.newInstance(xmlResourceId);
				fragmentManager.beginTransaction()
					.add(R.id.fragmentContainer, fragment)
					.commit();
			}
		}
	}
}