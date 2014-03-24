package com.example.SemsApp.data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Administrator on 14. 3. 20.
 * 옛날 샘스기계의 상태정보를 나타낸다.
 *
 * 선택 : 빌더 패턴을 사용할 것을 추천.
 */
public class OldSemsData implements Serializable {

	private HashMap<Key, ?> tokenHashMap;
	public String order;
	private int number;

	/**
	 * 문자메세지에서 얻어온 문자열을 분석하여 객체로 반환한다.
	 * 테스트용이다
	 * */
	public static OldSemsData getInstance(String string) {
		//필수 : 문자열처리 및 객체 생성
		return new OldSemsData(string);
	}

	/**
	 * 문자메세지에서 얻어온 문자열을 분석하여 객체로 반환한다.
	 * */
	public static OldSemsData getInstance(HashMap<Key, ?> tokenHashMap) {
		//필수 : 맵에서 정보를 읽어서 객체 생성
		return new OldSemsData(tokenHashMap);
	}

	private OldSemsData(String order) {
		this.order = order;
	}

	private OldSemsData(HashMap<Key, ?> tokenHashMap) {
		this.tokenHashMap = tokenHashMap;
	}

	static enum Key {
		KEY_ORDER_STRING(String.class),
		KEY_NAME_INTEGER(Integer.class);

		Class aClass;

		Key(Class aClass) {
			this.aClass = aClass;
		}
	}
}
