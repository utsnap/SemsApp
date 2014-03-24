package com.example.SemsApp.fragment.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import com.example.SemsApp.R;

/**
 * Created by Administrator on 14. 3. 20.
 * 탄소봉 기계의 기능을 사용하기 위한 다이얼로그
 */
public class CarbonFunctionDialogFragment extends AbsFunctionDialog {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return super.onCreateDialog(savedInstanceState);
	}

	@Override
	protected String getTitle() {
		return "탄소봉 기계 기능";
	}

	@Override
	protected View getDetailView() {
		return getActivity().getLayoutInflater().inflate(R.layout.carbon_function_dialog, null, false);
	}

	@Override
	protected void setEventHandlers() {
		;
	}
}