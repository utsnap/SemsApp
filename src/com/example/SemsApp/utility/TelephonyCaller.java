package com.example.SemsApp.utility;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import com.example.SemsApp.preference.PreferenceKeys;

/**
 * Created by Administrator on 14. 3. 23.
 * 기계에 전화하는 기능을 구현한다.
 */
public class TelephonyCaller {

	/**
	 * 구셈스기계에 전화를 건다.
	 * 설정에서의 기계 사용여부를 조사하고, 그후에 전화번호를 참조한다.
	 * */
	public static final void callToOldSems(Activity activity) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
		boolean oldSemsUsed = sharedPreferences.getBoolean(PreferenceKeys.OLD_SEMS_USED, false);

		/*기계사용여부 확인*/
		if ( oldSemsUsed ) {
			String oldSemsPhoneNumber = sharedPreferences.getString(PreferenceKeys.OLD_SEMS_PHONE_NUMBER, "");
			/*설정에서 기계의 전화번호를 입력한 경우*/
			if ( !oldSemsPhoneNumber.equals("") ) {
				String uriString = "tel:" + oldSemsPhoneNumber;
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(uriString));
				activity.startActivity(intent);
			}
			/*기계 사용을 선택하고, 전화번호를 입력하지 않은 경우*/
			else {
				//필수 : 전화번호를 입력하라고 알려야 한다.
			}
		}
		/*기계 사용이 설정되어 있지 않은 경우*/
		else {
			//필수 : 기계 사용여부를 선택하라고 알려야 한다.
		}
	}
}
