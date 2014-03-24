package com.example.SemsApp.fragment.machine_state;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.SemsApp.R;
import com.example.SemsApp.data.CarbonData;

/**
 * Created by Administrator on 14. 3. 20.
 * 탄소봉 기계의 상태를 보여주는 프래그먼트
 */
public class CarbonStateFragment extends Fragment {
	private CarbonData carbonData;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.carbon_state_fragment, null);
		return view;
	}
}