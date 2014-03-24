package com.example.SemsApp.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.SemsApp.R;
import com.example.SemsApp.fragment.SettingFragment;

/**
 * Created by Administrator on 14. 3. 20.
 * 셈스앱의 기본설정을 하는 액티비티이다.
 * app_preference.xml에 변경데이터를 저장한다.
 *
 * -- 저장되는 정보 --
 * 각각의 기계 설정을 수정하는데 사용하는 비빌번호
 * New Sems 기게의 사용여부
 * New Sems 기게의 전화번호
 * Old Sems 기게의 사용여부
 * Old Sems 기게의 전화번호
 * Led 조광기의 사용여부
 * Led 조광기의 전화번호
 * 탄소봉 기게의 사용여부
 * 탄소봉 기게의 전화번호
 * */
public class AppSettingActivity extends SingleFragmentActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("샘스 기본설정");
		if ( getActionBar() != null ) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	protected Fragment createFragment(Intent intent) {
		return SettingFragment.newInstance(R.xml.app_preference);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if ( item.getItemId() == android.R.id.home ) {
			backToMain();
		}
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	public void onBackPressed() {
		backToMain();
	}

	private void backToMain() {
		Intent intent = new Intent();
		setResult(RESULT_OK, intent);
		finish();
	}
}