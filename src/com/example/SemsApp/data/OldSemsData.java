package com.example.SemsApp.data;

import android.content.SharedPreferences;
import com.google.gson.Gson;

/**
 * Created by Administrator on 14. 3. 20.
 * 옛날 샘스기계의 상태정보를 나타낸다.
 *
 * 선택 : 빌더 패턴을 사용할 것을 추천.
 */
public class OldSemsData extends AbsData {
	private static final Gson GSON = new Gson();
	private static final String[] DETAILS = {
			"첫번째 비어있는 데이터", "두번째 비어있는 데이터", "세번째 비어있는 데이터", "네번째 비어있는 데이터"
	};

	public String detail;

	/**
	 * 문자메세지에서 얻어온 문자열을 분석하여 객체로 반환한다.
	 * 테스트용이다
	 * */
	public static OldSemsData getDefaultInstance(int index) {
		return new OldSemsData(index, DETAILS[index]);
	}

	public static OldSemsData getInstance(int index, String detail) {
		return new OldSemsData(index, detail);
	}

	public static OldSemsData getInstance(String preferenceKey, int index, String detail) {
		return new OldSemsData(preferenceKey, index, detail);
	}

	private OldSemsData(int index, String detail) {
		super(index);
		this.detail = detail;
	}

	private OldSemsData(String preferenceKey, int index, String detail) {
		super(index);
		this.preferenceKey = preferenceKey;
		this.detail = detail;
	}

	@Override
	protected Object getObject() {
		return this;
	}

	@Override
	protected Class<?> getDataClass() {
		return OldSemsData.class;
	}

	@Override
	protected void copyFrom(Object data) {
		OldSemsData oldSemsData = (OldSemsData) data;
		this.detail = new String(oldSemsData.detail);
	}

	@Override
	protected Object loadDefaultData(SharedPreferences preferences) {
		return OldSemsData.getDefaultInstance(index);
	}
}
