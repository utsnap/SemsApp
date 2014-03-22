package com.example.SemsApp.fragment.machine_state;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.SemsApp.R;

/**
 * Created by Administrator on 14. 3. 20.
 * LED 조광기의 상태를 보여주는 프래그먼트
 */
public class LedStateFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.led_state_fragment, null);
		return view;
	}
}