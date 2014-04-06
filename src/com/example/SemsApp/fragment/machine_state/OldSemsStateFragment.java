package com.example.SemsApp.fragment.machine_state;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.SemsApp.R;
import com.example.SemsApp.data.OldSemsData;

/**
 * Created by Administrator on 14. 3. 20.
 * 구버전 샘스기계의 정보를 보여주는 프래그먼트
 */
public class OldSemsStateFragment extends Fragment {
	private static final String EXTRA_OLD_SEMS_DATA = "extra_old_sems_data";

	private OldSemsData oldSemsData;

	public static OldSemsStateFragment newInstance(OldSemsData oldSemsData) {
		Bundle bundle = new Bundle();
		bundle.putSerializable(EXTRA_OLD_SEMS_DATA, oldSemsData);

		OldSemsStateFragment oldSemsStateFragment = new OldSemsStateFragment();
		oldSemsStateFragment.setArguments(bundle);

		return oldSemsStateFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		oldSemsData = (OldSemsData) getArguments().getSerializable(EXTRA_OLD_SEMS_DATA);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.old_sems_state_fragment, null);
		TextView textView = (TextView) view.findViewById(R.id.oldSemsTextView);
		textView.setText(oldSemsData.detail);

		return view;
	}
}