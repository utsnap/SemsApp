package com.example.SemsApp.fragment.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import com.example.SemsApp.R;

/**
 * Created by Administrator on 14. 3. 20.
 * 새로운 샘스기계를 사용하기 위한 다이얼로그
 */
public class NewSemsFunctionDialogFragment extends AbsFunctionDialog {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return super.onCreateDialog(savedInstanceState);
	}

	@Override
	protected String getTitle() {
		return "New Sems 기능";
	}

	@Override
	protected View getDetailView() {
		return getActivity().getLayoutInflater().inflate(R.layout.new_sems_function_dialog, null, false);
	}

	@Override
	protected void setEventHandlers() {
		;
	}
}