package com.example.SemsApp.fragment.dialog;

import android.view.View;
import com.example.SemsApp.R;

/**
 * Created by Administrator on 14. 3. 20.
 * 옛날 샘스기계의 기능을 사용하기 위한 다이얼로그
 */
public class OldSemsFunctionDialogFragment extends AbsFunctionDialog {
	@Override
	protected String getTitle() {
		return "Old Sems 기능";
	}

	@Override
	protected View getDetailView() {
		return getActivity().getLayoutInflater().inflate(R.layout.old_sems_function_dialog, null, false);
	}

	@Override
	protected void setEventHandlers() {
		;
	}
}