package com.example.SemsApp.data;

import android.content.SharedPreferences;
import com.example.SemsApp.data.new_sems.NewSemsInfoData;
import com.example.SemsApp.data.new_sems.NewSemsKindData;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Administrator on 14. 3. 20.
 * 새로운 샘스기계의 상태정보를 나타낸다.
 *
 * 선택 : 빌더 패턴을 사용할 것을 추천.
 */
public class NewSemsData extends AbsData implements Serializable {
	public NewSemsInfoData newSemsInfoData;

	public static NewSemsData getInstance(String prefernenceKey, int index, NewSemsInfoData newSemsInfoData) {
		return new NewSemsData(prefernenceKey, index, newSemsInfoData);
	}

	private NewSemsData(String prefernenceKey, int index, NewSemsInfoData newSemsInfoData) {
		super(index);
		this.preferenceKey = prefernenceKey;
		this.newSemsInfoData = newSemsInfoData;
	}

	@Override
	protected Object getSerializingObject() {
		return newSemsInfoData;
	}

	@Override
	protected Class<?> getSerializingDataClass() {
		return NewSemsInfoData.class;
	}

	@Override
	protected void copyFrom(Object infoData) {
		if ( infoData instanceof NewSemsInfoData ) {
			newSemsInfoData = (NewSemsInfoData) infoData;
		}
	}

	@Override
	protected Object loadDefaultInfoData(SharedPreferences preferences) {
		return NewSemsInfoData.getDefaultInstance(index);
	}
}
