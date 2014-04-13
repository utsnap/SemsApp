package com.example.SemsApp.data;

import com.google.gson.Gson;

/**
 * Created by Administrator on 14. 3. 20.
 * 옛날 샘스기계의 상태정보를 나타낸다.
 *
 * 선택 : 빌더 패턴을 사용할 것을 추천.
 */
public class OldSemsData extends AbsData {
	private static final Gson GSON = new Gson();

	public String detail;

	/**
	 * 문자메세지에서 얻어온 문자열을 분석하여 객체로 반환한다.
	 * 테스트용이다
	 * */
	public static OldSemsData getInstance(int order, String string) {
		//필수 : 문자열처리 및 객체 생성
		return new OldSemsData(order, string);
	}

	private OldSemsData(int order, String detail) {
		this.order = order;
		this.detail = detail;
	}

	public OldSemsData(String preferenceKey, int order, String detail) {
		this.preferenceKey = preferenceKey;
		this.order = order;
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
		this.detail = oldSemsData.detail;
	}
}
