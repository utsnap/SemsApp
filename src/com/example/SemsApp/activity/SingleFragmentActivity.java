package com.example.SemsApp.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import com.example.SemsApp.R;

/**
 * Created by Administrator on 14. 3. 13.
 * 하나의 프래그면트를 담는 액티비티이다.
 */
public abstract class SingleFragmentActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResId());

		FragmentManager fragmentManager = getFragmentManager();
		Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
		if ( fragment == null ) {
			fragment = createFragment(getIntent());
			fragmentManager.beginTransaction()
				.add(R.id.fragmentContainer, fragment)
				.commit();
		}
	}

	protected int getLayoutResId() {
		return R.layout.single_fragment_activity;
	}

	protected abstract Fragment createFragment(Intent intent);
}