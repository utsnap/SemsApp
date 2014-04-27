package com.example.SemsApp.data;

import android.content.SharedPreferences;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Administrator on 14. 3. 20.
 * LED 조광기의 상태정보를 나타낸다.
 *
 * 선택 : 빌더 패턴을 사용할 것을 추천.
 */
public class LedData extends AbsData implements Serializable {
	public LedData(int index) {
		super(index);
	}

	/**
	 * 문자메세지에서 얻어온 문자열을 분석하여 객체로 반환한다.
	 * */
	public static LedData getInstance(HashMap<Integer, ?> tokenHashMap) {
		//필수 : 맵에서 정보를 읽어서 객체 생성
		return null;
	}

	@Override
	protected Object getObject() {
		return this;
	}

	@Override
	protected Class<?> getDataClass() {
		return LedData.class;
	}

	@Override
	protected void copyFrom(Object data) {
		;
	}

	@Override
	protected Object loadDefaultData(SharedPreferences preferences) {
		return null;
	}
}
