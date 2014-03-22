package com.example.SemsApp.fragment.machine_state;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.SemsApp.R;

/**
 * Created by Administrator on 14. 3. 20.
 * 신버전 샘스기계의 정보를 보여주는 프래그먼트
 */
public class NewSemsStateFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.new_sems_state_fragment, null);
		return view;
	}
}