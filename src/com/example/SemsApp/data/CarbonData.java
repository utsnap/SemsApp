package com.example.SemsApp.data;

import android.content.SharedPreferences;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Administrator on 14. 3. 20.
 * 탄소봉 기계의 상태정보를 나타낸다.
 *
 * 선택 : 빌더 패턴을 사용할 것을 추천.
 *
 * 정태가 작업한다.
 */
public class CarbonData extends AbsData implements Serializable {

	public CarbonData(int index) {
		super(index);
	}

	/**
	 * 문자메세지에서 얻어온 문자열을 분석하여 객체로 반환한다.
	 * */
	public static CarbonData getInstance(HashMap<Integer, ?> tokenHashMap) {
		//필수 : 맵에서 정보를 읽어서 객체 생성
		return null;
	}

	@Override
	protected Object getSerializingObject() {
		return this;
	}

	@Override
	protected Class<?> getSerializingDataClass() {
		return CarbonData.class;
	}

	@Override
	protected void copyFrom(Object infoData) {
		;
	}

	@Override
	protected Object loadDefaultInfoData(SharedPreferences preferences) {
		return null;
	}
}
